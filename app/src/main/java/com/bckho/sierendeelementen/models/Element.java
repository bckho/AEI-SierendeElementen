package com.bckho.sierendeelementen.models;

import java.io.Serializable;

public class Element implements Serializable {
    private String identification;
    private String title;
    private String geographicalLocation;
    private String artist;
    private String material;
    private String underLayer;
    private String description;
    private String imageUri;
    private double latitude;
    private double longitude;

    public Element(String identification,
                   String title,
                   String geographicalLocation,
                   String artist,
                   String material,
                   String underLayer,
                   String description,
                   String imageUri,
                   double latitude,
                   double longitude) {
        this.imageUri = imageUri;
        this.identification = identification;
        this.title = title;
        this.geographicalLocation = geographicalLocation;
        this.artist = artist;
        this.material = material;
        this.underLayer = underLayer;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGeographicalLocation(String geographicalLocation) {
        this.geographicalLocation = geographicalLocation;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setUnderLayer(String underLayer) {
        this.underLayer = underLayer;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getIdentification() {
        return identification;
    }

    public String getTitle() {
        return title;
    }

    public String getGeographicalLocation() {
        return geographicalLocation;
    }

    public String getArtist() {
        return artist;
    }

    public String getMaterial() {
        return material;
    }

    public String getUnderLayer() {
        return underLayer;
    }

    public String getDescription() {
        return description;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getImageUri() {
        return imageUri;
    }


}
