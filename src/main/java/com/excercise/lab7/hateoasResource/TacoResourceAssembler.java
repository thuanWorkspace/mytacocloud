package com.excercise.lab7.hateoasResource;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import com.excercise.lab7.object.Taco;
import com.excercise.lab7.restController.DesignTacoController;

public class TacoResourceAssembler extends RepresentationModelAssemblerSupport<Taco, TacoResource2> {
	public TacoResourceAssembler() {
		super(DesignTacoController.class, TacoResource2.class);
	}

//	@Override
//	protected TacoResource instantiateResource(Taco taco) {
//		return new TacoResource(taco);
//	}
	@Override
	protected TacoResource2 instantiateModel(Taco entity) {
		// TODO Auto-generated method stub
		return new TacoResource2(entity);
	}
	//by books spring in action.
//	@Override
//	public TacoResource toResource(Taco taco) {
//		return createResourceWithId(taco.getId(), taco);
//	}

	@Override
	public TacoResource2 toModel(Taco entity) {
		// TODO Auto-generated method stub
		return createModelWithId(entity.getId(), entity);
	}
}