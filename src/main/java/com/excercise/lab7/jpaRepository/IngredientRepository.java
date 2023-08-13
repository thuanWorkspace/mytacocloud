package com.excercise.lab7.jpaRepository;

import org.springframework.data.repository.CrudRepository;

import com.excercise.lab7.object.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
