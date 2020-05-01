package io.mallinicouture.backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    // TODO: private Client client;
    private float price;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date paymentDate;

    // TODO: private CreditCard creditCard;
    // TODO: order

    @PrePersist
    protected void onCreate() {
        paymentDate = new Date();
    }
}
