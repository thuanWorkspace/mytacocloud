package com.excercise.lab7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.excercise.lab7.jpaRepository.OrderRepository;
import com.excercise.lab7.object.Order;
//import com.excercise.lab7.repository.OrderRepository;
import com.excercise.lab7.object.User;
import com.excercise.lab7.service.OrderProps;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DesignTacoController.class);

	private OrderRepository orderRepo;
	private OrderProps props;
	
	
	@Autowired
	public OrderController(OrderRepository orderRepo, OrderProps props) {
		super();
		this.orderRepo = orderRepo;
		this.props = props;
	}

	@GetMapping("/current")
	public String orderForm() {
		return "orderForm";
	}

	@GetMapping
	public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
		Pageable pageable = PageRequest.of(0, props.getPageSize());
		model.addAttribute("orders", orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));
		return "orderList";
	}

	@PostMapping
	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus,
			@AuthenticationPrincipal User user) {
		if (errors.hasErrors()) {
			return "orderForm";
		}
		order.setUser(user);
		System.out.println("user: "+user);
		order.getTacos().forEach(c -> {
			System.out.println(c);
			c.getIngredients().forEach(v -> System.out.println(v));
		});
		orderRepo.save(order);
		sessionStatus.setComplete();
		log.info("Order submitted: " + order);
		return "redirect:/";
	}

}
