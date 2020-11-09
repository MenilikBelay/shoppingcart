package cart.utils;

import java.util.HashSet;
import java.util.Set;

import cart.models.Address;
import cart.models.CardInformation;
import cart.models.Order;
import cart.models.OrderedProduct;

public class OrderUtil {

    public static Order createOrder(boolean withUserId, long numberOfProducts) {
        Long userId = withUserId ? 1L : null;
        Address address = new Address("1000 N 4th Street", "MR#26", "52557", "Fairfield", "USA", "Iowa");
        Set<OrderedProduct> products = new HashSet<>();
        for (int i = 0; i < numberOfProducts; i++) {
            products.add(new OrderedProduct(12.00, 1L, "image.com/" + i, "Description " + i, "Product Name" + i, 1L));
        }
        CardInformation paymentCard = new CardInformation("User", "11111111111", "11/01", "101");
        return new Order(userId, "testing@testing.com", address, address, products, paymentCard);
    }
}
