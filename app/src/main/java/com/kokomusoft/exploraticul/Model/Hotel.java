package com.kokomusoft.exploraticul.Model;

import android.graphics.drawable.Drawable;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Gabriel on 02/11/2014.
 */
public class Hotel implements Comparable<Hotel>{
    private int id;
    private Drawable image;
    private String name;
    private double basePrice;
    private String address;
    private String phoneNumber;
    private String website;
    private LatLng location;
    private ArrayList<String> services;
    private long hotelId;

    public Hotel(Drawable image, double basePrice, String name, String address, long hotelId) {
        this.image = image;
        this.basePrice = basePrice;
        this.name = name;
        this.address = address;
        this.hotelId = hotelId;
    }

    public Hotel(Drawable image, double basePrice, String name, String address, String phoneNumber,
                 String website, long hotelId) {
        this.image = image;
        this.basePrice = basePrice;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.hotelId = hotelId;
        this.services = services;
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

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
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

    public ArrayList<String> getServices() {
        return services;
    }

    public void setServices(ArrayList<String> services) {
        this.services = services;
    }

    public long getHotelId() {
        return hotelId;
    }

    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public int compareTo(Hotel another) {
        return name.compareToIgnoreCase(another.getName());
    }
}
