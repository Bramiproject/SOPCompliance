package com.forestry.sopcompliance.auth.model;

import android.databinding.BaseObservable;

import com.forestry.sopcompliance.di.ActivityScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fimansya on 7/18/2017.
 */
@ActivityScope
public class UserAuth extends BaseObservable implements Serializable {

    private List<String> buAuth = new ArrayList<>();
    private List<String> gisRegionAuth = new ArrayList<>();
    private List<companyAuth> companyAuth = new ArrayList<>();
    private List<districtAuth> districtAuth = new ArrayList<>();

    public UserAuth() {
        this.buAuth = new ArrayList<>();
        this.gisRegionAuth = new ArrayList<>();
        this.companyAuth = new ArrayList<companyAuth>();
        this.districtAuth = new ArrayList<districtAuth>();
    }

    public List<String> getBuAuth() {
        return buAuth;
    }

    public List<String> getGisRegionAuth() {
        return gisRegionAuth;
    }


    public List<com.forestry.sopcompliance.auth.model.companyAuth> getCompanyAuth() {
        return companyAuth;
    }

    public List<com.forestry.sopcompliance.auth.model.districtAuth> getDistrictAuth() {
        return districtAuth;
    }
}
