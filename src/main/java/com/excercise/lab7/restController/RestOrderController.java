package com.excercise.lab7.restController;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.excercise.lab7.jpaRepository.OrderRepository;
import com.excercise.lab7.object.Order;

@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class RestOrderController {
	private OrderRepository repo;

	@PutMapping("/{orderId}")
	public Order putOrder(@RequestBody Order order) {
		return repo.save(order);
	}

	@PatchMapping(path = "/{orderId}", consumes = "application/json")
	public Order patchOrder(@PathVariable("orderId") Long orderId, @RequestBody Order patch) {
		Order order = repo.findById(orderId).get();
		if (patch.getName() != null) {
			order.setName(patch.getName());
		}
		if (patch.getStreet() != null) {
			order.setStreet(patch.getStreet());
		}
		if (patch.getCity() != null) {
			order.setCity(patch.getCity());
		}
		if (patch.getState() != null) {
			order.setState(patch.getState());
		}
		if (patch.getZip() != null) {
			order.setZip(patch.getZip());
		}
		if (patch.getCcNumber() != null) {
			order.setCcNumber(patch.getCcNumber());
		}
		if (patch.getCcExpiration() != null) {
			order.setCcExpiration(patch.getCcExpiration());
		}
		if (patch.getCcCVV() != null) {
			order.setCcCVV(patch.getCcCVV());
		}
		return repo.save(order);
	}

	@DeleteMapping("/{orderId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteOrder(@PathVariable("orderId") Long orderId) {
		try {
			repo.deleteById(orderId);
		} catch (EmptyResultDataAccessException e) {
		}
	}
}
