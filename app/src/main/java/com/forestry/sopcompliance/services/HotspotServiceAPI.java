package com.forestry.sopcompliance.services;

import com.forestry.sopcompliance.AppController;
import com.forestry.sopcompliance.data.model.Hotspot;
import com.forestry.sopcompliance.data.model.PostApiResponse;
import com.forestry.sopcompliance.utils.ApiBuilder;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by fimansya on 5/15/2017.
 */

public interface HotspotServiceAPI {


    @GET("/api/Hotspot")
    Observable<List<Hotspot>> getHotspot();


    @GET("/api/Hotspot")
    Observable<List<Hotspot>> getAllHotspot(@Query("userId") String userId, @Query("locationId") String locationId);

    @Multipart
    @retrofit2.http.POST("api/hotspot/")
    Observable<PostApiResponse> saveVerificationData(
            @Part("UIDLogin") String uidLogin,
            @Part("ID") int hotspotId,
            @Part("FullName") String fullname,
            @Part("Remarks") String remarks,
            @Part("IsFireExist") int isFireExist,
            @PartMap Map<String, RequestBody> imgs1
    );



    class Factory {

        public static HotspotServiceAPI build(AppController app) {
            Retrofit client = ApiBuilder.buildHotspotApiClient(app);
            return client.create(HotspotServiceAPI.class);
        }
    }

    class POST {

        public static HotspotServiceAPI build(AppController app) {
            Retrofit client = ApiBuilder.buildHotspotPostApiClient(app);
            return client.create(HotspotServiceAPI.class);
        }
    }
}
