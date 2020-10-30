package cart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cart.models.Cart;
import cart.models.Product;
import cart.services.ShoppingCartService;

@RestController
@CrossOrigin
@RequestMapping("/api/cart/{userId}")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @PostMapping
    public Cart addToCart(@PathVariable Long userId, @RequestBody Product products) {
        return shoppingCartService.addToCart(userId, products);
    }

    @GetMapping
    public Cart getCartByUserId(@PathVariable Long userId) {
        return shoppingCartService.getCartByUserId(userId);
    }

    @DeleteMapping
    public void removeFromCart(@PathVariable Long userId, @RequestBody Product product) {
        shoppingCartService.removeFromCart(userId, product);
    }
}
