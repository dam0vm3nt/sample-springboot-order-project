package it.vb.sample.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import it.vb.sample.demo.entities.Product;

@RepositoryRestResource
public interface ProductRepository extends CrudRepository<Product, Long> {

	Product findByName(@Param("name") String name);

}