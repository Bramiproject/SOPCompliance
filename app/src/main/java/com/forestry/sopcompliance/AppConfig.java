package com.forestry.sopcompliance;


public class AppConfig {

   private String apiBaseUrl = "http://gkmweb49.forestree.com/";


    public String getApiBaseUrl() {
        return this.apiBaseUrl;
    }

    public void setApiBaseUrl(String apiBaseUrl) {
        this.apiBaseUrl = apiBaseUrl;
    }

    public String getApiResourceUrl() {
        return apiBaseUrl + "resources/";
    }

}