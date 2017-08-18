package com.forestry.sopcompliance.auth;

import com.forestry.sopcompliance.AppController;
import com.forestry.sopcompliance.auth.model.UserAuth;
import com.forestry.sopcompliance.data.remote.request.LoginRequestEnvelope;
import com.forestry.sopcompliance.data.remote.response.LoginResponseEnvelope;
import com.forestry.sopcompliance.utils.ApiBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by fimansya on 5/8/2017.
 */

public interface AuthService {

    @GET("/api/regiondistrictauthorization/")
    Observable<UserAuth> getAllAuthData(@Query("userId") String userId, @Query("locationId") String locationId);


    ///----------SOAP Services
    @Headers({
            "Content-Type: text/xml",
            "Accept-Charset: utf-8"
    })
    @POST("/loginservice2.asmx")
    Call<LoginResponseEnvelope> requestLogin(@Body LoginRequestEnvelope body);


    class SOAP {

        public static AuthService createLoginService() {
            Retrofit client = ApiBuilder.buildAuthApiServiceClient();
            return client.create(AuthService.class);
        }
    }

    class DISTRICT {

        public static AuthService createAuthService(AppController appController) {
            Retrofit client = ApiBuilder.buildDistrictAuthApiServiceClient(appController);
            return client.create(AuthService.class);
        }
    }

}
