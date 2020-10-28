package cart.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;
import java.util.HashSet;

import cart.models.Product;

@SpringBootTest
public class RegisteredUserShoppingCartServiceTest {

    @Autowired
    ShoppingCartService shoppingCartService;

    @Test
    public void addProductToCart() {

    }

    @Test
    public void addProductsToCart() {
        Set<Product> products = new HashSet<>();
        products.add(new Product(12.00, 1, "HELLO", "HI", "VIM", 3, 2));
        shoppingCartService.addToCart(1, products);
    }
}
