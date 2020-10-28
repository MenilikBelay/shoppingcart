package cart.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
public class Product {
    @Id
    @GeneratedValue
    private long id;
    @NonNull
    private Double price;
    private final long vendorId;
    private final String imageURL;
    private final String description;
    private final String productName;
    private final long productId;
    @NonNull
    private Integer quantity;

    public Product(double price, long vendorId, String imageURL, String description, String productName, int quantity,
            long productId) {
        this.price = price;
        this.vendorId = vendorId;
        this.imageURL = imageURL;
        this.description = description;
        this.productName = productName;
        this.quantity = quantity;
        this.productId = productId;
    }

    /**
     * Add quantity of the same product
     * 
     * @param quantity the number of products to add (only positive values)
     * @return the total amount of products after incrementing
     */
    public int add(int quantity) {
        if (quantity > 0)
            this.quantity += quantity;
        return this.quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        Product product = (Product) o;
        return product.productId == productId;
    }

    @Override
    public int hashCode() {
        return Long.valueOf(productId).hashCode();
    }
}
