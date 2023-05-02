package com.example.recipesbookapimaven.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String prepTime;
    private String cookTime;
    private int servings;
    @ElementCollection
    private List<String> ingredients=
            new ArrayList<>();
    @ElementCollection
    private List<String> steps=
            new ArrayList<>();
    private String imageUrl;

    public void addStep(String step){
        this.steps.add(step);
    }
    public void addIngredients(String ingredient){
        this.ingredients.add(ingredient);
    }

    public Recipe(String title, String prepTime, String cookTime,int servings, List<String> ingredients, List<String> steps, String imageUrl) {
        this.title = title;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.ingredients = ingredients;
        this.steps = steps;
        this.imageUrl = imageUrl;
    }

    public Recipe(){

    }



}
