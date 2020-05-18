package io.mallinicouture.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "mc_ordered_dress")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderedDress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "dress_id", nullable = false)
    private Dress dress;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "dress_size") // Size is reserved in oracle
    private DressSize size;

    @Min(value = 1, message = "Quantity can not be less than 1")
    private int quantity;
}
