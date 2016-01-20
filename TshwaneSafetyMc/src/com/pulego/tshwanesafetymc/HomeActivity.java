package com.pulego.tshwanesafetymc;

import java.util.ArrayList;
import java.util.List;



import com.pulego.tshwanesafetymc.ui.AboutFragment;
import com.pulego.tshwanesafetymc.ui.HelpFragment;
import com.pulego.tshwanesafetymc.ui.InboxFragment;
import com.pulego.tshwanesafetymc.ui.NotificationFragment;
import com.pulego.tshwanesafetymc.ui.ProfileFragment;
import com.pulego.tshwanesafetymc.ui.StrengthReportFragment;
import com.pulego.tshwanesafetymc.urlconnectors.UrlConnectNotifications;
import com.pulego.tshwanesafetymc.urlconnectors.UrlConnectStrengthReport;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.graphics.drawable.ColorDrawable;
import android.graphics.Typeface;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

public class HomeActivity extends Activity{

	private View mChart;
	private int index;
	
	
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	CustomDrawerAdapter adapter;

	List<DrawerItem> dataList;

	String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		 sharedpreferences  =  getApplicationContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
		 
		 
		//Calling an action bar Class
		ActionBar actionBar=getActionBar();
		
		//Changing the action bar's background color
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00B800")));
		
		//Sertting the action bar icon
        actionBar.setIcon(R.drawable.icon);
        
             
		// Initializing
				dataList = new ArrayList<DrawerItem>();
				mTitle = mDrawerTitle = getTitle();
				mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
				mDrawerList = (ListView) findViewById(R.id.left_drawer);

				mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
						GravityCompat.START);

				// Add Drawer Item to dataList
				//dataList.add(new DrawerItem(true)); // adding a spinner to the list

				//dataList.add(new DrawerItem("Main Options")); // adding a header to the list
				dataList.add(new DrawerItem("Incidents Stats", R.drawable.ic_i_statistics));
				dataList.add(new DrawerItem("Strength Reports", R.drawable.ic_i_reports));
				dataList.add(new DrawerItem("Notifications", R.drawable.ic_i_notification));
				//dataList.add(new DrawerItem("Inbox", R.drawable.ic_i_inbox));
				dataList.add(new DrawerItem("My Profile", R.drawable.ic_i_profile));

				// dataList.add(new DrawerItem("Other Option")); // adding a header to the list
				dataList.add(new DrawerItem("About", R.drawable.ic_i_about));
				dataList.add(new DrawerItem("Logout", R.drawable.ic_logout));
				//dataList.add(new DrawerItem("Help", R.drawable.ic_i_help));

				adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item,
						dataList);

				mDrawerList.setAdapter(adapter);

				mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
				
				getActionBar().setDisplayHomeAsUpEnabled(true);
				getActionBar().setHomeButtonEnabled(true);

				mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
						R.drawable.ic_drawer, R.string.drawer_open,
						R.string.drawer_close) {
					public void onDrawerClosed(View view) {
						if(index == 0){
						//getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
						SelectItem(0);
						}
						getActionBar().setTitle(mTitle);
						invalidateOptionsMenu(); // creates call to
													// onPrepareOptionsMenu()
					}

					public void onDrawerOpened(View drawerView) {
						getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
						getActionBar().removeAllTabs();
						getActionBar().setTitle(mDrawerTitle);
						invalidateOptionsMenu(); // creates call to
					    						// onPrepareOptionsMenu()

					}
				};

				mDrawerLayout.setDrawerListener(mDrawerToggle);

				if (savedInstanceState == null) {

					/*if (dataList.get(0).isSpinner()
							& dataList.get(1).getTitle() != null) {
						SelectItem(2);
					} else if (dataList.get(0).getTitle() != null) {
						SelectItem(1);
					} else {
						SelectItem(0);
					}*/
					if ( dataList.get(0).getTitle() != null) {
						SelectItem(0);
					} else{
						SelectItem(0);
					}
				}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	public void SelectItem(int possition) {

		Fragment fragment = null;
		Bundle args = new Bundle();
		FragmentManager frgManager = getFragmentManager();
		switch (possition) {

		case 0:
			fragment = new FragmentThree();
			frgManager.beginTransaction().replace(R.id.content_frame, fragment)
			.commit();
			break;
		case 1:
			fragment = new StrengthReportFragment();
			UrlConnectStrengthReport urlcreatereport = new UrlConnectStrengthReport(getApplicationContext());
			urlcreatereport.populateStrengthReportResult();
			//populate deployment on the db
			urlcreatereport.populateDiploymentTB();
			frgManager.beginTransaction().replace(R.id.content_frame, fragment)
			.commit();
			
			break;
		case 2:
			fragment = new NotificationFragment();
			UrlConnectNotifications urlfillNotification = new UrlConnectNotifications(getApplicationContext());
			urlfillNotification.fillNotifications();
			frgManager.beginTransaction().replace(R.id.content_frame, fragment)
			.commit();
			break;
		/*case 3:
			fragment = new InboxFragment();
			frgManager.beginTransaction().replace(R.id.content_frame, fragment)
			.commit();
			break;*/
		case 3:
			fragment = new ProfileFragment();
			frgManager.beginTransaction().replace(R.id.content_frame, fragment)
			.commit();
			break;
		case 4:
			fragment = new AboutFragment();
			frgManager.beginTransaction().replace(R.id.content_frame, fragment)
			.commit();
			break;
		case 5:
			SharedPreferences.Editor editor = sharedpreferences.edit();
			
			editor.clear();
			editor.commit();
		   
			Intent sigin =new Intent(getApplicationContext(),MainActivity.class);
			sigin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			sigin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(sigin);
			
			break;
		
		default:
			break;
		}

	/*	fragment.setArguments(args);
		FragmentManager frgManager = getFragmentManager();
		frgManager.beginTransaction().replace(R.id.content_frame, fragment)
				.commit();*/

		mDrawerList.setItemChecked(possition, true);
		setTitle(dataList.get(possition).getItemName());
		mDrawerLayout.closeDrawer(mDrawerList);

	}
	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		return false;
	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			
			if (dataList.get(position).getTitle() == null) {
				index=position;
				SelectItem(position);
				
			}

		}
	}
}
