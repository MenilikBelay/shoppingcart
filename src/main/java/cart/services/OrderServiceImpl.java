package cart.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import cart.models.Order;
import cart.models.OrderedProduct;
import cart.models.dto.IndividualProductOrderInformation;
import cart.models.dto.OrderInvoice;
import cart.repositories.OrderRepository;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private KafkaTemplate<Object, Object> producer;

    @Value("${cart.services.order-created-topic}")
    private String orderCreatedTopic;

    @Override
    public Order order(Order order) {
        order.setStatus(Order.OrderStatus.PENDING); // make sure it's pending on order
        Long userId = order.getUserId();
        if (userId != null) {
            // registered user, clear cart
            shoppingCartService.clearCart(userId);
        }
        order = orderRepository.save(order);
        updateConsumers(order);
        return order;
    }

    @Override
    public List<Order> getOrderById(long userId) {
        if (userId <= 0)
            return null; // it's for registered users only
        return orderRepository.findByUserId(userId);
    }

    /**
     * Generate the invoice from the given order
     * @param order
     * @return
     */
    private OrderInvoice generateInvoice(Order order) {
        List<IndividualProductOrderInformation> orderInfo = new ArrayList<>();
        for (OrderedProduct product : order.getProducts()) {
            orderInfo.add(new IndividualProductOrderInformation(product.getPrice(), product.getProductId(), product.getQuantity()));
        }
        return new OrderInvoice(order.getPaymentCard(), order.getId(), order.getUserEmail(), orderInfo);
    }

    /**
     * Update any consumer waiting for invoices (async)
     * 
     * @param order
     */
    private void updateConsumers(Order order) {
        producer.send(orderCreatedTopic, generateInvoice(order));
    }
}
