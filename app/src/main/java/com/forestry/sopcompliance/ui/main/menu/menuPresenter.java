package com.forestry.sopcompliance.ui.main.menu;

import android.content.Context;

import com.forestry.sopcompliance.services.UserSession;
import com.forestry.sopcompliance.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by abrami on 8/16/2017.
 */

public class menuPresenter implements BasePresenter<menuView> {

    private menuView menuView;
    private UserSession userSession;
    private Context context;

    @Inject
    public menuPresenter(UserSession userSession){
        this.userSession=userSession;
    }

    public void sethomePresenter(){
        this.menuView.initUI();
    }

    @Override
    public void attachView(menuView view, Context ctx) {
        this.menuView = view;
        this.context = ctx;
    }

    @Override
    public void detachView() {
        menuView = null;
    }
}
