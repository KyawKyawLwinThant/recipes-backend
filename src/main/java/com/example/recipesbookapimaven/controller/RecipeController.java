package com.example.recipesbookapimaven.controller;

import com.example.recipesbookapimaven.model.Recipe;
import com.example.recipesbookapimaven.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;
    @GetMapping("/list-recipes")
    public ResponseEntity listRecipes(){
        return ResponseEntity.ok()
                .body(recipeService.findAllRecipe())
                ;
    }



    record RequestRecipe(String title, String prepTime, String cookTime,
                       int servings, List<String> ingredients,
                         List<String> steps,String imageUrl){}
    record ResponseRecipe(int id,String title){}
    @Transactional
    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseRecipe createRecipe(@RequestBody RequestRecipe requestRecipe){
        Recipe recipe=new Recipe();
        List<String> ingredients=requestRecipe.ingredients();
        List<String> steps=requestRecipe.steps();

        ingredients.stream()
                .forEach(i -> recipe.addIngredients(i));
        steps.stream().forEach(s -> recipe.addStep(s));

        recipe.setCookTime(requestRecipe.cookTime());
        recipe.setServings(requestRecipe.servings());
        recipe.setImageUrl(requestRecipe.imageUrl());
        recipe.setPrepTime(requestRecipe.prepTime());
        recipe.setTitle(requestRecipe.title());

        Recipe saveRecipe=recipeService.saveRecipe(recipe);

        return  new ResponseRecipe(saveRecipe.getId(),
                saveRecipe.getTitle());
    }

}
