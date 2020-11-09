package cart.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import cart.models.Order;
import cart.utils.OrderUtil;

@SpringBootTest
@Transactional
@Rollback(true)
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void orderTest() {
        Order order = OrderUtil.createOrder(true, 1);
        order = orderService.order(order);
        assertEquals(order.getId(), orderService.getOrderById(order.getUserId()).get(0).getId());
    }

    @Test
    public void getOrderByIdTest() {
        Order order = OrderUtil.createOrder(true, 1);
        order = orderService.order(order);
        assertEquals(order.getId(), orderService.getOrderById(order.getUserId()).get(0).getId());
    }

}
