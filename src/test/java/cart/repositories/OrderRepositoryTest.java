package cart.repositories;

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
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void findByUserIdTest() {
        Order order = OrderUtil.createOrder(true, 1);
        order = orderRepository.save(order);
        assertEquals(order.getId(), orderRepository.findByUserId(order.getUserId()).get(0).getId());
    }
}
