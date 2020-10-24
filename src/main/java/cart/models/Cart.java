package cart.models;

import java.util.Set;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.AccessLevel;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
public class Cart {
    @Id
    @GeneratedValue
    private long id;
    @NonNull
    @OneToMany
    @JoinColumn(name = "cart_id")
    private Set<Product> products = new HashSet<>();
    private final long userId;

    public void addToCart(Product product) {
        products.add(product);
    }

    public void addToCart(Set<Product> products) {
        for (Product product : products) {
            addToCart(product);
        }
    }
}
