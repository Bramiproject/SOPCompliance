package com.forestry.sopcompliance.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.forestry.sopcompliance.di.ActivityScope;

import java.io.Serializable;

/**
 * Created by fimansya on 5/10/2017.
 */

@ActivityScope
public class User extends BaseObservable implements Serializable {

    public String cuisID;
    private String username;
    public String password;
    public String locationId;
    public String location;

    public User() {
    }

    @Bindable
    public String getCuisID() { return cuisID; }
    public void setCuisID(String cuisID) {
        this.cuisID = cuisID;
    }

    @Bindable
    public String getPassword() { return password; }
    public void setPassword(String password) {
        this.password = password;
    }


    @Bindable
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }


    @Bindable
    public String getLocationId() {
        return locationId;
    }
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    @Bindable
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
}
