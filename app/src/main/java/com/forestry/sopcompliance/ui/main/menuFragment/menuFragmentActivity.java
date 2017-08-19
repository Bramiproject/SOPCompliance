package com.forestry.sopcompliance.ui.main.menuFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.forestry.sopcompliance.R;
import com.forestry.sopcompliance.ui.base.BaseFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import javax.inject.Inject;
/**
 * Created by abrami on 8/16/2017.
 */

public class menuFragmentActivity extends BaseFragment implements menuFragmentView {

    @Inject
    menuFragmentPresenter presenter;

    /*@BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;

    @BindView(R.id.fragment_demo_recycler_view)
    RecyclerView recyclerView;*/

    /*Tab Mineral*/
    ViewGroup tabMineral;
    ViewPager viewPagerMineral;
    SmartTabLayout viewPagerTabMineral;

    /*Tab Gambut*/
    ViewGroup tabGambut;
    ViewPager viewPagerGambut;
    SmartTabLayout viewPagerTabGambut;

    public static int[] tabMineral() {
        return new int[] {
                R.string.tab_mineral_1,
                R.string.tab_mineral_2,
                R.string.tab_mineral_3
        };
    }
    public static int[] tabGambut() {
        return new int[] {
                R.string.tab_mineral_1,
                R.string.tab_mineral_2,
                R.string.tab_mineral_3
        };
    }

    public static menuFragmentActivity newInstance(int index) {
        menuFragmentActivity fragment = new menuFragmentActivity();
        Bundle b = new Bundle();
        b.putInt("index", index);
        fragment.setArguments(b);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (getArguments().getInt("index") == 0) {
            component().inject(this);
            this.presenter.attachView(this, getActivity());
            View viewMineral = inflater.inflate(R.layout.fragment_mineral, container, false);
            this.presenter.setInitMineral(viewMineral);

            return viewMineral;
        } else {
            component().inject(this);
            this.presenter.attachView(this, getActivity());
            View viewGambut = inflater.inflate(R.layout.fragment_gambut, container, false);
            this.presenter.setInitGambut(viewGambut);

            return viewGambut;
        }
    }

    @Override
    public void initMineral(View v) {
        tabMineral = v.findViewById(R.id.tabMinerals);
        tabMineral.addView(LayoutInflater.from(getActivity()).inflate(R.layout.tab_adapter_mineral, tabMineral, false));

        viewPagerMineral = v.findViewById(R.id.viewpagerMinerals);
        viewPagerTabMineral = v.findViewById(R.id.viewpagertabMinerals);

        FragmentPagerItems pages = new FragmentPagerItems(getActivity());
        for (int titleResIdMineral : tabsMineral()) {
            pages.add(FragmentPagerItem.of(getString(titleResIdMineral), submenuFragmentMineral.class));
        }

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getActivity().getSupportFragmentManager(), pages);

        viewPagerMineral.setAdapter(adapter);
        viewPagerTabMineral.setViewPager(viewPagerMineral);
    }

    @Override
    public void initGambut(View v) {
        tabGambut = v.findViewById(R.id.tabGambuts);
        tabGambut.addView(LayoutInflater.from(getActivity()).inflate(R.layout.tab_adapter_gambut, tabGambut, false));

        viewPagerGambut = v.findViewById(R.id.viewpagerGambuts);
        viewPagerTabGambut = v.findViewById(R.id.viewpagertabGambuts);

        FragmentPagerItems pages = new FragmentPagerItems(getActivity());
        for (int titleResIdGambut : tabsGambut()) {
            pages.add(FragmentPagerItem.of(getString(titleResIdGambut), submenuFragmentGambut.class));
        }

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getActivity().getSupportFragmentManager(), pages);

        viewPagerGambut.setAdapter(adapter);
        viewPagerTabGambut.setViewPager(viewPagerGambut);
    }

    @Override
    public void refresh() {
        /*if (getArguments().getInt("index", 0) > 0 && recyclerView != null) {
            recyclerView.smoothScrollToPosition(0);
        }*/
    }

    @Override
    public void willBeDisplayed() {
        // Do what you want here, for example animate the content
        /*if (fragmentContainer != null) {
            Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
            fragmentContainer.startAnimation(fadeIn);
        }*/
    }

    @Override
    public void willBeHidden() {
        /*if (fragmentContainer != null) {
            Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
            fragmentContainer.startAnimation(fadeOut);
        }*/
    }

    public int[] tabsMineral() {
        return tabMineral();
    }
    public int[] tabsGambut() {
        return tabGambut();
    }
}
