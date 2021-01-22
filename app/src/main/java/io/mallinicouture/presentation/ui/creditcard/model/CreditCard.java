package io.mallinicouture.presentation.ui.creditcard.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {
    private Long id;

    //@CreditCardNumber(ignoreNonDigitCharacters = true, message = "Not valid credit card number")
    private String cardNumber;

    // @JsonFormat(pattern = "mm|yyyy")
    // @NotNull(message = "Expire date is required")
    private Date expireDate;

    // @Range(min = 0, max = 9999, message = "CVV can be number between 0 and 9999")
    // @NotNull(message = "CVV is required")
    private Integer cvv;
}
