package guru.springframework.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 *  Represents an recipe which is made up of ingredients, and has an accompanying notes
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions;

    /* indicate this should be persisted to the database as a string value (i.e. hard), instead of 1 or 2 or 3 of the enum */
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    /* indicate one notes per one recipe, changes from recipe cascade down to the notes; this also indicates Recipe being
        the relationship owner via cascade
     */
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    /* one recipe will have many ingredients; indicates Recipe is the relationship owner, changes persist to ingredients
        and that the relationship is defined by the reference property "recipe" in the Ingredient object
     */

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @ManyToMany // a recipe can have any number of categories
    @JoinTable(name = "recipe_category",
        joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    @Lob // indicate that this may be larger than standard size
    private Byte[] image;

    @Override
    public String toString() {
        return this.description;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);
    }

    public Recipe addIngredient(Ingredient ingredient) {
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

}
