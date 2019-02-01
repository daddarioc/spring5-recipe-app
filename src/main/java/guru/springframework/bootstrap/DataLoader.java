package guru.springframework.bootstrap;

import guru.springframework.model.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private RecipeRepository recipeRepository;
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoader(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional  // directs Spring framework to create a transaction around this method; will prevent seeming random exception
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {

        // get the units of measure
        log.info("Checking units of measure...");
        Optional<UnitOfMeasure> optionalEach = unitOfMeasureRepository.findByDescription("Each");
        if (optionalEach.isPresent() == false) {
            throw new RuntimeException("UoM not found");
        }

        Optional<UnitOfMeasure> optionalTeaspoon= unitOfMeasureRepository.findByDescription("Teaspoon");
        if (optionalTeaspoon.isPresent() == false) {
            throw new RuntimeException("UoM not found");
        }

        Optional<UnitOfMeasure> optionalTablespoon = unitOfMeasureRepository.findByDescription("Tablespoon");
        if (optionalTablespoon.isPresent() == false) {
            throw new RuntimeException("UoM not found");
        }

        Optional<UnitOfMeasure> optionalCup = unitOfMeasureRepository.findByDescription("Cup");
        if (optionalCup.isPresent() == false) {
            throw new RuntimeException("UoM not found");
        }

        Optional<UnitOfMeasure> optionalPint = unitOfMeasureRepository.findByDescription("Pint");
        if (optionalPint.isPresent() == false) {
            throw new RuntimeException("UoM not found");
        }

        Optional<UnitOfMeasure> optionalPinch = unitOfMeasureRepository.findByDescription("Pinch");
        if (optionalPinch.isPresent() == false) {
            throw new RuntimeException("UoM not found");
        }

        Optional<UnitOfMeasure> optionalDash = unitOfMeasureRepository.findByDescription("Dash");
        if (optionalDash.isPresent() == false) {
            throw new RuntimeException("UoM not found");
        }

        Optional<UnitOfMeasure> optionalOunce = unitOfMeasureRepository.findByDescription("Ounce");
        if (optionalOunce.isPresent() == false) {
            throw new RuntimeException("UoM not found");
        }

        // set local UoM
        log.info("Setting units of measure...");
        UnitOfMeasure uomEach = optionalEach.get();
        UnitOfMeasure uomTeaspoon = optionalTeaspoon.get();
        UnitOfMeasure uomTablespoon = optionalTablespoon.get();
        UnitOfMeasure uomCup = optionalCup.get();
        UnitOfMeasure uomPint = optionalPint.get();
        UnitOfMeasure uomPinch = optionalPinch.get();
        UnitOfMeasure uomDash = optionalDash.get();
        UnitOfMeasure uomOunce = optionalOunce.get();

        // get and set categories

        log.info("Preparing categories...");
        Optional<Category> optionalAmerican = categoryRepository.findByDescription("American");
        if (optionalAmerican.isPresent() == false){
            throw new RuntimeException("Category not found");
        }

        Optional<Category> optionalItalian = categoryRepository.findByDescription("Italian");
        if (optionalItalian.isPresent() == false){
            throw new RuntimeException("Category not found");
        }

        Optional<Category> optionalMexican = categoryRepository.findByDescription("Mexican");
        if (optionalAmerican.isPresent() == false){
            throw new RuntimeException("Category not found");
        }

        Optional<Category> optionalFastFood = categoryRepository.findByDescription("Fast Food");
        if (optionalAmerican.isPresent() == false){
            throw new RuntimeException("Category not found");
        }

        Category catAmerican = optionalAmerican.get();
        Category catItalian = optionalItalian.get();
        Category catMexican = optionalMexican.get();
        Category catFastFood = optionalFastFood.get();

        List<Recipe> recipes = new ArrayList<Recipe>();

        // assemble tacos
        log.info("Generating recipe...");
        Recipe tacos = new Recipe();

        tacos.setDescription("Spicy Grilled Chicken Tacos Recipe");
        tacos.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, " +
                "cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. " +
                "Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "Spicy Grilled Chicken Tacos\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted " +
                "into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. " +
                "As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for " +
                "a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. " +
                "Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the " +
                "thinned sour cream. Serve with lime wedges.");
        tacos.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        tacos.setSource("Simply Recipes");
        tacos.setServings(6);
        tacos.setDifficulty(Difficulty.MODERATE);
        tacos.setCookTime(15);
        tacos.setPrepTime(20);

        tacos.addIngredient(new Ingredient("Ancho chili powder", new BigDecimal(2), uomTablespoon));
        tacos.addIngredient(new Ingredient("dried oregano", new BigDecimal(1), uomTeaspoon));
        tacos.addIngredient(new Ingredient("dried cumin", new BigDecimal(1), uomTeaspoon));
        tacos.addIngredient(new Ingredient("sugar", new BigDecimal(1), uomTeaspoon));
        tacos.addIngredient(new Ingredient("salt", new BigDecimal(.5), uomTeaspoon));
        tacos.addIngredient(new Ingredient("garlic clove", new BigDecimal(1), uomEach));
        tacos.addIngredient(new Ingredient("grated orange zest", new BigDecimal(1), uomTablespoon));
        tacos.addIngredient(new Ingredient("orange juice", new BigDecimal(3), uomTablespoon));
        tacos.addIngredient(new Ingredient("olive oil", new BigDecimal(2), uomTablespoon));
        tacos.addIngredient(new Ingredient("boneless chicken thighs", new BigDecimal(6), uomEach));
        tacos.addIngredient(new Ingredient("small corn tortilla", new BigDecimal(8), uomEach));
        tacos.addIngredient(new Ingredient("baby arugula", new BigDecimal(3), uomCup));
        tacos.addIngredient(new Ingredient("avocado, sliced", new BigDecimal(2), uomEach));
        tacos.addIngredient(new Ingredient("radish, sliced", new BigDecimal(4), uomEach));
        tacos.addIngredient(new Ingredient("cherry tomatos, halved", new BigDecimal(.5), uomPint));
        tacos.addIngredient(new Ingredient("onion, thinly sliced", new BigDecimal(.25), uomEach));
        tacos.addIngredient(new Ingredient("cilantro, chopped", new BigDecimal(1), uomEach));
        tacos.addIngredient(new Ingredient("sour cream, thinned with 1/4 cup milk", new BigDecimal(.5), uomCup));
        tacos.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(1), uomEach));

        Notes tacoNote = new Notes();
        tacoNote.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on " +
                "buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and " +
                "the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)");
        tacoNote.setRecipe(tacos);

        tacos.setNotes(tacoNote);

        tacos.getCategories().add(catMexican);
        tacos.getCategories().add(catAmerican);


        recipes.add(tacos);
        log.info("Recipe added...");

        // assemble guacamole
        log.info("Generating recipe...");
        Recipe guac = new Recipe();

        guac.setPrepTime(10);
        guac.setCookTime(0);
        guac.setDescription("Guacomole");
        guac.setServings(4);
        guac.setDifficulty(Difficulty.EASY);
        guac.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the" +
                " avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will " +
                "provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.");


        guac.addIngredient(new Ingredient("avocado", new BigDecimal(2), uomEach));
        guac.addIngredient(new Ingredient("salt", new BigDecimal(.5), uomTeaspoon));
        guac.addIngredient(new Ingredient("lime juice", new BigDecimal(1), uomTablespoon));
        guac.addIngredient(new Ingredient("minced red onion", new BigDecimal(2), uomTablespoon));
        guac.addIngredient(new Ingredient("serrano chile", new BigDecimal(2), uomEach));
        guac.addIngredient(new Ingredient("cilantro, chopped", new BigDecimal(2), uomTablespoon));
        guac.addIngredient(new Ingredient("grated black pepper", new BigDecimal(1), uomDash));
        guac.addIngredient(new Ingredient("tomato", new BigDecimal(1), uomEach));

        Notes guacNote = new Notes();
        guacNote.setRecipeNotes("Be careful handling chiles if using. Wash your hands thoroughly after handling and " +
                "do not touch your eyes or the area near your eyes with your hands for several hours.");
        guac.setNotes(guacNote);
        guac.getCategories().add(catMexican);

        recipes.add(guac);
        log.info("Recipe added...");

        return recipes;

    }


}
