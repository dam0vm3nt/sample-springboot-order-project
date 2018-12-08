package it.vb.sample.demo.controllers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.vb.sample.demo.dto.OrderDTO;
import it.vb.sample.demo.entities.Order;
import it.vb.sample.demo.entities.OrderLine;
import it.vb.sample.demo.entities.Product;
import it.vb.sample.demo.repositories.OrderRepository;
import it.vb.sample.demo.repositories.ProductRepository;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(method = RequestMethod.PUT)
    public Order createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = new Order();
        order.setLines(orderDTO.getLines().stream().map((l) -> {
            OrderLine line = new OrderLine();
            Product product = productRepository.findBySku(l.getSku());

            line.setQty(l.getQty());
            line.setPrice(product.getPrice());
            line.setProduct(product);
            
            return line;
        }).collect(Collectors.toList()));
        orderRepository.save(order);

        return order;
    }
}