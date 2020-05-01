package io.mallinicouture.ui.home.advertisement;

public class Advertisement {

    private int imageId;
    private String title;
    private String information;

    public Advertisement() {
    }

    public Advertisement(int imageId, String title, String information) {
        this.imageId = imageId;
        this.title = title;
        this.information = information;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
