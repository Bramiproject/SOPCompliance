package com.forestry.sopcompliance.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.forestry.sopcompliance.AppController;
import com.forestry.sopcompliance.di.component.ActivityComponent;

/**
 * Created by abrami on 8/14/2017.
 */

public abstract class BaseFragment extends Fragment {


    public ActivityComponent component() {
        return ((BaseActivity) getActivity()).component();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    protected AppController getApp() {
        return (AppController) getActivity().getApplication();
    }

    protected Context getAppContext() {
        return getActivity().getApplicationContext();
    }

}
