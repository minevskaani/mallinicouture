package io.mallinicouture.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@Table(name = "mc_dress")
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mc_image_id", nullable = false)
    private Image mainImage;

    @OneToMany(mappedBy = "dress")
    private List<Image> images;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mc_category_id", nullable = false)
    private Category category;

    public Dress(String title, Image mainImage, float price, DressSize... sizes) {
        this.title = title;
        this.mainImage = mainImage;
        this.availableSizes = new HashSet<>(sizes.length);

        // this.mainImage = mainImage;
        this.availableSizes.addAll(Arrays.asList(sizes));
        this.price = price;
    }

}
