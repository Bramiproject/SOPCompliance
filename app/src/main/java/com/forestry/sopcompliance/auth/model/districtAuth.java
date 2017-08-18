package com.forestry.sopcompliance.auth.model;

import android.databinding.BaseObservable;

import com.forestry.sopcompliance.di.ActivityScope;

import java.io.Serializable;

/**
 * Created by fimansya on 7/18/2017.
 */
@ActivityScope
public class districtAuth extends BaseObservable implements Serializable {

    private String districtCode;
    private String districtName;

    public districtAuth() {
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
