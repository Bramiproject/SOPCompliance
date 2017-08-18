package com.forestry.sopcompliance.di.component;

import android.app.Activity;

import com.forestry.sopcompliance.data.local.dao.HotspotVerificationTableDAO;
import com.forestry.sopcompliance.di.ActivityScope;
import com.forestry.sopcompliance.di.module.ActivityModule;
import com.forestry.sopcompliance.ui.main.home.homeActivity;
import com.forestry.sopcompliance.ui.main.homeFragment.homeFragmentActivity;
import com.forestry.sopcompliance.ui.main.login.loginActivity;
import com.forestry.sopcompliance.ui.main.splash.splashScreenActivity;

import dagger.Component;

/**
 * Created by abrami on 8/14/2017.
 */

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    //------------- PROVIDE
    Activity provideActivity();

    HotspotVerificationTableDAO provideTableHotspotVerification();

    //------------- INJECT
    void inject(loginActivity loginActivity);

    void inject(splashScreenActivity splashScreenActivity);

    void inject(homeActivity homeActivity);

    void inject(homeFragmentActivity homeFragmentActivity);
}
