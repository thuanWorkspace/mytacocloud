package com.excercise.lab7.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.excercise.lab7.jpaRepository.IngredientRepository;
import com.excercise.lab7.jpaRepository.TacoRepository;
import com.excercise.lab7.object.Ingredient;
import com.excercise.lab7.object.Ingredient.Type;
import com.excercise.lab7.object.Order;
import com.excercise.lab7.object.Taco;
//import com.excercise.lab7.repository.IngredientRepository;

import jakarta.validation.Valid;

//@Slf4j
//@Controller
//@RequestMapping("/design")
//@SessionAttributes("order")
public class DesignTacoController {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DesignTacoController.class);
	private final IngredientRepository ingredientRepo;
	private final TacoRepository designRepo;

	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo) {
		super();
		this.ingredientRepo = ingredientRepo;
		this.designRepo = designRepo;
	}

	@GetMapping
	public String showDesignForm(Model model) {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepo.findAll().forEach(i -> ingredients.add(i));
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
		model.addAttribute("design", new Taco());
		return "design";
	}
//	@RequestMapping(method = RequestMethod.GET)
////	@GetMapping
//	public String showDesignForm(Model model) {
//		return coreDesignForm(model);
//	}
//
//	public String coreDesignForm(Model model) {
//		List<Ingredient> ingredients = Arrays.asList(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
//				new Ingredient("COTO", "Corn Tortilla", Type.WRAP), new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
//				new Ingredient("CARN", "Carnitas", Type.PROTEIN),
//				new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES), new Ingredient("LETC", "Lettuce", Type.VEGGIES),
//				new Ingredient("CHED", "Cheddar", Type.CHEESE), new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
//				new Ingredient("SLSA", "Salsa", Type.SAUCE), new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
//		Type[] types = Ingredient.Type.values();
//		for (Type type : types) {
//			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
//		}
//		model.addAttribute("design", new Taco());
//		return "design";
//	}

	

	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}

	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}
	//@ModelAttribute Order order ==(Oder) model.getaatttribute (order) 
	@PostMapping
	public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order) {
		if (errors.hasErrors()) {
			return "design";
		}
		design.getIngredients().forEach(a -> System.out.println(a));
		Taco saved = designRepo.save(design);
		order.addDesign(design);
		return "redirect:/orders/current";
	}

	private Object filterByType(List<Ingredient> ingredients, Type type) {
		// TODO Auto-generated method stub
		List<Ingredient> ingredientByType = new ArrayList<>();
		for (Ingredient ingredient : ingredients) {
			if (ingredient.getType().equals(type))
				ingredientByType.add(ingredient);
		}
		return ingredientByType;
	}
}
