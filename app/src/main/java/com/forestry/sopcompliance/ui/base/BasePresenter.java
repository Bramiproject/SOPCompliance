package com.forestry.sopcompliance.ui.base;


import android.content.Context;
/**
 * Created by abrami on 8/14/2017.
 */
public interface BasePresenter<V extends BaseView> {

    void attachView(V view, Context context);

    void detachView();

}