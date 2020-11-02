package cart.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cart.models.Order;
import cart.models.dto.FirstReport;
import cart.models.dto.SecondReport;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByUserId(long userId);

    @Query("SELECT new cart.models.dto.FirstReport(o.timestamp, p.productId, sum(p.quantity)) "
            + "FROM Order o join o.products p GROUP BY o.timestamp, p.productId")
    List<FirstReport> customQueryOne();

    @Query("SELECT new cart.models.dto.SecondReport(o.timestamp, p.productId, sum(p.price * p.quantity), count(o.id)) "
            + "FROM Order o join o.products p GROUP BY o.timestamp, p.productId")
    List<SecondReport> customQueryTwo();
}
