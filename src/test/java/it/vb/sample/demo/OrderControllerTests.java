package it.vb.sample.demo;

import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.hamcrest.Matchers;
import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import it.vb.sample.demo.controllers.OrderController;
import it.vb.sample.demo.dto.FindOrderCriteriaDTO;
import it.vb.sample.demo.dto.OrderDTO;
import it.vb.sample.demo.dto.OrderLineDTO;
import it.vb.sample.demo.repositories.OrderRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderControllerTests {

    @Autowired
    private OrderController orderController;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @Transactional
    public void canCreateAnOrder() {
        long id = createAnOrder();
        assertThat(id, Matchers.greaterThan(0L));

        // Leave it cleaned Remove the order
        orderRepository.deleteById(id);
    }

    @Test
    @Transactional
    public void canFindOrders() {
        Calendar cal = Calendar.getInstance();
        long id = createAnOrder();
        FindOrderCriteriaDTO criteriaDTO = new FindOrderCriteriaDTO();
        cal.set(118, 4, 1, 12, 0);
        criteriaDTO.setDateFrom(cal.getTime());
        cal.set(118, 6, 1, 12, 0);
        criteriaDTO.setDateTo(cal.getTime());

        List<OrderDTO> orders = orderController.findOrdersInRange(criteriaDTO);

        assertThat(orders, IsCollectionWithSize.hasSize(1));
        assertThat(orders.get(0).getId(), IsEqual.equalTo(id));

        orderRepository.deleteById(id);
    }

    private long createAnOrder() {
        OrderDTO order = new OrderDTO();
        Calendar cal = Calendar.getInstance();
        cal.set(118, 5, 1, 12, 0);
        order.setDate(cal.getTime());
        order.setBuyerEmail("a@b.it");
        OrderLineDTO line1 = new OrderLineDTO();
        line1.setSku("AAA0001");
        line1.setQty(10.0);
        order.setLines(Arrays.asList(line1));
        long id = orderController.createOrder(order);
        return id;
    }
}