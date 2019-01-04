package guru.springframework.services;

import guru.springframework.model.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

@Service
public class RecipeService  {

    private RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Iterable<Recipe> findAll() {
        return recipeRepository.findAll();
    }
}
