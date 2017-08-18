package com.forestry.sopcompliance.auth;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by fimansya on 5/8/2017.
 */

public class AuthInfo {
    private SharedPreferences authPref;
    private String UIDLogin;
    private String IDLocation;
    private String Username;
    private String Password;


    public AuthInfo(Context context) {
        authPref = context.getSharedPreferences("AUTH_INFO", Context.MODE_PRIVATE);
        loadFromCache();
    }


    public SharedPreferences getAuthPref() {
        return authPref;
    }

    public String getUIDLogin() {
        return UIDLogin;
    }

    public String getIDLocation() {
        return IDLocation;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }


    private void loadFromCache() {
        if (authPref != null) {
            this.UIDLogin = authPref.getString("UIDLogin", null);
            this.IDLocation = authPref.getString("IDLocation", null);
            this.Username = authPref.getString("Username", null);
            this.Password = authPref.getString("Password", null);
        } else {
            reset();
        }
    }

    private void reset() {
        this.UIDLogin = null;
        this.IDLocation = null;
        this.Username = null;
        this.Password = null;
    }


    private void saveToCache() {
        SharedPreferences.Editor editor = authPref.edit();
        editor.putString("UIDLogin", this.UIDLogin);
        editor.putString("IDLocation", this.IDLocation);
        editor.putString("Username", this.Username);
        editor.putString("Password", this.Password);
        editor.commit();
    }

    private void removeFromCache() {
        SharedPreferences.Editor editor = authPref.edit();
        editor.remove("UIDLogin");
        editor.remove("IDLocation");
        editor.remove("email");
        editor.remove("accessToken");
        editor.remove("refreshToken");
        editor.commit();
    }

    public void resetAndRemoveFromCache() {
        reset();
        removeFromCache();
    }

/*    public void updateFromLoginResponse(AuthLoginResponse response) {
        this.UIDLogin = response.UID_Login.trim();
        this.IDLocation = response.user_name.trim();
        this.Username = response.email.trim();
        this.Password = response.access_token.trim();
        saveToCache();
    }*/

}
