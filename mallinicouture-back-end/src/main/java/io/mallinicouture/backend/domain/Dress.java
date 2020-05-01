package io.mallinicouture.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@Table(name = "mc_dresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title should not be blank")
    private String title;

    private float price;

    @ElementCollection(targetClass = DressSize.class)
    @CollectionTable(name = "TBL_DRESS_SIZE", joinColumns = @JoinColumn(name = "DRESS_ID"))
    @Column(name = "DRESS_SIZE_ID")
    @Enumerated(EnumType.STRING)
    private Set<DressSize> availableSizes;

    // TODO:
    // @OneToOne(fetch = FetchType.LAZY)
    // @MapsId
    // private Image mainImage;

    // @OneToMany
    // private List<Image> images;

    // category

    public Dress(String title, Image mainImage, DressSize... sizes) {
        this.title = title;
        this.availableSizes = new HashSet<>(sizes.length);

        // this.mainImage = mainImage;
        this.availableSizes.addAll(Arrays.asList(sizes));
    }

}
