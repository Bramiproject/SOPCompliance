package com.forestry.sopcompliance.services;

import com.forestry.sopcompliance.AppController;
import com.forestry.sopcompliance.auth.AuthService;
import com.forestry.sopcompliance.auth.model.UserAuth;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by fimansya on 7/18/2017.
 */

public class AuthObservableService {

    private AppController app;

    @Inject
    public AuthObservableService(AppController app){
        this.app = app;
    }


    public Observable<UserAuth> getAllDataAuth(String userId, String locationId) {
        AuthService api = AuthService.DISTRICT.createAuthService(this.app);
        return api.getAllAuthData(userId, locationId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(app.getSubscribeScheduler());
    }

}
