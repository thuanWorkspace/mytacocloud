package com.excercise.lab7.hateoasResource;

import java.util.Date;
import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.excercise.lab7.object.Ingredient;
import com.excercise.lab7.object.Taco;

@Relation(value="taco", collectionRelation="tacos")
public class TacoResource2 extends RepresentationModel {
	private final String name;

	private final Date createdAt;

	private final CollectionModel<IngreDientResouce> ingredients;

	public TacoResource2(Taco taco) {
		this.name = taco.getName();
		this.createdAt = taco.getCreatedAt();
		this.ingredients = new IngredientResourceAssembler()
				.toCollectionModel(taco.getIngredients());

	}

	public String getName() {
		return name;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public CollectionModel<IngreDientResouce> getIngredients() {
		return ingredients;
	}

	

}