package io.mallinicouture.backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mc_payment_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mc_client_id", nullable = false)
    @JsonIgnore
    private Client client;

    private float price;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date paymentDate;

    @CreditCardNumber(ignoreNonDigitCharacters = true, message = "Not valid credit card number")
    private String creditCard;

    @OneToOne
    @JoinColumn(name = "mc_order_id")
    @JsonIgnore
    private Order order;

    @PrePersist
    protected void onCreate() {
        paymentDate = new Date();
    }
}
