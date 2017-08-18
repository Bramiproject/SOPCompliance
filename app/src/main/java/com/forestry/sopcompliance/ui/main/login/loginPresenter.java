package com.forestry.sopcompliance.ui.main.login;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.forestry.sopcompliance.AppController;
import com.forestry.sopcompliance.auth.AuthService;
import com.forestry.sopcompliance.data.local.dao.HotspotVerificationTableDAO;
import com.forestry.sopcompliance.data.model.User;
import com.forestry.sopcompliance.data.remote.request.LoginRequestBody;
import com.forestry.sopcompliance.data.remote.request.LoginRequestData;
import com.forestry.sopcompliance.data.remote.request.LoginRequestEnvelope;
import com.forestry.sopcompliance.data.remote.response.LoginResponseEnvelope;
import com.forestry.sopcompliance.events.SyncEvent;
import com.forestry.sopcompliance.services.UserSession;
import com.forestry.sopcompliance.ui.base.BasePresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by abrami on 8/14/2017.
 */

public class loginPresenter implements BasePresenter<loginView> {


    private loginView mMainView;
    private Subscription mSubscription = Subscriptions.empty();
    private EventBus eventBus;
    private AuthService authService;
    private Context context;
    private HotspotVerificationTableDAO dataHs;
    private UserSession userSession;



    @Inject
    public loginPresenter(UserSession userSession , EventBus eventBus, AuthService authService, HotspotVerificationTableDAO dataHs) {
        this.eventBus = eventBus;
        this.userSession = userSession;
        this.authService = authService;
        this.dataHs = dataHs;
    }


    @Override
    public void attachView(loginView view, Context ctx) {
        mMainView = view;
        eventBus.register(this);
        context = ctx;
        dataHs.open();

    }


    @Override
    public void detachView() {
        mMainView = null;
        if (mSubscription != null) mSubscription.unsubscribe();
        //dataHs.close();
    }



    public void login(User userBinding, String locationId){

        mMainView.showProgress("Loading... Mohon tunggu");
        //if (mSubscription != null) mSubscription.unsubscribe();
        LoginRequestEnvelope envelope = new LoginRequestEnvelope();

        LoginRequestBody body = new LoginRequestBody();

        LoginRequestData data = new LoginRequestData();

        data.setIDLocation(locationId);
        data.setUIDLogin(userBinding.getCuisID());
        data.setPassword(userBinding.getPassword());
        body.setLoginRequestData(data);
        envelope.setBody(body);

        Call<LoginResponseEnvelope> call = authService.requestLogin(envelope);

        call.enqueue(new Callback<LoginResponseEnvelope>() {
            @Override
            public void onResponse(Call<LoginResponseEnvelope> call, Response<LoginResponseEnvelope> response) {
                if (mMainView != null ) {
                    CharSequence t = response.body().getBody().getData().getUsername();

                    try {
                        if (!TextUtils.isEmpty(t)) {
                            userSession.createUserLoginSession(userBinding.getCuisID(), response.body().getBody().getData().getUsername(),  locationId);
                            String msg = userSession.getUsername();
                            if (dataHs != null) dataHs.deleteAllHotspots();
                            mMainView.hideProgress();
                            mMainView.showData(msg);

                        } else {
                            mMainView.hideProgress();
                            mMainView.loginFailed("CUIS ID, password atau lokasi yang anda masukan salah!\nSilahkan coba lagi.");
                        }

                    } catch (Exception e) {}

                }
            }

            @Override
            public void onFailure(Call<LoginResponseEnvelope> call, Throwable t) {
                if (mMainView != null) {
                    mMainView.hideProgress();
                    mMainView.loginFailed("Jaringan bermasalah, Mohon cek koneksi Internet/WIFI");
                }
            }
        });

    }



    @Subscribe
    public void onEvent(SyncEvent event) {
        Log.d("Unauthorized", "Redirect to Signin Activity!");
    }



}
