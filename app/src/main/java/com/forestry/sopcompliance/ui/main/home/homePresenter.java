package com.forestry.sopcompliance.ui.main.home;

import android.content.Context;

import com.forestry.sopcompliance.services.UserSession;
import com.forestry.sopcompliance.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by abrami on 8/16/2017.
 */

public class homePresenter implements BasePresenter<homeView> {

    private homeView homeView;
    private UserSession userSession;
    private Context context;

    @Inject
    public homePresenter(UserSession userSession){
        this.userSession=userSession;
    }

    public void sethomePresenter(){
        this.homeView.initUI();
    }

    @Override
    public void attachView(homeView view, Context ctx) {
        this.homeView = view;
        this.context = ctx;
    }

    @Override
    public void detachView() {
        homeView = null;
    }
}
