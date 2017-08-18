package com.forestry.sopcompliance.ui.main.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.forestry.sopcompliance.MainActivity;
import com.forestry.sopcompliance.R;
import com.forestry.sopcompliance.data.model.User;
import com.forestry.sopcompliance.data.remote.response.LoginResponseEnvelope;
import com.forestry.sopcompliance.databinding.ActivityLoginBinding;
import com.forestry.sopcompliance.services.UserSession;
import com.forestry.sopcompliance.ui.base.BaseActivity;
import com.forestry.sopcompliance.ui.main.home.homeActivity;
import com.forestry.sopcompliance.utils.PermissionUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;

/**
 * Created by abrami on 8/14/2017.
 */

public class loginActivity extends BaseActivity implements loginView {

    @Inject
    loginPresenter presenter;
    @Inject
    UserSession userSession;

    @BindView(R.id.input_uid_login)
    EditText loginId;

    @BindView(R.id.input_password)
    EditText passwordId;

    @BindView(R.id.location_spinner)
    Spinner locationSpinner;

    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    private ProgressDialog mProgressDialog;

    private ActivityLoginBinding dataBinding;
    private User userVM = new User();

    private SharedPreferences locationSessionSpinner;
    private SharedPreferences.Editor prefEditor;
    private int spinner_pos;
    private String location, locationId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component().inject(this);

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        if (userSession.isUserLoggedIn()) {
            startActivity(new Intent(loginActivity.this, homeActivity.class));
        }

        presenter.attachView(this, getContext());

        dataBinding.setData(userVM);
        ButterKnife.bind(this);

        locationSessionSpinner = getSharedPreferences("LocationSpinner", 0);
        int pos = locationSessionSpinner.getInt("location", -1);
        if (pos != -1) {
            locationSpinner.setSelection(pos);
        }

        // Transparent Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        checkPermission();
    }

    private void checkPermission() {
        PermissionUtils.checkAllPermissions(this, result -> {
            if (result) {} else {
                Snackbar.make(coordinatorLayout, "Untuk menggunakan aplikasi ini,\nAnda harus mengijinkan penggunaan akses Internet pada device", Snackbar.LENGTH_LONG).show();
                //Toast.makeText(getBaseContext(), "Untuk menggunakan aplikasi ini,\nAnda harus mengijinkan penggunaan akses Internet pada device", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private String getLocationID() {
        getLocation();
        spinner_pos = locationSpinner.getSelectedItemPosition();
        String[] stringLocationId = getResources().getStringArray(R.array.locationID);
        locationId = stringLocationId[spinner_pos];
        return locationId;
    }



    @OnClick(R.id.btn_login)
    public void prosesLogin(){
        if (!TextUtils.isEmpty(userVM.cuisID) && !TextUtils.isEmpty(userVM.password)){
            presenter.login(userVM , getLocationID());
            prefEditor = locationSessionSpinner.edit();
            prefEditor.putInt("location", spinner_pos);
            prefEditor.apply();
        } else {
            Snackbar.make(coordinatorLayout, "Mohon lengkapi semua kotak isian...", Snackbar.LENGTH_LONG).show();
            //Toast.makeText(this, "Mohon lengkapi semua kotak isian...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public String getLocation() {
        location = locationSpinner.getSelectedItem().toString();
        return location;
    }

    @Override
    public void showProgress(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(true);
        }
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showData(String username) {
        //Toast.makeText(this, "Login atas nama " + username + " berhasil.", Toast.LENGTH_SHORT).show();
        Snackbar.make(coordinatorLayout, "Hello " + username + " anda berhasil login.", Snackbar.LENGTH_LONG).show();
        Intent i = new Intent(loginActivity.this, MainActivity.class);
        i.putExtra("USERNAME", username);
        startActivity(i);
        finish();
    }

    @Override
    public void loginSuccess(Response<LoginResponseEnvelope> response) { }

    @Override
    public void loginFailed(String msg) {
        Snackbar.make(coordinatorLayout, msg, Snackbar.LENGTH_LONG).show();
    }
}