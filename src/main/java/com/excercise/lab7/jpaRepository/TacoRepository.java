package com.excercise.lab7.jpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.excercise.lab7.object.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {
	Page<Taco> findAll(Pageable pageable);

}
