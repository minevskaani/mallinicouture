package io.mallinicouture.ui.gallery;

public class GalleryCardItem {
    private String title;
    private int image;
    private float price;

    public GalleryCardItem(String title, int image, float price) {
        this.title = title;
        this.image = image;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
