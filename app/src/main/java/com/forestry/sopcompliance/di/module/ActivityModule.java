package com.forestry.sopcompliance.di.module;

import android.app.Activity;

import com.forestry.sopcompliance.data.local.dao.HotspotVerificationTableDAO;
import com.forestry.sopcompliance.di.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by abrami on 8/14/2017.
 */
@Module
public class ActivityModule {
    final Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityScope
    public HotspotVerificationTableDAO provideTableHotspotVerification() {
        return new HotspotVerificationTableDAO(mActivity);
    }

    @Provides
    @ActivityScope
    Activity provideActivity() {
        return this.mActivity;
    }
}
