package com.forestry.sopcompliance.data.remote;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.forestry.sopcompliance.AppController;
import com.forestry.sopcompliance.events.SyncEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by fimansya on 5/5/2017.
 */

public class UnauthorisedInterceptor implements Interceptor {

    @Inject
    EventBus eventBus;

    public UnauthorisedInterceptor(Context context) {
        AppController.get(context).getApplicationComponent().inject(this);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        if (response.code() == 401) {
            //new Handler(Looper.getMainLooper()).post(() -> eventBus.post(new SyncEvent()));
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    eventBus.post(new SyncEvent());
                }
            });
        }
        return response;
    }
}