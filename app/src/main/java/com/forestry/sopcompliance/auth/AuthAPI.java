package com.forestry.sopcompliance.auth;

import com.forestry.sopcompliance.AppController;
import com.forestry.sopcompliance.data.remote.response.LoginResponseEnvelope;
import com.forestry.sopcompliance.events.SyncEvent;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

/**
 * Created by fimansya on 5/11/2017.
 */

public class AuthAPI {
    public interface LoginCallback {
        void onSuccess();
        void onFail(Throwable e);
    }


    private AppController app;
    private AuthInfo authInfo;
    private EventBus eventBus;


    @Inject
    public AuthAPI(AppController app, AuthInfo authInfo,
                       EventBus eventBus) {
        this.app = app;
        this.authInfo = authInfo;
        this.eventBus = eventBus;
    }

    public AuthInfo getAuthInfo() {
        return authInfo;
    }


    /*public Subscription login(LoginRequestEnvelope envelope, final LoginCallback callback) {
        AuthService api = AuthService.SOAP.createLoginService();

        return api.requestLogin(envelope)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(app.getSubscribeScheduler())
                .subscribe(new Subscriber<LoginResponseEnvelope>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        callback.onFail(e);
                    }

                    @Override
                    public void onNext(LoginResponseEnvelope loginResponseEnvelope) {
                        onLoginSuccess(loginResponseEnvelope);
                        callback.onSuccess();
                    }
                });
    }*/

    private void onLoginSuccess(LoginResponseEnvelope response) {
        eventBus.post(new SyncEvent());
    }


}
