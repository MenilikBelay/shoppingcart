package cart.models.dto;

import java.io.Serializable;
import java.util.Date;

public class FirstReport implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Date order_date;
    private final long product_id;
    private final long total_qty;

    public FirstReport(Date orderDate, long productId, long totalQty) {
        order_date = orderDate;
        product_id = productId;
        total_qty = totalQty;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public long getProduct_id() {
        return product_id;
    }

    public long getTotal_qty() {
        return total_qty;
    }
}
