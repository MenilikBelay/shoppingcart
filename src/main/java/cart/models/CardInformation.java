package cart.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class CardInformation {
    @Column(name = "cardHolderName")
    private final String name;
    @CreditCardNumber(message = "Not a valid credit card number")
    private final String cardNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
    private final String expDate;
    @Digits(integer = 3, fraction = 0, message = "invalid CVV")
    private final String pin; // card validation value
}
