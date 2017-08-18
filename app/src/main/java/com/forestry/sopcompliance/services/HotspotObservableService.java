package com.forestry.sopcompliance.services;

import android.util.Log;

import com.forestry.sopcompliance.AppController;
import com.forestry.sopcompliance.data.model.Hotspot;
import com.forestry.sopcompliance.data.model.PostApiResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * Created by fimansya on 5/15/2017.
 */

public class HotspotObservableService  {

    private AppController app;

    @Inject
    public HotspotObservableService(AppController app){
        this.app = app;
    }

    public Observable<List<Hotspot>> getAllHotspot(String userId, String locationId) {
        HotspotServiceAPI api = HotspotServiceAPI.Factory.build(this.app);
        return api.getAllHotspot(userId, locationId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(app.getSubscribeScheduler());
    }



    public Observable<PostApiResponse> saveHots(List<Hotspot> hotspots, String uidLogin) {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        Map<String,RequestBody> images = null;

        for (Hotspot hotspot : hotspots) {
            try {
                ArrayList<String> finalOutputString = gson.fromJson(hotspot.getImages(), type);
                images = new HashMap<>();
                for (int j = 0 ; j < finalOutputString.size() ; j++) {
                    images.put("photos"+ j + "\"; filename=\"pic"+ j +".png",  RequestBody.create(MediaType.parse("multipart/form-data"), new File(finalOutputString.get(j))));
                }
            } catch (Exception e) {
                Log.e("Imagepath Err" , "Image Null");
            }
        }

        HotspotServiceAPI api = HotspotServiceAPI.POST.build(this.app);
        Map<String, RequestBody> finalImages = images;
        return Observable.from(hotspots)
                .concatMap(hotspot -> api.saveVerificationData(uidLogin, hotspot.getID(), hotspot.getFullname(), hotspot.getRemarks(), hotspot.getIsFireExist(), finalImages))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(app.getSubscribeScheduler());
    }


    public Observable<PostApiResponse> saveHotspots(String uidLogin, int hotspotId, String fullname, String remarks, int isfireexist,  String imagePaths) {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        Map<String,RequestBody> photos = null;

        try {
            ArrayList<String> finalOutputString = gson.fromJson(imagePaths, type);
            photos = new HashMap<>();
            for (int j = 0 ; j < finalOutputString.size() ; j++) {
                photos.put("photos" + j + "\"; filename=\"" + hotspotId + "_" + j + ".png",  RequestBody.create(MediaType.parse("multipart/form-data"), new File(finalOutputString.get(j))));
            }
        } catch (Exception e) {
            Log.e("Imagepath Err" , "Image Null");
        }

        HotspotServiceAPI api = HotspotServiceAPI.POST.build(this.app);
        return api.saveVerificationData(uidLogin, hotspotId, fullname, remarks, isfireexist, photos)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(app.getSubscribeScheduler());
    }

}
