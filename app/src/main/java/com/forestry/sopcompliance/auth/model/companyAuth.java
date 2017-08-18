package com.forestry.sopcompliance.auth.model;

import android.databinding.BaseObservable;

import com.forestry.sopcompliance.di.ActivityScope;

import java.io.Serializable;

/**
 * Created by fimansya on 7/18/2017.
 */
@ActivityScope
public class companyAuth extends BaseObservable implements Serializable {
    private String companyCode;
    private String companyName;


    public companyAuth() {
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
