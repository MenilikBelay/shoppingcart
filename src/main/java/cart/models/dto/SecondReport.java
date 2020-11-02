package cart.models.dto;

import java.io.Serializable;
import java.util.Date;

public class SecondReport implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Date order_date;
    private final long product_id;
    private final double total;
    private final long number_of_order;

    public SecondReport(Date orderDate, long productId, double total, long numberOfOrder) {
        order_date = orderDate;
        product_id = productId;
        this.total = total;
        number_of_order = numberOfOrder;
    }

    public long getNumber_of_order() {
        return number_of_order;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public long getProduct_id() {
        return product_id;
    }

    public double getTotal() {
        return total;
    }
}
