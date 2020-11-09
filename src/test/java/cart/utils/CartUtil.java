package cart.utils;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

import cart.models.Product;
import cart.models.Cart;

public class CartUtil {

    public static boolean areTheSameCart(Cart cart1, Cart cart2) {
        return cart1.equals(cart2) && cart1.getProducts().equals(cart2.getProducts()) && cart1.getId() == cart2.getId();
    }

    public static List<Cart> createCarts(int numberOfCarts, int numberOfProductsPerCart) {
        List<Cart> carts = new ArrayList<>();
        for (int i = 0; i < numberOfCarts; i++) {
            Cart cart = new Cart(i);
            cart.addToCart(createProducts(numberOfProductsPerCart));
            carts.add(cart);
        }
        return carts;
    }

    /**
     * 
     * @param numberOfProductsPerCart
     * @return
     */
    public static Cart createCart(int numberOfProductsPerCart) {
        Cart cart = new Cart(1);
        cart.addToCart(createProducts(numberOfProductsPerCart));
        return cart;
    }

    public static Set<Product> createProducts(int numberOfProducts) {
        Set<Product> products = new HashSet<>();
        for (int i = 0; i < numberOfProducts; i++) {
            products.add(new Product((i + 1) * 10.0, i, "imageURL/" + i, "description " + i, "Product " + i, i + 1, i));
        }
        return products;
    }
}
