package com.kokomusoft.exploraticul.Model;

import android.graphics.drawable.Drawable;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Gabriel on 30/10/2014.
 */
public class HandCraftStore implements Comparable<HandCraftStore> {
    private int id;
    private Drawable image;
    private String name;
    private String address;
    private String phoneNumber;
    private String website;
    private LatLng location;
    private ArrayList<String> openTime;
    private long handCraftStoreId;

    public HandCraftStore(int id, Drawable image, String name, String address) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.address = address;
    }

    public HandCraftStore(Drawable image, String name, String address, long handCraftStoreId) {
        this.image = image;
        this.name = name;
        this.address = address;
        this.handCraftStoreId = handCraftStoreId;
    }

    public HandCraftStore(Drawable image, String name, String address, String phoneNumber,
                          long handCraftStoreId, ArrayList<String> openTime) {
        this.image = image;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.handCraftStoreId = handCraftStoreId;
        this.openTime = openTime;
    }

    public HandCraftStore(Drawable image, String name, String address,
                          String phoneNumber, String website, ArrayList<String> openTime,
                          long handCraftStoreId) {
        this.image = image;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.openTime = openTime;
        this.handCraftStoreId = handCraftStoreId;
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

    public long getHandCraftStoreId() {
        return handCraftStoreId;
    }

    public void setHandCraftStoreId(long handCraftStoreId) {
        this.handCraftStoreId = handCraftStoreId;
    }

    @Override
    public int compareTo(HandCraftStore handCraftStore) {
        return name.compareToIgnoreCase(handCraftStore.getName());
    }
}
