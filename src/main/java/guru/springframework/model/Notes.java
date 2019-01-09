package guru.springframework.model;

import javax.persistence.*;

/**
 * Class that represents a note for a given recipe
 */
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob    // indicates to Hibernate this will be larger than the standard 255 char string
    private String recipeNote;

    @OneToOne   // only one note per one recipe
    private Recipe recipe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipeNote() {
        return recipeNote;
    }

    public void setRecipeNote(String recipeNote) {
        this.recipeNote = recipeNote;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public String toString() {
        return recipeNote;
    }
}
