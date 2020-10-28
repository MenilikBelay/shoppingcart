package cart.services;

import java.util.Set;
import cart.models.Product;
import cart.models.Cart;

public interface ShoppingCartService {
    public Cart addToCart(long userId, Set<Product> products);

    public Cart addToCart(long userId, Product product);

    public Cart getCartByUserId(long userId);

    public void removeFromCart(long userId, Product product);
}
