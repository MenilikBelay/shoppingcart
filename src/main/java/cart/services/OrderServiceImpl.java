package cart.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cart.models.Order;
import cart.repositories.OrderRepository;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Override
    public Order order(Order order) {
        order.setStatus(Order.OrderStatus.PENDING); // make sure it's pending on order
        Long userId = order.getUserId();
        if (userId != null) {
            // registered user, clear cart
            shoppingCartService.clearCart(userId);
        }
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrderById(long userId) {
        if (userId <= 0)
            return null; // it's for registered users only
        return orderRepository.findByUserId(userId);
    }
}
