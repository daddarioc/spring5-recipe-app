package guru.springframework.controllers;

import guru.springframework.model.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    RecipeController controller;

    @Mock
    RecipeService service;

    Recipe recipe;
    final Long id = 1L;

    @BeforeEach
    void setUp() {

        controller = new RecipeController(service);
        recipe = new Recipe().builder().id(id).description("new recipe").build();
    }

    @Test
    void showById() throws Exception {
        when(service.getRecipeById(anyLong())).thenReturn(recipe);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/recipe/show/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attribute("recipe", recipe));

        verify(service, times(1)).getRecipeById(id);
    }
}