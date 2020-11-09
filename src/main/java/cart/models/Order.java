package cart.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Table(name = "user_order")
public class Order {
    @Id
    @GeneratedValue
    private long id;
    private Long userId; // can be null for guest user
    private final String userEmail; // needed for both registered and unregistered users
    private OrderStatus status = OrderStatus.PENDING; // by default it's pending
    @Temporal(TemporalType.DATE)
    private final Date timestamp = new Date();
    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "street1", column = @Column(name = "billing_street1")),
            @AttributeOverride(name = "street2", column = @Column(name = "billing_street2")),
            @AttributeOverride(name = "zip", column = @Column(name = "billing_zip")),
            @AttributeOverride(name = "city", column = @Column(name = "billing_city")),
            @AttributeOverride(name = "country", column = @Column(name = "billing_country")),
            @AttributeOverride(name = "state", column = @Column(name = "billing_state")) })
    private Address billingAddress;
    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "street1", column = @Column(name = "shipping_street1")),
            @AttributeOverride(name = "street2", column = @Column(name = "shipping_street2")),
            @AttributeOverride(name = "zip", column = @Column(name = "shipping_zip")),
            @AttributeOverride(name = "city", column = @Column(name = "shipping_city")),
            @AttributeOverride(name = "country", column = @Column(name = "shipping_country")),
            @AttributeOverride(name = "state", column = @Column(name = "shipping_state")) })
    private Address shippingAddress;
    @ElementCollection
    @CollectionTable(name = "ordered_products")
    private Set<OrderedProduct> products;
    @Embedded
    private CardInformation paymentCard;

    public static enum OrderStatus {
        PENDING, ORDERED, DELIVERED, CANCELED
    };

    public Order(Long userId, String userEmail, Address billingAddress, Address shippingAddress,
            Set<OrderedProduct> products, CardInformation paymentCard) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.products = products;
        this.paymentCard = paymentCard;
    }
}
