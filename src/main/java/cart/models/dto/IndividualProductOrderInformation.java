package cart.models.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class IndividualProductOrderInformation {
    private final double price;
    private final long productId;
    private final int quantity;

    public IndividualProductOrderInformation(double price, long productId, int quantity) {
        this.price = price;
        this.productId = productId;
        this.quantity = quantity;
    }
}
