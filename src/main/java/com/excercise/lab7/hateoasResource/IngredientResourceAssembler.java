package com.excercise.lab7.hateoasResource;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import com.excercise.lab7.object.Ingredient;
import com.excercise.lab7.restController.DesignTacoController;

class IngredientResourceAssembler extends RepresentationModelAssemblerSupport<Ingredient, IngreDientResouce> {
	public IngredientResourceAssembler() {
		super(DesignTacoController.class, IngreDientResouce.class);
	}

	@Override
	public IngreDientResouce toModel(Ingredient ingredient) {
		return createModelWithId(ingredient.getId(), ingredient);
	}

	@Override
	protected IngreDientResouce instantiateModel(Ingredient ingredient) {
		return new IngreDientResouce(ingredient);
	}
}
