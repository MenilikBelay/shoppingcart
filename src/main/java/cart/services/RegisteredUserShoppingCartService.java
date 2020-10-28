package cart.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cart.models.Cart;
import cart.models.Product;
import cart.repositories.CartRepository;

@Service
public class RegisteredUserShoppingCartService implements ShoppingCartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart addToCart(long userId, Set<Product> products) {
        Cart cart = cartRepository.findByUserId(userId).orElseGet(() -> new Cart(userId));
        cart.addToCart(products);
        return cartRepository.save(cart);
    }

    @Override
    public Cart addToCart(long userId, Product product) {
        Cart cart = cartRepository.findByUserId(userId).orElseGet(() -> new Cart(userId));
        cart.addToCart(product);
        return cartRepository.save(cart);
    }

    @Override
    public Cart getCartByUserId(long userId) {
        return cartRepository.findByUserId(userId).orElseGet(() -> new Cart(userId));
    }

    @Override
    public void removeFromCart(long userId, Product product) {
        Optional<Cart> optionalCart = cartRepository.findByUserId(userId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.removeFromCart(product);
            cartRepository.save(cart);
        }
    }
}
