package com.nogravity.pixabaytest;

public class Model {

    String imgURL;
    int likes;
    int download;

    public Model(String imgURL, int likes, int download) {
        this.imgURL = imgURL;
        this.likes = likes;
        this.download = download;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDownload() {
        return download;
    }

    public void setDownload(int download) {
        this.download = download;
    }
}
