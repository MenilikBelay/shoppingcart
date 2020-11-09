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

    public Address(String street1, String street2, String zip, String city, String country, String state) {
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.zip = zip;
        this.country = country;
        this.state = state;
    }
}
