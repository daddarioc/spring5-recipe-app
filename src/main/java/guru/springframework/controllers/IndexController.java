package guru.springframework.controllers;

import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller class for main index landing page
 */
@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private RecipeService recipeService;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeService recipeService) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "index"})
    public String getIndexPage(Model model) {

        // add the attribute "owners" to the model, which represents all the owners from the service
        model.addAttribute("recipes", recipeService.findAll());

        return "index";
    }
}
