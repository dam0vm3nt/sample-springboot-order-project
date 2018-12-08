package it.vb.sample.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import it.vb.sample.demo.entities.Order;

@RepositoryRestResource
public interface OrderRepository extends CrudRepository<Order, Long> {
}