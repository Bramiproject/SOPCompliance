package com.forestry.sopcompliance.ui.main.homeFragment;

import android.content.Context;
import android.view.View;

import com.forestry.sopcompliance.services.UserSession;
import com.forestry.sopcompliance.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by abrami on 8/16/2017.
 */

public class homeFragmentPresenter implements BasePresenter<homeFragmentView> {


    private homeFragmentView homeFragmentView;
    private UserSession userSession;
    private Context context;

    @Inject
    public homeFragmentPresenter(UserSession userSession) {
        this.userSession = userSession;
    }

    public void sethomeFragmentPresenter() {
        this.homeFragmentView.initSettings();
        this.homeFragmentView.initList();
        this.homeFragmentView.refresh();
        this.homeFragmentView.willBeDisplayed();
        this.homeFragmentView.willBeHidden();
    }

    @Override
    public void attachView(homeFragmentView view, Context ctx) {
        this.homeFragmentView = view;
        this.context = ctx;
    }

    @Override
    public void detachView() {
        this.homeFragmentView = null;
    }
}
