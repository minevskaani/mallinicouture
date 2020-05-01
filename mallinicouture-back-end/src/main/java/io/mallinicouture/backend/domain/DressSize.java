package io.mallinicouture.backend.domain;

public enum DressSize {
    XXS,
    XS,
    S,
    M,
    L,
    XL,
    XXL,
    XXXL;

    private String size;

    DressSize() {
        this.size = toString();
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
