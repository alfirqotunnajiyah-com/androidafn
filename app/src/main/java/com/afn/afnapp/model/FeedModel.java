package com.afn.afnapp.model;

public class FeedModel {
    private int idImage,idResource;
    private String linkImage,title,caption;

    public void setIdResource(int idResource) {
        this.idResource = idResource;
    }

    public int getIdResource() {
        return idResource;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
