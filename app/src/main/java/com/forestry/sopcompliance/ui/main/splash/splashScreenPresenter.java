package com.forestry.sopcompliance.ui.main.splash;

import android.content.Context;

import com.forestry.sopcompliance.AppController;
import com.forestry.sopcompliance.services.UserSession;
import com.forestry.sopcompliance.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by abrami on 8/15/2017.
 */

public class splashScreenPresenter implements BasePresenter<splashScreenView> {

    private splashScreenView splashScreenView;
    private UserSession userSession;
    private Context context;

    @Inject
    public splashScreenPresenter(UserSession userSession){
        this.userSession = userSession;
    }

    public void setSplashScreenViews(){
        this.splashScreenView.showContent();
    }

    @Override
    public void attachView(splashScreenView view, Context ctx) {
        splashScreenView = view;
        context = ctx;
    }

    @Override
    public void detachView() {
        splashScreenView = null;
    }
}
