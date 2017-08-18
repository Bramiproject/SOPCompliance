package com.forestry.sopcompliance.ui.main.homeFragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 *
 */
public class homeViewPagerAdapter extends FragmentPagerAdapter {

	private ArrayList<homeFragmentActivity> fragments = new ArrayList<>();
	private homeFragmentActivity currentFragment;

	public homeViewPagerAdapter(FragmentManager fm) {
		super(fm);

		fragments.clear();
		fragments.add(homeFragmentActivity.newInstance(0));
		fragments.add(homeFragmentActivity.newInstance(1));
		fragments.add(homeFragmentActivity.newInstance(2));
		fragments.add(homeFragmentActivity.newInstance(3));
		fragments.add(homeFragmentActivity.newInstance(4));
	}

	@Override
	public homeFragmentActivity getItem(int position) {
		return fragments.get(position);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	@Override
	public void setPrimaryItem(ViewGroup container, int position, Object object) {
		if (getCurrentFragment() != object) {
			currentFragment = ((homeFragmentActivity) object);
		}
		super.setPrimaryItem(container, position, object);
	}

	/**
	 * Get the current fragment
	 */
	public homeFragmentActivity getCurrentFragment() {
		return currentFragment;
	}
}