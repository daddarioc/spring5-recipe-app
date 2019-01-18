package guru.springframework.controllers;

import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class RecipeController {

    private final RecipeService service;

    public RecipeController(RecipeService service) {
        this.service = service;
    }

    @RequestMapping("/recipe/show/{id}")
    public String showById(@PathVariable Long id, Model model) {
        log.debug("in /recipe/show controller method");
        model.addAttribute("recipe", service.getRecipeById(id));

        return "recipe/show";
    }

}
