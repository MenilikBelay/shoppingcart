package cart.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Set;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cart.models.Cart;
import cart.models.Product;
import cart.utils.CartUtil;

@SpringBootTest
@Transactional
@Rollback(true)
public class RegisteredUserShoppingCartServiceTest {

    @Autowired
    ShoppingCartService shoppingCartService;

    @Test
    public void addProductToCartTest() {

    }

    @Test
    public void addProductsToCartTest() {
        Set<Product> products = CartUtil.createProducts(2);
        Cart cart = shoppingCartService.addToCart(1, products);
        assertEquals(products, cart.getProducts());
    }

    @Test
    public void getCartByUserIdTest() {
        Set<Product> products = CartUtil.createProducts(1);
        Cart cart = shoppingCartService.addToCart(1, products);
        assertEquals(cart, shoppingCartService.getCartByUserId(cart.getUserId()));
    }

    @Test
    public void removeFromCartTest() {
        Set<Product> products = CartUtil.createProducts(10);
        Cart cart = shoppingCartService.addToCart(1, products);
        Product product = new Product(12.0, 1, "imageURL/", "description ", "Product ", 1, 1);
        assertTrue(shoppingCartService.getCartByUserId(cart.getUserId()).getProducts().contains(product));
        shoppingCartService.removeFromCart(cart.getUserId(), product);
        assertFalse(shoppingCartService.getCartByUserId(cart.getUserId()).getProducts().contains(product));
    }

    @Test
    public void clearCartTest() {
        Set<Product> products = CartUtil.createProducts(10);
        Cart cart = shoppingCartService.addToCart(1, products);
        assertFalse(shoppingCartService.getCartByUserId(cart.getUserId()).getProducts().isEmpty());
        shoppingCartService.clearCart(cart.getUserId());
        assertTrue(shoppingCartService.getCartByUserId(cart.getUserId()).getProducts().isEmpty());
    }
}
