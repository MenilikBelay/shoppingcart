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
    private final long vendorID;
    private final String imageURL;
    private final String description;
    private final String productName;
    @NonNull
    private Integer quantity;

    public int add(int quantity) {
        this.quantity += quantity;
        return this.quantity;
    }

    public int add() {
        return add(1);
    }
}
