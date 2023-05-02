package com.example.recipesbookapimaven.dao;

import com.example.recipesbookapimaven.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RecipeDao extends CrudRepository<Recipe,Integer> {
}
