package io.mallinicouture.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "mc_advertisement")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Text can not be blank")
    private String text;

    @NotNull(message = "SubText is required")
    private String subText;

    @OneToOne
    @JoinColumn(name = "mc_image_id", nullable = false)
    private Image image;

}
