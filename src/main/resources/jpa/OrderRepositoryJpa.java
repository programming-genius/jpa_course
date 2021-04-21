package it.jpa.course.repositories;

import org.springframework.data.repository.CrudRepository;

import it.jpa.course.entities.Order;

public interface OrderRepositoryJpa extends CrudRepository<Order,Long>, OrderRepositoryCustomJpa {

}
