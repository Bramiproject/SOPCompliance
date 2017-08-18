package com.forestry.sopcompliance;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.forestry.sopcompliance.di.component.ApplicationComponent;
import com.forestry.sopcompliance.di.component.DaggerApplicationComponent;
import com.forestry.sopcompliance.di.module.ApplicationModule;
import com.forestry.sopcompliance.events.SyncEvent;
import com.forestry.sopcompliance.services.UserSession;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by abrami on 8/9/2017.
 */

public class AppController extends Application {

    @Inject
    AppConfig appConfig;

    @Inject
    EventBus mEventBus;

    @Inject
    UserSession userSession;

    private Scheduler mScheduler;
    private ApplicationComponent mApplicationComponent;

    public AppController() {
    }

    public static AppController get(Context context) {
        return (AppController) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        mApplicationComponent.inject(this);
        mEventBus.register(this);

        /*Speech.init(this, getPackageName());
        Logger.setLogLevel(Logger.LogLevel.DEBUG);*/
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public Scheduler getSubscribeScheduler() {
        if (mScheduler == null) {
            mScheduler = Schedulers.io();
        }
        return mScheduler;
    }


    public AppConfig getConfig() {
        return this.appConfig;
    }

    public UserSession getUserSession(){ return this.userSession; }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        mEventBus.unregister(this);
        super.onTerminate();
    }

    @Subscribe
    public void onEvent(SyncEvent event) { Log.d("Unauthorized", "Redirect to Signin Activity!"); }



}

