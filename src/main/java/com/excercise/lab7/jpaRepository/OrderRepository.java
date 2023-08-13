package com.excercise.lab7.jpaRepository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.excercise.lab7.object.Order;
import com.excercise.lab7.object.User;

public interface OrderRepository extends CrudRepository<Order, Long> {
	List<Order> findByUserOrderByPlacedAtDesc(User user,Pageable pageable);
}