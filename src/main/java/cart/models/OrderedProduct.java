package cart.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

import lombok.AccessLevel;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Embeddable
public class OrderedProduct {
    @NonNull
    private Double price;
    private final long vendorId;
    private final String imageURL;
    @Lob
    private final String description;
    private final String productName;
    private final long productId;
    @NonNull
    private Integer quantity;

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        OrderedProduct product = (OrderedProduct) o;
        return product.productId == productId;
    }

    @Override
    public int hashCode() {
        return Long.valueOf(productId).hashCode();
    }
}
