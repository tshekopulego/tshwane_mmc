package com.pulego.tshwanesafetymc;



import java.util.ArrayList;
import java.util.List;

import com.pulego.tshwanesafetymc.utils.BarFragment;
import com.pulego.tshwanesafetymc.utils.InterfaceUtils;
import com.pulego.tshwanesafetymc.utils.LineFragment;
import com.pulego.tshwanesafetymc.utils.PieFragment;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
//import android.app.ActionBar;
//import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentThree extends Fragment implements TabListener{
	 List<Fragment> tabFragmentList = new ArrayList<Fragment>();
	// Declaring our tabs and the corresponding fragments.
		ActionBar.Tab barTab, lineTab, pieTab;
		Fragment barFragmentTab = new BarFragment();
		Fragment lineFragmentTab = new LineFragment();
		Fragment pieFragmentTab = new PieFragment();
		
	private InterfaceUtils interfaceUtilsListener;
	
	public FragmentThree() {

	}
    
	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
		interfaceUtilsListener=null;
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try {
			  interfaceUtilsListener =
			    (InterfaceUtils)  activity;
			 } catch (ClassCastException e) {
			  throw new ClassCastException(activity.toString()
			 + " must implement OnFragmentInteractionListener");
			 }
			
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.frag_incident, container,
				false);
//******************************************************************
		// Asking for the default ActionBar element that our platform supports.
		//	ActionBar actionBar = getActionBar();
		ActionBar actionBar = getActivity().getActionBar();
				 
		        // Screen handling while hiding ActionBar icon.
		       actionBar.setDisplayShowHomeEnabled(true);
		 
		        // Screen handling while hiding Actionbar title.
		        actionBar.setDisplayShowTitleEnabled(true);
		 
		        // Creating ActionBar tabs.
		        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		        //Changing background color on action bar
		       // actionBar.setBackgroundDrawable();
		        
		        // Setting custom tab icons.
		        barTab = actionBar.newTab().setText("Type");
		        pieTab = actionBar.newTab().setText("Status");
		        lineTab = actionBar.newTab().setText("Incidents");
		        //initializing all the tabs
		        com.pulego.tshwanesafetymc.utils.TabListener bar=new com.pulego.tshwanesafetymc.utils.TabListener(barFragmentTab);
		        com.pulego.tshwanesafetymc.utils.TabListener pie=new com.pulego.tshwanesafetymc.utils.TabListener(pieFragmentTab);
		        com.pulego.tshwanesafetymc.utils.TabListener line=new com.pulego.tshwanesafetymc.utils.TabListener(lineFragmentTab);
		        // Setting tab listeners.
		        barTab.setTabListener(bar);
		        pieTab.setTabListener(pie);
		        lineTab.setTabListener(line);
		      
		        // Adding tabs to the ActionBar.
		        actionBar.addTab(lineTab);
		        actionBar.addTab(pieTab);
		        actionBar.addTab(barTab);
		
//******************************************************************
		/*ActionBar actionBar = getActivity().getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        /*for (int i=1; i <= 3; i++) {
            Tab tab = actionBar.newTab();
            tab.setText("Tab " + i);
            tab.setTabListener(this);
            actionBar.addTab(tab);
        }*/
        /*Tab tab = actionBar.newTab();
        tab.setText("Bar graph" );
        tab.setTabListener(this);
        actionBar.addTab(tab);
        
        Tab tab2 = actionBar.newTab();
        tab.setText("Pie graph");
        tab.setTabListener(this);
        actionBar.addTab(tab2);
        
        Tab tab3 = actionBar.newTab();
        tab.setText("Line graph");
        tab.setTabListener(this);
        actionBar.addTab(tab3);*/
	    //**************************************************************
		 Toast.makeText(view.getContext(), "Open chart", Toast.LENGTH_SHORT).show();
		return view;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		Fragment fragment = null;
       BarFragment barFragment = null;
       PieFragment pieFragment = null;
       LineFragment lineFragment = null;

        if (tabFragmentList.size() > tab.getPosition()) {
            fragment = tabFragmentList.get(tab.getPosition());
        }

        if (fragment == null) {
        	barFragment = new BarFragment();
        	pieFragment =new PieFragment();
        	lineFragment = new LineFragment();
        	
            Bundle bundle = new Bundle();
            int imgResId = 0;
            int colorResId = 0;
            if(tab.getPosition()==0){
               // imgResId = getResources().getIdentifier("duck", "drawable",
                //        "com.javapapers.android.androidtablayout.app");
               // colorResId = R.color.color1;
            	fragment = (Fragment) barFragment;
            } else if (tab.getPosition()==1){
              //  imgResId = getResources().getIdentifier("parrot", "drawable",
               //         "com.javapapers.android.androidtablayout.app");
              //  colorResId = R.color.color2;
            	fragment = (Fragment) pieFragment;
            } else if(tab.getPosition()==2){
               // imgResId = getResources().getIdentifier("cock", "drawable",
                //        "com.javapapers.android.androidtablayout.app");
               // colorResId = R.color.color3;
            	fragment = (Fragment) lineFragment;
            }
           // bundle.putInt("image", imgResId);
          //  bundle.putInt("color", colorResId);
           // barFragment.setArguments(bundle);
           // tabFragmentList.add(barFragment);
        }
       /* else {
        	barFragment = (BarFragment) fragment;
        }*/
        ft.replace(android.R.id.content, fragment);
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

}
