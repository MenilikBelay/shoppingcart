package cart.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
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
     * 
     * @param order
     * @return
     */
    private OrderInvoice generateInvoice(Order order) {
        List<IndividualProductOrderInformation> orderInfo = new ArrayList<>();
        for (OrderedProduct product : order.getProducts()) {
            orderInfo.add(new IndividualProductOrderInformation(product.getPrice(), product.getProductId(),
                    product.getQuantity()));
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

    public void cancelOrderByOrderId(long orderId) {
        updateOrderStatus(orderId, Order.OrderStatus.CANCELED);
    }

    public void orderSuccessful(long orderId) {
        updateOrderStatus(orderId, Order.OrderStatus.ORDERED);
    }

    private void updateOrderStatus(long orderId, Order.OrderStatus status) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            // change status
            Order order = optionalOrder.get();
            order.setStatus(status);
            orderRepository.save(order);
        }
    }

    @KafkaListener(id = "${cart.services.shopping-cart-and-order-service-order-succeed-listener}", topics = "${cart.services.Order-Succeed}")
    public void orderSuccessfulListener(Long orderId) {
        System.out.println("Received Successful Order Id: " + orderId);
        orderSuccessful(orderId);
    }

    @KafkaListener(id = "${cart.services.shopping-cart-and-order-service-failed-qty-deduction-listener}", topics = {
            "${cart.services.Fail-Qty-Deduction}", "${cart.services.Fail-Payment}" })
    public void orderCanceledListener(Long orderId) {
        System.out.println("Received Failed Order Id: " + orderId);
        cancelOrderByOrderId(orderId);
    }
}
