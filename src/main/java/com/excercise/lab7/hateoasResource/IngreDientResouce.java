package com.excercise.lab7.hateoasResource;

import org.springframework.hateoas.RepresentationModel;

import com.excercise.lab7.object.Ingredient;
import com.excercise.lab7.object.Ingredient.Type;

public class IngreDientResouce extends RepresentationModel {
	private String name;
	private Type type;

	public IngreDientResouce(Ingredient ingredient) {
		this.name = ingredient.getName();
		this.type = ingredient.getType();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
}