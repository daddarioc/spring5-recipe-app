package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.model.Ingredient;
import guru.springframework.model.Recipe;
import org.springframework.stereotype.Service;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeServiceImpl recipeService;
    private final IngredientToIngredientCommand convertor;


    public IngredientServiceImpl(RecipeServiceImpl recipeService, IngredientToIngredientCommand convertor) {
        this.recipeService = recipeService;
        this.convertor = convertor;
    }

    @Override
    public IngredientCommand save(IngredientCommand ingredientCommand) {



        return null;
    }

    @Override
    public IngredientCommand findByRecipeIdandIngredientId(Long recipeId, Long ingredientId) {

        Recipe recipeCommand = recipeService.findById(recipeId);

        IngredientCommand ingredientCommand = new IngredientCommand();

        for (Ingredient ingredient : recipeCommand.getIngredients()) {
            if (ingredient.getId() == ingredientId) {
                return convertor.convert(ingredient);

            }
        }

        return null;
    }
}
