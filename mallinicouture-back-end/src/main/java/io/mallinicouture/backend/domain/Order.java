package io.mallinicouture.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "mc_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mc_client_id", nullable = false)
    @JsonIgnore
    private Client client;

    // TODO: dresses
    @OneToMany
    @JoinColumn(name = "order_id")
    private List<OrderedDress> dresses;

    private float totalPrice;

    // TODO:
    @OneToOne(mappedBy = "order",
            cascade = { CascadeType.PERSIST }
    )
    private PaymentDetails paymentDetails;

    @NotBlank(message = "Address is required")
    private String address;

}
