package io.mallinicouture.ui.gallery.model;

import io.mallinicouture.data.model.Dress;
import io.mallinicouture.data.model.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GalleryCardItem {

    private Long id;
    private String title;
    private Image image;
    private float price;

    public GalleryCardItem(Dress that) {
        this.id    = that.getId();
        this.title = that.getTitle();
        this.image = that.getMainImage();
        this.price = that.getPrice();
    }

}
