package it.vb.sample.demo.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.vb.sample.demo.dto.FindOrderCriteriaDTO;
import it.vb.sample.demo.dto.OrderDTO;
import it.vb.sample.demo.dto.OrderLineDTO;
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
        order.setBuyerEmail(orderDTO.getBuyerEmail());
        order.setDate(orderDTO.getDate());
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

    @RequestMapping(path = "{id}/update", method = RequestMethod.GET)
    public void updatePrices(@PathVariable("id") long id) {
        Order order = orderRepository.findById(id).get();
        order.getLines().forEach(l -> {
            l.setPrice(l.getProduct().getPrice());
        });
    }

    @RequestMapping(method = RequestMethod.POST)
    public List<OrderDTO> findOrdersInRange(@RequestBody FindOrderCriteriaDTO criteria) {
        List<Order> orders = orderRepository.findByDateBetween(criteria.getDateFrom(), criteria.getDateTo());

        return orders.stream().map((o) -> {
            OrderDTO dto = new OrderDTO();
            dto.setId(o.getId());
            dto.setBuyerEmail(o.getBuyerEmail());
            dto.setDate(o.getDate());
            dto.setLines(o.getLines().stream().map((l) -> {
                OrderLineDTO ldto = new OrderLineDTO();
                ldto.setQty(l.getQty());
                ldto.setSku(l.getProduct().getSku());
                return ldto;
            }).collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());
    }

}