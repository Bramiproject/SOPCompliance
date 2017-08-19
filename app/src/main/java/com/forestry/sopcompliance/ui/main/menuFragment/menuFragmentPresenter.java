package com.forestry.sopcompliance.ui.main.menuFragment;

import android.content.Context;
import android.view.View;

import com.forestry.sopcompliance.services.UserSession;
import com.forestry.sopcompliance.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by abrami on 8/16/2017.
 */

public class menuFragmentPresenter implements BasePresenter<menuFragmentView> {


    private menuFragmentView menuFragmentView;
    private UserSession userSession;
    private Context context;

    @Inject
    public menuFragmentPresenter(UserSession userSession) {
        this.userSession = userSession;
    }

    public void setInitMineral(View v) {
        this.menuFragmentView.initMineral(v);
    }
    public void setInitGambut(View v) {
        this.menuFragmentView.initGambut(v);
    }

    public void sethomeFragmentPresenter() {
        this.menuFragmentView.refresh();
        this.menuFragmentView.willBeDisplayed();
        this.menuFragmentView.willBeHidden();
    }

    @Override
    public void attachView(menuFragmentView view, Context ctx) {
        this.menuFragmentView = view;
        this.context = ctx;
    }

    @Override
    public void detachView() {
        this.menuFragmentView = null;
    }
}
