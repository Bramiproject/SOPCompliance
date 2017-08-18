package com.forestry.sopcompliance.ui.main.login;

import com.forestry.sopcompliance.data.remote.response.LoginResponseEnvelope;
import com.forestry.sopcompliance.ui.base.BaseView;

import retrofit2.Response;

/**
 * Created by abrami on 8/14/2017.
 */

public interface loginView extends BaseView {
    String getLocation();

    void showProgress(String message);

    void hideProgress();

    void showData(String username);

    void loginSuccess(Response<LoginResponseEnvelope> response);

    void loginFailed(String msg);
}
