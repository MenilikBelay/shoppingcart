package cart.repositories;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import cart.models.Cart;
import cart.utils.CartUtil;

@SpringBootTest
@Transactional
@Rollback(true)
public class CartRepositoryTest {
    @Autowired
    CartRepository cartRepository;

    @Test
    public void insertionTest() {
        Cart cart = CartUtil.createCart(2);
        cart = cartRepository.save(cart);
        assertTrue(CartUtil.areTheSameCart(cart, cartRepository.findByUserId(cart.getUserId()).get()));
    }

    @Test
    public void findByUserIdTest() {
        Cart cart = CartUtil.createCart(1);
        cart = cartRepository.save(cart);
        assertTrue(CartUtil.areTheSameCart(cart, cartRepository.findByUserId(cart.getUserId()).get()));
    }

    @Test
    public void deleteByUserIdTest() {
        Cart cart = CartUtil.createCart(2);
        cartRepository.save(cart);
        assertTrue(CartUtil.areTheSameCart(cart, cartRepository.findByUserId(cart.getUserId()).get()));
        cartRepository.deleteByUserId(cart.getUserId());
        assertTrue(cartRepository.findByUserId(cart.getUserId()).isEmpty());
    }

}
