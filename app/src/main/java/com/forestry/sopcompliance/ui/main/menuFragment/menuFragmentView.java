package com.forestry.sopcompliance.ui.main.menuFragment;

import android.view.View;

import com.forestry.sopcompliance.ui.base.BaseView;

/**
 * Created by abrami on 8/16/2017.
 */

public interface menuFragmentView extends BaseView {

    void initMineral(View v);
    void initGambut(View v);
    void refresh();
    void willBeDisplayed();
    void willBeHidden();
}

