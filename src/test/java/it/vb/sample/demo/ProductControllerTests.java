package it.vb.sample.demo;

import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import it.vb.sample.demo.controllers.ProductController;
import it.vb.sample.demo.entities.Product;
import it.vb.sample.demo.repositories.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerTests {

    @Autowired
    private ProductController productController;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional
    public void canCreateAProduct() {
        Product prod = newProduct("X001", "M", 30d, "Something with a dimension");
        assertThat(prod.getId(), Matchers.equalTo(0L));
        prod = productController.createProduct(prod);
        assertThat(prod.getId(), Matchers.greaterThan(0L));
    }

    @Test
    @Transactional
    public void canDeleteAProduct() {
        Product prod = newProduct("X002", "M", 30d, "Something with a dimension");
        prod = productController.createProduct(prod);
        productController.deleteProduct(prod.getId());
        Optional<Product> zombie = productRepository.findById(prod.getId());
        assertThat(zombie.isPresent(), Matchers.is(false));
    }

    @Test
    @Transactional
    public void canUpdateAProduct() {
        Product prod = newProduct("X003", "M", 30d, "Something with a dimension");
        prod = productController.createProduct(prod);
        Product prod2 = newProduct("X004", "M", 45d, "Description is changed");
        prod2 = productController.updateProduct(prod.getId(), prod2);

        assertThat(prod2.getId(), Matchers.equalTo(prod.getId()));
        assertThat(prod2.getName(), Matchers.equalTo("X004"));
    }

    @Test
    @Transactional
    public void canGetAllProducts() {
        final Product prod = productController
                .createProduct(newProduct("X003", "M", 30d, "Something with a dimension"));
        List<Product> allProds = productController.getProducts();

        assertThat(allProds, IsCollectionWithSize.hasSize(4));
        Optional<Product> op = allProds.stream().filter((p) -> p.getId() == prod.getId()).findFirst();
        assertThat(op.isPresent(), Matchers.is(true));
    }

    @Test
    @Transactional
    public void canGetOneProduct() {
        Product prod = productController.getProduct(1);        
        assertThat(prod.getName(), Matchers.is("AAA0001"));
    }

    private Product newProduct(String sku, String um, double price, String description) {
        Product prod = new Product();
        prod.setName(sku);
        prod.setUm(um);
        prod.setPrice(price);
        prod.setDescription(description);

        return prod;
    }
}