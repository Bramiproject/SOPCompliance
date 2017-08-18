package com.forestry.sopcompliance.data.model;

import android.databinding.BaseObservable;

import java.io.Serializable;

/**
 * Created by fimansya on 6/5/2017.
 */


public class PostApiResponse extends BaseObservable implements Serializable {
    String status;
    String messageCode;
    String message;


    public String getStatus() {
        return status;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public String getMessage() {
        return message;
    }
}
