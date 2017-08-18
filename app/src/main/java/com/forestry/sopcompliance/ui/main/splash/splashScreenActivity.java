package com.forestry.sopcompliance.ui.main.splash;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.forestry.sopcompliance.R;
import com.forestry.sopcompliance.ui.base.BaseActivity;
import com.forestry.sopcompliance.ui.main.login.loginActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by abrami on 8/15/2017.
 */

public class splashScreenActivity extends BaseActivity implements splashScreenView {

    @Inject
    splashScreenPresenter presenter;

    @BindView(R.id.animImage)
    ImageView animImage;

    @BindView(R.id.animText)
    TextView animText;

    private static final int SPLASH_TIME = 3000;
    private static final int SPLASH_TIME_ANIMIAMGE = 1000;
    private static final int SPLASH_TIME_ANIMTEXTVIEW = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component().inject(this);
        setContentView(R.layout.activity_launchscreen);

        presenter.attachView(this, getContext());
        presenter.setSplashScreenViews();
        ButterKnife.bind(this);

        // Transparent Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showContent() {

        new Handler().postDelayed(() -> {
            animImage.setVisibility(View.VISIBLE);
            YoYo.with(Techniques.FadeInUp).duration(5000).playOn(animImage);
            new Handler().postDelayed(() -> {
                animText.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.BounceInUp).duration(2000).playOn(animText);
                new Handler().postDelayed(() -> {
                    Intent i = new Intent(splashScreenActivity.this, loginActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                    finish();
                }, SPLASH_TIME);
            }, SPLASH_TIME_ANIMTEXTVIEW);
        }, SPLASH_TIME_ANIMIAMGE);
    }
}
