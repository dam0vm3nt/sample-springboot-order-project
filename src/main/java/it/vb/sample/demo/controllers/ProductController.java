package it.vb.sample.demo.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.vb.sample.demo.entities.Product;
import it.vb.sample.demo.repositories.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @RequestMapping(method = RequestMethod.PUT)
    @Transactional
    public Product createProduct(@RequestBody Product newProduct) {
        productRepository.save(newProduct);
        return newProduct;
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    @Transactional
    public void deleteProduct(@PathVariable("id") long id) {
        productRepository.deleteById(id);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    @Transactional
    public Product getProduct(@PathVariable("id") long id) {
        return productRepository.findById(id).get();
    }

    @RequestMapping(path = "{id}", method = RequestMethod.POST)
    @Transactional
    public Product updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
        Product toUpdate = productRepository.findById(id).get();
        toUpdate.setName(product.getName());
        toUpdate.setDescription(product.getDescription());
        toUpdate.setPrice(product.getPrice());
        toUpdate.setUm(product.getUm());
        return toUpdate;
    }

    @RequestMapping(method = RequestMethod.GET)
    @Transactional
    public List<Product> getProducts() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

}