package com.kokomusoft.exploraticul.Model;

import android.graphics.drawable.Drawable;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Gabriel on 01/11/2014.
 */
public class Restaurant implements Comparable<Restaurant>{
    private int id;
    private Drawable image;
    private String name;
    private String description;
    private String address;
    private String phoneNumber;
    private String website;
    private LatLng location;
    private ArrayList<String> openTime;
    private long restaurantId;

    public Restaurant(Drawable image, String description, String name, String address,
                      long restaurantId) {
        this.image = image;
        this.description = description;
        this.name = name;
        this.address = address;
        this.restaurantId = restaurantId;
    }

    public Restaurant(Drawable image, String description, String name, String address,
                      String phoneNumber, String website, ArrayList<String> openTime,
                      long restaurantId) {
        this.image = image;
        this.description = description;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.openTime = openTime;
        this.restaurantId = restaurantId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public ArrayList<String> getOpenTime() {
        return openTime;
    }

    public void setOpenTime(ArrayList<String> openTime) {
        this.openTime = openTime;
    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public int compareTo(Restaurant another) {
        return name.compareToIgnoreCase(another.getName());
    }
}
