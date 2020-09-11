package io.mallinicouture.data.model;

import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dress {

    private Long id;
    private String title;
    private float price;
    private Set<DressSize> availableSizes;
    private Image mainImage;
    private List<Image> images;
    private Category category;

    public enum DressSize {
        XXS,
        XS,
        S,
        M,
        L,
        XL,
        XXL,
        XXXL
    }
}
