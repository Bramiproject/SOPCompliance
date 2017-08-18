package com.forestry.sopcompliance.di.module;

import com.forestry.sopcompliance.AppConfig;
import com.forestry.sopcompliance.AppController;
import com.forestry.sopcompliance.auth.AuthService;
import com.forestry.sopcompliance.di.ApplicationScope;
import com.forestry.sopcompliance.services.AuthObservableService;
import com.forestry.sopcompliance.services.HotspotObservableService;
import com.forestry.sopcompliance.services.UserSession;

import org.greenrobot.eventbus.EventBus;

import dagger.Module;
import dagger.Provides;

/**
 * Created by abrami on 8/14/2017.
 */
@Module
public class ApplicationModule {
    private final AppController app;

    public ApplicationModule(AppController app) {
        this.app = app;
    }

    @Provides
    @ApplicationScope
    public AppController provideApplication() {
        return app;
    }

    @Provides
    @ApplicationScope
    public AuthService provideAuthService() {
        return AuthService.SOAP.createLoginService();
    }

    @Provides
    @ApplicationScope
    public AppConfig provideAppConfig() { return new AppConfig(); }

    @Provides
    @ApplicationScope
    public EventBus eventBus() {
        return new EventBus();
    }

    @Provides
    @ApplicationScope
    public HotspotObservableService provideHotspotObsService() {
        return new HotspotObservableService(this.app);
    }

    @Provides
    @ApplicationScope
    public AuthObservableService provideAuthObservableService() {
        return new AuthObservableService(this.app);
    }

    @Provides
    @ApplicationScope
    public UserSession provideUserSession() {
        return new UserSession(this.app);
    }

}
