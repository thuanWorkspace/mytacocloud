package com.excercise.lab7.repository;

import com.excercise.lab7.object.Ingredient;

public interface IngredientRepository {
	Iterable<Ingredient> findAll();

	Ingredient findOne(String id);

	Ingredient save(Ingredient ingredient);

}
