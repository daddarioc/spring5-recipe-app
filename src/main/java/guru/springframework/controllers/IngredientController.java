package guru.springframework.controllers;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.services.IngredientServiceImpl;
import guru.springframework.services.RecipeService;
import guru.springframework.services.UnitOfMeasureServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientServiceImpl ingredientService;
    private final UnitOfMeasureServiceImpl unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientServiceImpl ingredientService, UnitOfMeasureServiceImpl unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
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

    @GetMapping("/recipe/{recipeId}/ingredients/{id}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId,
                                       @PathVariable String id,
                                       Model model) {

        model.addAttribute("ingredient", ingredientService.findByRecipeIdandIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
        model.addAttribute("unitOfMeasure", unitOfMeasureService.listAllUoms());

        return "recipe/ingredients/update";
    }

    @PostMapping("recipe/{recipeId}/ingredients")
    public String saveOrUpdate(@ModelAttribute IngredientCommand ingredientCommand) {

        IngredientCommand savedIngredient = ingredientService.save(command);

        return "redirect:/recipe" + savedIngredient.getRecipeId() + "/ingredients/" + savedIngredient.getId() + "/show";
    }
}
