package com.forestry.sopcompliance.ui.main.home;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.forestry.sopcompliance.R;
import com.forestry.sopcompliance.ui.base.BaseActivity;
import com.forestry.sopcompliance.ui.main.homeFragment.homeFragmentActivity;
import com.forestry.sopcompliance.ui.main.homeFragment.homeViewPagerAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by abrami on 8/16/2017.
 */

public class homeActivity extends BaseActivity implements homeView {

    @Inject
    homePresenter presenter;

    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigation;

    @BindView(R.id.view_pager)
    AHBottomNavigationViewPager viewPager;

    @BindView(R.id.floating_action_button)
    FloatingActionButton floatingActionButton;

    private AHBottomNavigationItem mineralEntry;
    private AHBottomNavigationItem gambutEntry;
    private AHBottomNavigationItem acSedling;
    private AHBottomNavigationItem acCutting;
    private AHBottomNavigationItem epCutting;

    private homeFragmentActivity currentFragment;
    private homeViewPagerAdapter adapter;
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component().inject(this);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        this.presenter.attachView(this, getContext());
        this.presenter.sethomePresenter();

        boolean enabledTranslucentNavigation = getSharedPreferences("shared", Context.MODE_PRIVATE)
                .getBoolean("translucentNavigation", false);
        setTheme(enabledTranslucentNavigation ? R.style.AppTheme_TranslucentNavigation : R.style.AppTheme);

    }

    @Override
    public void initUI() {

        mineralEntry = new AHBottomNavigationItem(R.string.menu_1, R.mipmap.ic_mineral, R.color.dark_blue);
        gambutEntry = new AHBottomNavigationItem(R.string.menu_2, R.mipmap.ic_gambut, R.color.blue_grey);
        acSedling = new AHBottomNavigationItem(R.string.menu_3, R.mipmap.ic_seedling, R.color.dark_blue);
        acCutting = new AHBottomNavigationItem(R.string.menu_4, R.mipmap.ic_seedling, R.color.blue_grey);
        epCutting = new AHBottomNavigationItem(R.string.menu_5, R.mipmap.ic_seedling, R.color.dark_blue);

        bottomNavigationItems.add(mineralEntry);
        bottomNavigationItems.add(gambutEntry);
        bottomNavigationItems.add(acSedling);
        bottomNavigationItems.add(acCutting);
        bottomNavigationItems.add(epCutting);

        bottomNavigation.addItems(bottomNavigationItems);

        bottomNavigation.manageFloatingActionButtonBehavior(floatingActionButton);
        bottomNavigation.setTranslucentNavigationEnabled(true);

        bottomNavigation.setOnTabSelectedListener((position, wasSelected) -> {

            if (currentFragment == null) {
                currentFragment = adapter.getCurrentFragment();
            }

            if (wasSelected) {
                currentFragment.refresh();
                return true;
            }

            if (currentFragment != null) {
                currentFragment.willBeHidden();
            }

            viewPager.setCurrentItem(position, false);
            currentFragment = adapter.getCurrentFragment();
            currentFragment.willBeDisplayed();

            if (position == 1) {
                bottomNavigation.setNotification("", 1);

                floatingActionButton.setVisibility(View.VISIBLE);
                floatingActionButton.setAlpha(0f);
                floatingActionButton.setScaleX(0f);
                floatingActionButton.setScaleY(0f);
                floatingActionButton.animate()
                        .alpha(1)
                        .scaleX(1)
                        .scaleY(1)
                        .setDuration(300)
                        .setInterpolator(new OvershootInterpolator())
                        .setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                floatingActionButton.animate()
                                        .setInterpolator(new LinearOutSlowInInterpolator())
                                        .start();
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        })
                        .start();

            } else {
                if (floatingActionButton.getVisibility() == View.VISIBLE) {
                    floatingActionButton.animate()
                            .alpha(0)
                            .scaleX(0)
                            .scaleY(0)
                            .setDuration(300)
                            .setInterpolator(new LinearOutSlowInInterpolator())
                            .setListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    floatingActionButton.setVisibility(View.GONE);
                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {
                                    floatingActionButton.setVisibility(View.GONE);
                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {

                                }
                            })
                            .start();
                }
            }

            return true;
        });


        bottomNavigation.setOnNavigationPositionListener(y -> Log.d("DemoActivity", "BottomNavigation Position: " + y));

        viewPager.setOffscreenPageLimit(4);
        adapter = new homeViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        currentFragment = adapter.getCurrentFragment();

        /*Notification*/
        /*handler.postDelayed(() -> {
            // Setting custom colors for notification
            AHNotification notification = new AHNotification.Builder()
                    .setText(":)")
                    .setBackgroundColor(ContextCompat.getColor(homeActivity.this, R.color.color_notification_back))
                    .setTextColor(ContextCompat.getColor(homeActivity.this, R.color.color_notification_text))
                    .build();
            bottomNavigation.setNotification(notification, 1);
            Snackbar.make(bottomNavigation, "Snackbar with bottom navigation",
                    Snackbar.LENGTH_SHORT).show();

        }, 3000);*/

        bottomNavigation.setDefaultBackgroundResource(R.drawable.bottom_navigation_background);
        bottomNavigation.setColored(true);
        bottomNavigation.setSelectedBackgroundVisible(true);
    }

    @OnClick(R.id.floating_action_button)
    public void saved(){
        Snackbar.make(bottomNavigation, "Saved data success",
                Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }

}
