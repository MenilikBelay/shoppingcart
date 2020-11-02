package cart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cart.repositories.OrderRepository;

@Service
public class OrderQuantityReportGenerator implements ReportGenerator {

    @Autowired
    private OrderRepository orderRepository;

    public Object generate() {
        return orderRepository.customQueryTwo();
    }
}
