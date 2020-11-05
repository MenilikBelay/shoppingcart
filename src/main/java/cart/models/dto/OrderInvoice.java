package cart.models.dto;

import java.util.List;

import cart.models.CardInformation;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class OrderInvoice {
    private final CardInformation cardInfo;
    private final long orderId;
    private final String userEmail;
    private final List<IndividualProductOrderInformation> payTo;

    public OrderInvoice(CardInformation cardInformation, long orderId, String userEmail,
            List<IndividualProductOrderInformation> payTo) {
        this.cardInfo = cardInformation;
        this.orderId = orderId;
        this.userEmail = userEmail;
        this.payTo = payTo;
    }

    public String toString() {
        return orderId + " " + userEmail;
    }
}
