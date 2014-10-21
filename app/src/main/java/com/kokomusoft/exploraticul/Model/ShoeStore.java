package com.kokomusoft.exploraticul.Model;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

/**
 * Created by Gabriel on 11/10/2014.
 */
public class ShoeStore implements Comparable<ShoeStore>{
    private Drawable image;
    private String name;
    private String address;
    private String phoneNumber;
    private String webSite;
    private String facebookLink;
    private ArrayList<String> openTime;
    private long shoeStoreId;
    private int id;





    public ShoeStore(Drawable image, String name, String address) {
        super();
        this.image = image;
        this.name = name;
        this.address = address;
    }

    public ShoeStore(Drawable image, String name, String address, long shoeStoreId) {
        super();
        this.image = image;
        this.name = name;
        this.address = address;
        this.shoeStoreId = shoeStoreId;
    }

    public ShoeStore(Drawable image, String name, String address, String phoneNumber,
                     String webSite, String facebookLink, ArrayList<String> openTime,
                     long shoeStoreId) {
        super();
        this.image = image;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.webSite = webSite;
        this.facebookLink = facebookLink;
        this.openTime = openTime;
        this.shoeStoreId = shoeStoreId;
    }

    public ShoeStore(int id, String name, String address, String phoneNumber, String webSite) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.webSite = webSite;
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

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public ArrayList<String> getOpenTime() {
        return openTime;
    }

    public void setOpenTime(ArrayList<String> openTime) {
        this.openTime = openTime;
    }

    public long getShoeStoreId() {
        return shoeStoreId;
    }

    public void setShoeStoreId(long shoeStoreId) {
        this.shoeStoreId = shoeStoreId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int[] imageId;

    public int[] getImageId() {
        return imageId;
    }

    public void setImageId(int[] imageId) {
        this.imageId = imageId;
    }


    @Override
    public int compareTo(ShoeStore shoeStore) {
        return name.compareToIgnoreCase(shoeStore.getName());
    }
}
