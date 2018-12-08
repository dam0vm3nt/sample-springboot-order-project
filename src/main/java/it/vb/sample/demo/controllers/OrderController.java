package it.vb.sample.demo.controllers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.vb.sample.demo.dto.OrderDTO;
import it.vb.sample.demo.entities.Order;
import it.vb.sample.demo.entities.OrderLine;
import it.vb.sample.demo.entities.Product;
import it.vb.sample.demo.repositories.OrderLineRepository;
import it.vb.sample.demo.repositories.OrderRepository;
import it.vb.sample.demo.repositories.ProductRepository;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @RequestMapping(method = RequestMethod.PUT)
    public Long createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = new Order();
        orderRepository.save(order);

        order.setLines(orderDTO.getLines().stream().map((l) -> {
            OrderLine line = new OrderLine();
            Product product = productRepository.findBySku(l.getSku());

            line.setQty(l.getQty());
            line.setPrice(product.getPrice());
            line.setProduct(product);
            line.setOrder(order);
            orderLineRepository.save(line);

            return line;
        }).collect(Collectors.toList()));
        

        return order.getId();
    }

    @RequestMapping(path="{id}/update",method = RequestMethod.GET)
    public void updatePrices(@PathVariable("id") long id) {
        Order order = orderRepository.findById(id).get();
        order.getLines().forEach(l -> {
            l.setPrice(l.getProduct().getPrice());
        });
    }




}