package guru.springframework.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 *  Represents an recipe which is made up of ingredients, and has an accompanying note
 */
@Data
@Entity
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
    private String directions;

    /* indicate this should be persisted to the database as a string value (i.e. hard), instead of 1 or 2 or 3 of the enum */
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    /* indicate one note per one recipe, changes from recipe cascade down to the note; this also indicates Recipe being
        the relationship owner via cascade
     */
    @OneToOne(cascade = CascadeType.ALL)
    private Note note;

    /* one recipe will have many ingredients; indicates Recipe is the relationship owner, changes persist to ingredients
        and that the relationship is defined by the reference property "recipe" in the Ingredient object
     */

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredientSet;

    @Lob // indicate that this may be larger than standard size
    private Byte[] image;
}
