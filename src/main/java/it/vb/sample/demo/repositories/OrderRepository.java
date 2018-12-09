package it.vb.sample.demo.repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import it.vb.sample.demo.entities.Order;

@RepositoryRestResource
public interface OrderRepository extends CrudRepository<Order, Long> {
	List<Order> findByDateBetween(Instant from, Instant to);
}