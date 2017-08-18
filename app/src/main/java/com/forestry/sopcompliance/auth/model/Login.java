package com.forestry.sopcompliance.auth.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.forestry.sopcompliance.di.ActivityScope;

import java.io.Serializable;

/**
 * Created by fimansya on 5/9/2017.
 */

@ActivityScope
public class Login extends BaseObservable implements Serializable {
    private String cuisID;
    private String password;
    private String location;

    public Login() {
    }

    @Bindable
    public String getCuisID() {
        return cuisID;
    }

    public void setCuisID(String cuisID) {
        this.cuisID = cuisID;
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
