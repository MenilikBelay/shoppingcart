package cart.services;

import cart.models.Order;

import java.util.List;

public interface OrderService {
    Order order(Order order);

    List<Order> getOrderById(long userId);
}
