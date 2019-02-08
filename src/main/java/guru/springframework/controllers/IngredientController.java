package guru.springframework.controllers;

import guru.springframework.services.IngredientServiceImpl;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientServiceImpl ingredientService;

    public IngredientController(RecipeService recipeService, IngredientServiceImpl ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String showIngredients(@PathVariable String recipeId, Model model) {

        log.debug("Retrieving ingredient list for recipe: " + recipeId);

        model.addAttribute("recipe", recipeService.findByCommandId(Long.valueOf(recipeId)));

        return "recipe/ingredients/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredients/{id}/show")
    public String showRecipeIngredient(@PathVariable String recipeId,
                                       @PathVariable String id,
                                       Model model) {

        model.addAttribute("ingredient", ingredientService.findByRecipeIdandIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
        return "recipe/ingredients/show";
    }
}
