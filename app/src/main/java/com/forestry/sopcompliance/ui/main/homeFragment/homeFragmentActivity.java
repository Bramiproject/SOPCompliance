package com.forestry.sopcompliance.ui.main.homeFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.forestry.sopcompliance.R;
import com.forestry.sopcompliance.ui.base.BaseFragment;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by abrami on 8/16/2017.
 */

public class homeFragmentActivity extends BaseFragment implements homeFragmentView {

    @Inject
    homeFragmentPresenter presenter;

    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;

    @BindView(R.id.fragment_demo_recycler_view)
    RecyclerView recyclerView;

    private RecyclerView.LayoutManager layoutManager;

    public static homeFragmentActivity newInstance(int index) {
        homeFragmentActivity fragment = new homeFragmentActivity();
        Bundle b = new Bundle();
        b.putInt("index", index);
        fragment.setArguments(b);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getArguments().getInt("index", 0) == 0) {
            component().inject(this);
            View view = inflater.inflate(R.layout.fragment_list, container, false);
            ButterKnife.bind(this, view);
            presenter.attachView(this, getActivity());
            presenter.sethomeFragmentPresenter();
            return view;
        } else {
            component().inject(this);
            View view = inflater.inflate(R.layout.fragment_list, container, false);
            ButterKnife.bind(this, view);
            presenter.attachView(this, getActivity());
            presenter.sethomeFragmentPresenter();
            return view;
        }
    }

    @Override
    public void initSettings() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<String> itemsData = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            itemsData.add("Fragment " + getArguments().getInt("index", -1) + " / Item " + i);
        }

        homeFragmentAdapter adapter = new homeFragmentAdapter(itemsData);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initList() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<String> itemsData = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            itemsData.add("Fragment " + getArguments().getInt("index", -1) + " / Item " + i);
        }

        homeFragmentAdapter adapter = new homeFragmentAdapter(itemsData);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void refresh() {
        if (getArguments().getInt("index", 0) > 0 && recyclerView != null) {
            recyclerView.smoothScrollToPosition(0);
        }
    }

    @Override
    public void willBeDisplayed() {
        // Do what you want here, for example animate the content
        if (fragmentContainer != null) {
            Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
            fragmentContainer.startAnimation(fadeIn);
        }
    }

    @Override
    public void willBeHidden() {
        if (fragmentContainer != null) {
            Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
            fragmentContainer.startAnimation(fadeOut);
        }
    }
}
