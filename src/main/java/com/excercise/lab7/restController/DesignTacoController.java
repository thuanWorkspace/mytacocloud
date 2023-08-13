package com.excercise.lab7.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.core.ControllerEntityLinks;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
//import org.springframework.hateoas.server.mvc.WebMvcLinkBuilde;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.excercise.lab7.hateoasResource.TacoResource2;
import com.excercise.lab7.hateoasResource.TacoResourceAssembler;
import com.excercise.lab7.jpaRepository.TacoRepository;
import com.excercise.lab7.object.Taco;

@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoController {
	private TacoRepository tacoRepo;

//	@Autowired
//	EntityLinks entityLinks;
	@Autowired
	public DesignTacoController(TacoRepository tacoRepo) {
		this.tacoRepo = tacoRepo;
	}

//	@GetMapping("/recent")
//	public Iterable<Taco> recentTacos() {
//		PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
//		return tacoRepo.findAll(page).getContent();
//	}
	/**
	 * insert into taco(created_at,id,name) values (now(),1,'good');
insert into taco(created_at,id,name) values (now(),2,'taco2');
insert into taco(created_at,id,name) values (now(),3,'taco3');
insert into taco_ingredients(taco_id,ingredients_id) values (1,'CARN');
insert into taco_ingredients(taco_id,ingredients_id) values (1,'COTO');
insert into taco_ingredients(taco_id,ingredients_id) values (1,'FLTO');
insert into taco_ingredients(taco_id,ingredients_id) values (1,'JACK');
insert into taco_ingredients(taco_id,ingredients_id) values (2,'TMTO');
insert into taco_ingredients(taco_id,ingredients_id) values (2,'SRCR');
insert into taco_ingredients(taco_id,ingredients_id) values (2,'SLSA');
insert into taco_ingredients(taco_id,ingredients_id) values (2,'LETC');
insert into taco_ingredients(taco_id,ingredients_id) values (3,'JACK');
insert into taco_ingredients(taco_id,ingredients_id) values (3,'GRBF');
insert into taco_ingredients(taco_id,ingredients_id) values (3,'CHED');
insert into taco_ingredients(taco_id,ingredients_id) values (3,'CARN');
	 * @return
	 */
	@GetMapping("/recent")
	public CollectionModel<TacoResource2> recentTacos() {
		PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
		List<Taco> tacos = tacoRepo.findAll(page).getContent();
		Iterable<Taco> iterableTacos = tacos;
		CollectionModel<TacoResource2> tacoResources =
				new TacoResourceAssembler().toCollectionModel(iterableTacos);
//		CollectionModel<TacoResource> recentResources = new CollectionModel (tacoResources) ;
//		recentResources.add(new Link(, ));
//		recentResources.add(Link.of("http://localhost:8080/design/recent", "recents"));
		tacoResources.add(
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DesignTacoController.class).recentTacos())
				//				.slash("recent")
				.withRel("recents"));
		return tacoResources;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
		Optional<Taco> optTaco = tacoRepo.findById(id);
		if (optTaco.isPresent()) {
			return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Taco postTaco(@RequestBody Taco taco) {
		return tacoRepo.save(taco);
	}

}
