package io.mallinicouture.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mc_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title should not be blank")
    private String title;

    @OneToOne
    @JoinColumn(name = "mc_image_id", nullable = false)
    private Image image;

    @OneToMany(mappedBy = "category", cascade = { CascadeType.REMOVE })
    @JsonIgnore
    private List<Dress> dresses;

}
