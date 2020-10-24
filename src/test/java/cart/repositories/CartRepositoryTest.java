package cart.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import cart.models.Cart;

@SpringBootTest
public class CartRepositoryTest {
    @Autowired
    CartRepository cartRepository;

    @Test
    @Rollback(true)
    public void insertionTest() {
        // cartRepository.save(new Cart(10));
    }
}
