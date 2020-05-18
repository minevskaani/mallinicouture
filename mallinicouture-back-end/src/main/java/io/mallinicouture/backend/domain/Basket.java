package io.mallinicouture.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mc_basket")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne( mappedBy = "basket" )
    @JsonIgnore
    private Client client;

    @OneToMany(fetch = FetchType.EAGER,
    //@OneToMany(fetch = FetchType.LAZY,
            cascade = { CascadeType.ALL }
    )
    @JoinColumn(name = "mc_basket_id")
    private List<OrderedDress> dresses = new ArrayList<>();

    private float totalPrice;

    public void updateTotalPrice() {
        totalPrice = (float)dresses
                .stream()
                .mapToDouble(od -> od.getQuantity() * od.getDress().getPrice())
                .sum();
    }
}
