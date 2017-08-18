package com.forestry.sopcompliance.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import java.util.HashMap;

import javax.inject.Inject;

/**
 * Created by FLASHY on 08-Jan-17.
 */

public class UserSession extends BaseObservable {
    // Shared Preferences reference
    SharedPreferences pref;

    // Editor reference for Shared preferences
    Editor editor;

    // Shared preferences mode
    int PRIVATE_MODE = 0;


    public static final String TAG_LOGIN_SESSION_DATA = "LOGIN_HISTORY";
    public static final String TAG_LOGIN_SESSION_CUISID = "CUIS_ID";
    public static final String TAG_LOGIN_SESSION_FULLNAME = "FULLNAME";
    public static final String TAG_LOGIN_SESSION_PASSWORD = "PASSWORD";
    public static final String TAG_LOGIN_SESSION_LOCATIONID = "LOCATION_ID";
    public static final String TAG_LOGIN_SESSION_LOCATION = "LOCATION";
    public static final String TAG_USER_AUTH= "USER_AUTH";


/*    // Shared preferences file name
    public static final String PREFER_NAME = "Reg";*/

    // All Shared Preferences Keys
    public static final String IS_USER_LOGIN = "IsUserLoggedIn";



    public UserSession(Context context){
        pref = context.getSharedPreferences(TAG_LOGIN_SESSION_DATA, PRIVATE_MODE);
        editor = pref.edit();
    }

    /*public UserSession(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }*/

    //Create login session
    public void createUserLoginSession(String cuisId, String fullname, String locationId){
        // Storing login value as TRUE
        editor.putBoolean(IS_USER_LOGIN, true);

        // Storing name in preferences
        editor.putString(TAG_LOGIN_SESSION_CUISID, cuisId);

        // Storing name in preferences
        editor.putString(TAG_LOGIN_SESSION_FULLNAME, fullname);

        // Storing locationId in preferences
        editor.putString(TAG_LOGIN_SESSION_LOCATIONID,  locationId);

        // commit changes
        editor.commit();
    }

    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){

        //Use hashmap to store user credentials
        HashMap<String, String> user = new HashMap<String, String>();

        // user name
        user.put(TAG_LOGIN_SESSION_CUISID, pref.getString(TAG_LOGIN_SESSION_CUISID, null));

        // user email id
        user.put(TAG_LOGIN_SESSION_FULLNAME, pref.getString(TAG_LOGIN_SESSION_FULLNAME, null));

        // user address id
        user.put(TAG_LOGIN_SESSION_PASSWORD, pref.getString(TAG_LOGIN_SESSION_PASSWORD, null));

        // user phone id
        user.put(TAG_LOGIN_SESSION_LOCATIONID, pref.getString(TAG_LOGIN_SESSION_LOCATIONID, null));

        // user image id
        user.put(TAG_LOGIN_SESSION_LOCATION, pref.getString(TAG_LOGIN_SESSION_LOCATION, null));

        // return user
        return user;
    }


    /**
     * Get stored session data
     * */
    @Bindable
    public String getUsername(){
        return pref.getString(TAG_LOGIN_SESSION_FULLNAME, null);
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){

        // Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();
    }


    // Check for login
    public boolean isUserLoggedIn(){
        return pref.getBoolean(IS_USER_LOGIN, false);
    }

    public void putUserAuth(String userAuth){
        editor.putString(TAG_USER_AUTH, userAuth);

        // commit changes
        editor.commit();
    }

    public String getUserAuth(){
        return pref.getString(TAG_USER_AUTH, null);
    }

}