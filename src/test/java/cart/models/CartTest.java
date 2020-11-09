package cart.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import cart.utils.CartUtil;

@SpringBootTest
public class CartTest {

    @Test
    public void addProductTest() {
        Cart cart = CartUtil.createCart(1);
        assertEquals(1, cart.getProducts().size());
        cart.addToCart(CartUtil.createProducts(2));
        assertEquals(2, cart.getProducts().size());
    }

    @Test
    public void remoteFromCartTest() {
        Cart cart = CartUtil.createCart(1);
        assertEquals(1, cart.getProducts().size());
        cart.removeFromCart(cart.getProducts().iterator().next());
        assertEquals(0, cart.getProducts().size());
    }
}
