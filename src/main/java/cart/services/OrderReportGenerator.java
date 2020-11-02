package cart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cart.repositories.OrderRepository;

@Service
public class OrderReportGenerator implements ReportGenerator {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Object generate() {
        return orderRepository.customQueryOne();
    }
}
