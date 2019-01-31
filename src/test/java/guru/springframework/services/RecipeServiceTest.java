package guru.springframework.services;

import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.model.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceTest {

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @InjectMocks
    RecipeServiceImpl recipeService;

    Set<Recipe> recipes = new HashSet<>();

    @BeforeEach
    public void setUp() throws Exception {
        recipes.add(new Recipe().builder().id(1L).description("recipe 1").build());
        recipes.add(new Recipe().builder().id(2L).description("recipe 2").build());

        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipeByIdTest() {
        final Long id = 3L;
        Recipe recipe = new Recipe().builder().id(id).description("new recipe").build();

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        Recipe newRecipe = recipeService.findById(id);

        assertNotNull("Returned null", newRecipe);
        assertEquals(id, newRecipe.getId());
        verify(recipeRepository, times(1)).findById(id);
    }

    @Test
    public void getRecipesTest() {
        when(recipeRepository.findAll()).thenReturn(recipes);

        Set<Recipe> retrievedRecipes = recipeService.getRecipes();

        assertEquals(2, retrievedRecipes.size());
        verify(recipeRepository, times(1)).findAll();
    }
}