package cart.models;

import java.util.Set;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "cart_id")
    private Set<Product> products = new HashSet<>();
    private final long userId;

    public Cart(long userId) {
        this.userId = userId;
    }

    /**
     * Add product to cart
     * 
     * @param product The product to be added to the cart
     */
    public void addToCart(Product product) {
        removeFromCart(product); // remove if product exists
        products.add(product);
    }

    /**
     * Add a set of products to cart
     * 
     * @param products Set of products to be added
     */
    public void addToCart(Set<Product> products) {
        for (Product product : products) {
            addToCart(product);
        }
    }

    public void removeFromCart(Product product) {
        if (products.contains(product))
            products.remove(product);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        Cart cart = (Cart) o;
        return cart.userId == userId;
    }
}
