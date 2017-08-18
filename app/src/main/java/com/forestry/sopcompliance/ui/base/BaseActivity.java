package com.forestry.sopcompliance.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.forestry.sopcompliance.AppController;
import com.forestry.sopcompliance.di.component.ActivityComponent;
import com.forestry.sopcompliance.di.component.DaggerActivityComponent;
import com.forestry.sopcompliance.di.module.ActivityModule;

/**
 * Created by Abrami on 08/14/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private ActivityComponent component;


    public ActivityComponent component() {
        if (this.component == null) {
            this.component = DaggerActivityComponent.builder().applicationComponent(getApp().getApplicationComponent())
                    .activityModule(new ActivityModule(this))
                    .build();

        }
        return this.component;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    protected AppController getApp() {
        return (AppController) getApplication();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
