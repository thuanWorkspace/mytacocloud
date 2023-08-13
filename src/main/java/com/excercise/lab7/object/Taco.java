package com.excercise.lab7.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.rest.core.annotation.RestResource;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@RestResource(rel="tacos",path="tacos")
public class Taco implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// params
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	// end
	@NotNull
	@Size(min = 5, message = "Name must be at least 5 characters long")
	private String name;
	private Date createdAt;
	//
	@ManyToMany(cascade = CascadeType.MERGE)
	@Size(min = 1, message = "You must choose at least 1 ingredient")
	private List<Ingredient> ingredients;
	// Custom method to associate existing Ingredient objects or create new ones

	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}

	public Taco(String name, List<Ingredient> ingredients) {
		super();
		this.name = name;
		this.ingredients = ingredients;
	}

	public Taco() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public String toString() {
		return "Taco [name=" + name + "]";
	}

}
