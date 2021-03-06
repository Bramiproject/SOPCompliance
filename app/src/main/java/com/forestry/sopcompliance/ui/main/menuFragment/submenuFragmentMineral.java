package com.forestry.sopcompliance.ui.main.menuFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.forestry.sopcompliance.R;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;

public class submenuFragmentMineral extends Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_pages_mineral, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    int position = FragmentPagerItem.getPosition(getArguments());
    TextView title = view.findViewById(R.id.item_title);
    title.setText(String.valueOf(position));
  }

}
