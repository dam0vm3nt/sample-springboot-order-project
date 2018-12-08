package it.vb.sample.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import it.vb.sample.demo.entities.OrderLine;

@RepositoryRestResource
public interface OrderLineRepository extends CrudRepository<OrderLine, Long> {

}