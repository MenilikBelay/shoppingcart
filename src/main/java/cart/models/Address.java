package cart.models;

import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Address {
    private final String street1;
    private String street2;
    private final String zip;
    private final String city;
    private final String country;
    private final String state;
}
