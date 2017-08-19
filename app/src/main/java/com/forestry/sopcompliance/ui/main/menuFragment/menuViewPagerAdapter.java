package com.forestry.sopcompliance.ui.main.menuFragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 *
 */
public class menuViewPagerAdapter extends FragmentPagerAdapter {

	private ArrayList<menuFragmentActivity> fragments = new ArrayList<>();
	private menuFragmentActivity currentFragment;

	public menuViewPagerAdapter(FragmentManager fm) {
		super(fm);

		fragments.clear();
		fragments.add(menuFragmentActivity.newInstance(0));
		fragments.add(menuFragmentActivity.newInstance(1));
		fragments.add(menuFragmentActivity.newInstance(2));
		fragments.add(menuFragmentActivity.newInstance(3));
		fragments.add(menuFragmentActivity.newInstance(4));
	}

	@Override
	public menuFragmentActivity getItem(int position) {
		return fragments.get(position);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	@Override
	public void setPrimaryItem(ViewGroup container, int position, Object object) {
		if (getCurrentFragment() != object) {
			currentFragment = ((menuFragmentActivity) object);
		}
		super.setPrimaryItem(container, position, object);
	}

	/**
	 * Get the current fragment
	 */
	public menuFragmentActivity getCurrentFragment() {
		return currentFragment;
	}
}