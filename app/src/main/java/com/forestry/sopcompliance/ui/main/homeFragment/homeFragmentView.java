package com.forestry.sopcompliance.ui.main.homeFragment;

import android.view.View;

import com.forestry.sopcompliance.ui.base.BaseView;

/**
 * Created by abrami on 8/16/2017.
 */

public interface homeFragmentView extends BaseView {

    void initSettings();
    void initList();
    void refresh();
    void willBeDisplayed();
    void willBeHidden();
}

