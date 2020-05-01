package io.mallinicouture.backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "mc_credit_card")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreditCardNumber(ignoreNonDigitCharacters = true, message = "Not valid credit card number")
    private String cardNumber;

    @JsonFormat(pattern = "mm/yyyyy")
    @NotNull(message = "Expire date is required")
    private Date expireDate;

    @Range(min = 0, max = 9999, message = "CVV can be number between 0 and 9999")
    @NotNull(message = "CVV is required")
    private Integer cvv;
}
