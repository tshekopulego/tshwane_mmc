package com.pulego.tshwanesafetymc.utils;

import com.pulego.tshwanesafetymc.R;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AboutFragment extends Fragment {
    public AboutFragment(){
    	
    }
    @Override
   	public View onCreateView(LayoutInflater inflater, ViewGroup container,
   			Bundle savedInstanceState) {
   	   View view=inflater.inflate(R.layout.fragment_layout_two,container, false);
   		
   		ActionBar actionBar = getActivity().getActionBar();
   		 actionBar.removeAllTabs();
          // Screen handling while hiding ActionBar icon.
         actionBar.setDisplayShowHomeEnabled(true);

          // Screen handling while hiding Actionbar title.
          actionBar.setDisplayShowTitleEnabled(true);

          // Creating ActionBar tabs.
          actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
          
          return view;
   	}
}
