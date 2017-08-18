package com.forestry.sopcompliance.di.component;

import com.forestry.sopcompliance.AppController;
import com.forestry.sopcompliance.auth.AuthService;
import com.forestry.sopcompliance.data.remote.UnauthorisedInterceptor;
import com.forestry.sopcompliance.di.ApplicationScope;
import com.forestry.sopcompliance.di.module.ApplicationModule;
import com.forestry.sopcompliance.services.AuthObservableService;
import com.forestry.sopcompliance.services.HotspotObservableService;
import com.forestry.sopcompliance.services.UserSession;

import org.greenrobot.eventbus.EventBus;

import dagger.Component;

/**
 * Created by abrami on 8/14/2017.
 */

@ApplicationScope
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(AppController app);

    void inject(UnauthorisedInterceptor unauthorisedInterceptor);

    AppController provideApplication();

    EventBus provideEventBus();

    AuthService provideAuthService();

    AuthObservableService provideAuthObservableService();

    HotspotObservableService provideHotspotObsServices();

    UserSession provideUserSession();

    /*

    AuthObservableService provideAuthObservableService();

    GisDataObservableService provideGisDataObservableService();

    UserSession provideUserSession();*/
}
