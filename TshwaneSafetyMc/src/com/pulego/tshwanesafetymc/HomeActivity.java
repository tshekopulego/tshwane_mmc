package com.pulego.tshwanesafetymc;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.BasicStroke;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import com.pulego.tshwanesafetymc.utils.InterfaceUtils;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Paint.Align;
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

public class HomeActivity extends Activity implements InterfaceUtils{

	private View mChart;
	private String[] mMonth = new String[] {
			"Jan", "Feb" , "Mar", "Apr", "May", "Jun",
			"Jul", "Aug" , "Sep", "Oct", "Nov", "Dec"};
	
	
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	CustomDrawerAdapter adapter;

	List<DrawerItem> dataList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		// Initializing
				dataList = new ArrayList<DrawerItem>();
				mTitle = mDrawerTitle = getTitle();
				mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
				mDrawerList = (ListView) findViewById(R.id.left_drawer);

				mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
						GravityCompat.START);

				// Add Drawer Item to dataList
				dataList.add(new DrawerItem(true)); // adding a spinner to the list

				dataList.add(new DrawerItem("Main Options")); // adding a header to the list
				dataList.add(new DrawerItem("Incidents", R.drawable.not_));
				dataList.add(new DrawerItem("Strength Report", R.drawable.ic_report));
				dataList.add(new DrawerItem("Message", R.drawable.ic_action_email));
				dataList.add(new DrawerItem("Calendar", R.drawable.ic_action_gamepad));
				

				 dataList.add(new DrawerItem("My Favorites"));// adding a header to the list
				dataList.add(new DrawerItem("Search", R.drawable.ic_action_search));
				dataList.add(new DrawerItem("My Profile", R.drawable.user1));
				dataList.add(new DrawerItem("Camara", R.drawable.ic_action_camera));
				//dataList.add(new DrawerItem("Video", R.drawable.ic_action_video));
				dataList.add(new DrawerItem("Groups", R.drawable.ic_action_group));
				dataList.add(new DrawerItem("Import & Export",
						R.drawable.ic_action_import_export));

				 dataList.add(new DrawerItem("Other Option")); // adding a header to the list
				dataList.add(new DrawerItem("About", R.drawable.ic_action_about));
				dataList.add(new DrawerItem("Settings", R.drawable.ic_action_settings));
				dataList.add(new DrawerItem("Help", R.drawable.ic_action_help));

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
						getActionBar().setTitle(mTitle);
						invalidateOptionsMenu(); // creates call to
													// onPrepareOptionsMenu()
					}

					public void onDrawerOpened(View drawerView) {
						getActionBar().setTitle(mDrawerTitle);
						invalidateOptionsMenu(); // creates call to
													// onPrepareOptionsMenu()
					}
				};

				mDrawerLayout.setDrawerListener(mDrawerToggle);

				if (savedInstanceState == null) {

					if (dataList.get(0).isSpinner()
							& dataList.get(1).getTitle() != null) {
						SelectItem(2);
					} else if (dataList.get(0).getTitle() != null) {
						SelectItem(1);
					} else {
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

		case 2:
			fragment = new FragmentThree();
			
			//fragment.setArguments(args);
			frgManager.beginTransaction().replace(R.id.content_frame, fragment)
			.commit();
			break;
		case 3:
			fragment = new FragmentOne();
			args.putString(FragmentOne.ITEM_NAME, dataList.get(possition)
					.getItemName());
			args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(possition)
					.getImgResID());
			
			fragment.setArguments(args);
			frgManager.beginTransaction().replace(R.id.content_frame, fragment)
			.commit();
			break;
		case 4:
			fragment = new FragmentTwo();
			args.putString(FragmentTwo.ITEM_NAME, dataList.get(possition)
					.getItemName());
			args.putInt(FragmentTwo.IMAGE_RESOURCE_ID, dataList.get(possition)
					.getImgResID());
			
			fragment.setArguments(args);
			frgManager.beginTransaction().replace(R.id.content_frame, fragment)
			.commit();
			break;
		case 5:
			fragment = new FragmentThree();
			
			//fragment.setArguments(args);
			frgManager.beginTransaction().replace(R.id.content_frame, fragment)
			.commit();
			break;
		case 7:
			fragment = new FragmentTwo();
			args.putString(FragmentTwo.ITEM_NAME, dataList.get(possition)
					.getItemName());
			args.putInt(FragmentTwo.IMAGE_RESOURCE_ID, dataList.get(possition)
					.getImgResID());
			
			fragment.setArguments(args);
			frgManager.beginTransaction().replace(R.id.content_frame, fragment)
			.commit();
			break;
		case 8:
			fragment = new FragmentThree();
			
			//fragment.setArguments(args);
			frgManager.beginTransaction().replace(R.id.content_frame, fragment)
			.commit();
			break;
		case 9:
			fragment = new FragmentOne();
			args.putString(FragmentOne.ITEM_NAME, dataList.get(possition)
					.getItemName());
			args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(possition)
					.getImgResID());
			
			fragment.setArguments(args);
			frgManager.beginTransaction().replace(R.id.content_frame, fragment)
			.commit();
			break;
		case 10:
			fragment = new FragmentTwo();
			args.putString(FragmentTwo.ITEM_NAME, dataList.get(possition)
					.getItemName());
			args.putInt(FragmentTwo.IMAGE_RESOURCE_ID, dataList.get(possition)
					.getImgResID());
			
			fragment.setArguments(args);
			frgManager.beginTransaction().replace(R.id.content_frame, fragment)
			.commit();
			break;
		case 11:
			fragment = new FragmentThree();
			
			//fragment.setArguments(args);
			//FragmentManager frgManager = getFragmentManager();
			frgManager.beginTransaction().replace(R.id.content_frame, fragment)
					.commit();
			break;
		case 12:
			fragment = new FragmentOne();
			args.putString(FragmentOne.ITEM_NAME, dataList.get(possition)
					.getItemName());
			args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(possition)
					.getImgResID());
			
			fragment.setArguments(args);
			//FragmentManager frgManager = getFragmentManager();
			frgManager.beginTransaction().replace(R.id.content_frame, fragment)
					.commit();
			break;
		case 14:
			fragment = new FragmentThree();
			
			//fragment.setArguments(args);
			//FragmentManager frgManager = getFragmentManager();
			frgManager.beginTransaction().replace(R.id.content_frame, fragment)
					.commit();
			break;	
		case 15:
			fragment = new FragmentOne();
			args.putString(FragmentOne.ITEM_NAME, dataList.get(possition)
					.getItemName());
			args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(possition)
					.getImgResID());
			
			fragment.setArguments(args);
			//FragmentManager frgManager = getFragmentManager();
			frgManager.beginTransaction().replace(R.id.content_frame, fragment)
					.commit();
			break;
		case 16:
			fragment = new FragmentTwo();
			args.putString(FragmentTwo.ITEM_NAME, dataList.get(possition)
					.getItemName());
			args.putInt(FragmentTwo.IMAGE_RESOURCE_ID, dataList.get(possition)
					.getImgResID());
			
			fragment.setArguments(args);
			//FragmentManager frgManager = getFragmentManager();
			frgManager.beginTransaction().replace(R.id.content_frame, fragment)
					.commit();
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
				SelectItem(position);
			}

		}
	}

	@Override
	public void openChart(Context c, LinearLayout l) {
		// TODO Auto-generated method stub

		    	int[] x = { 0,1,2,3,4,5,6,7, 8, 9, 10, 11 };

		    	int[] income = { 2000,2500,2700,3000,2800,3500,3700,3800, 0,0,0,0};

		    	int[] expense = {2200, 2700, 2900, 2800, 2600, 3000, 3300, 3400, 0, 0, 0, 0 };

		    	// Creating an XYSeries for Income
		    	XYSeries incomeSeries = new XYSeries("Income");

		    	// Creating an XYSeries for Expense

		    	XYSeries expenseSeries = new XYSeries("Expense");

		    	// Adding data to Income and Expense Series

		    	for(int i=0;i<x.length;i++){

		    	incomeSeries.add(i,income[i]);

		    	expenseSeries.add(i,expense[i]);

		    	}

		    	 

		    	// Creating a dataset to hold each series

		    	XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

		    	// Adding Income Series to the dataset

		    	dataset.addSeries(incomeSeries);

		    	// Adding Expense Series to dataset

		    	dataset.addSeries(expenseSeries);

		    	// Creating XYSeriesRenderer to customize incomeSeries

		    	XYSeriesRenderer incomeRenderer = new XYSeriesRenderer();

		    	incomeRenderer.setColor(Color.CYAN); //color of the graph set to cyan

		    	incomeRenderer.setFillPoints(true);

		    	incomeRenderer.setLineWidth(2f);

		    	incomeRenderer.setDisplayChartValues(true);

		    	//setting chart value distance

		    	incomeRenderer.setDisplayChartValuesDistance(10);

		    	//setting line graph point style to circle

		    	incomeRenderer.setPointStyle(PointStyle.CIRCLE);

		    	//setting stroke of the line chart to solid

		    	incomeRenderer.setStroke(BasicStroke.SOLID);

		    	// Creating XYSeriesRenderer to customize expenseSeries

		    	XYSeriesRenderer expenseRenderer = new XYSeriesRenderer();

		    	expenseRenderer.setColor(Color.GREEN);

		    	expenseRenderer.setFillPoints(true);

		    	expenseRenderer.setLineWidth(2f);

		    	expenseRenderer.setDisplayChartValues(true);

		    	//setting line graph point style to circle

		    	expenseRenderer.setPointStyle(PointStyle.SQUARE);

		    	//setting stroke of the line chart to solid

		    	expenseRenderer.setStroke(BasicStroke.SOLID);

		    	// Creating a XYMultipleSeriesRenderer to customize the whole chart

		    	XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();

		    	multiRenderer.setXLabels(0);

		    	multiRenderer.setChartTitle("Income vs Expense Chart");
           
		    	multiRenderer.setXTitle("Year 2015");

		    	multiRenderer.setYTitle("Amount in Dollars");

		    	 

		    	/***

		    	* Customizing graphs

		    	*/

		    	//setting text size of the title

		    	multiRenderer.setChartTitleTextSize(14);

		    	//setting text size of the axis title

		    	multiRenderer.setAxisTitleTextSize(12);

		    	//setting text size of the graph lable

		    	multiRenderer.setLabelsTextSize(12);

		    	//setting zoom buttons visiblity
		    	multiRenderer.setZoomButtonsVisible(false);

		    	//setting pan enablity which uses graph to move on both axis
		    	multiRenderer.setPanEnabled(false, false);

		    	//setting click false on graph
		    	multiRenderer.setClickEnabled(false);

		    	//setting zoom to false on both axis
		    	multiRenderer.setZoomEnabled(false, false);

		    	//setting lines to display on y axis
		    	multiRenderer.setShowGridY(true);

		    	//setting lines to display on x axis

		    	multiRenderer.setShowGridX(true);

		    	//setting legend to fit the screen size

		    	multiRenderer.setFitLegend(true);

		    	//setting displaying line on grid

		    	multiRenderer.setShowGrid(true);

		    	//setting zoom to false

		    	multiRenderer.setZoomEnabled(false);

		    	//setting external zoom functions to false

		    	multiRenderer.setExternalZoomEnabled(false);

		    	//setting displaying lines on graph to be formatted(like using graphics)

		    	multiRenderer.setAntialiasing(true);

		    	//setting to in scroll to false

		    	multiRenderer.setInScroll(false);

		    	//setting to set legend height of the graph

		    	multiRenderer.setLegendHeight(30);

		    	//setting x axis label align

		    	multiRenderer.setXLabelsAlign(Align.CENTER);

		    	//setting y axis label to align

		    	multiRenderer.setYLabelsAlign(Align.LEFT);

		    	//setting text style

		    	multiRenderer.setTextTypeface("sans_serif", Typeface.NORMAL);

		    	//setting no of values to display in y axis

		    	multiRenderer.setYLabels(10);

		    	// setting y axis max value, Since i'm using static values inside the graph so i'm setting y max value to 4000.

		    	// if you use dynamic values then get the max y value and set here

		    	multiRenderer.setYAxisMax(4000);

		    	//setting used to move the graph on xaxiz to .5 to the right

		    	multiRenderer.setXAxisMin(-0.5);

		    	//setting used to move the graph on xaxiz to .5 to the right

		    	multiRenderer.setXAxisMax(11);

		    	//setting bar size or space between two bars

		    	//multiRenderer.setBarSpacing(0.5);

		    	//Setting background color of the graph to transparent

		    	multiRenderer.setBackgroundColor(Color.TRANSPARENT);

		    	//Setting margin color of the graph to transparent
		    	multiRenderer.setMarginsColor(getResources().getColor(R.color.transparent_background));

		    	multiRenderer.setApplyBackgroundColor(true);

		    	multiRenderer.setScale(2f);

		    	//setting x axis point size
		    	multiRenderer.setPointSize(4f);

		    	//setting the margin size for the graph in the order top, left, bottom, right
		    	multiRenderer.setMargins(new int[]{30, 30, 30, 30});

		    	for(int i=0; i< x.length;i++){

		    	multiRenderer.addXTextLabel(i, mMonth[i]);

		    	}

		    	// Adding incomeRenderer and expenseRenderer to multipleRenderer

		    	// Note: The order of adding dataseries to dataset and renderers to multipleRenderer

		    	// should be same
		    	multiRenderer.addSeriesRenderer(incomeRenderer);

		    	multiRenderer.addSeriesRenderer(expenseRenderer);

		    	//this part is used to display graph on the xml
		    	LinearLayout chartContainer = (LinearLayout) l;

		    	//remove any views before u paint the chart
		    	chartContainer.removeAllViews();

		    	//drawing bar chart
		    	mChart = ChartFactory.getLineChartView(c, dataset, multiRenderer);

		    	//adding the view to the linearlayout
		    	chartContainer.addView(mChart);
	}

	@Override
	public void openBar(Context c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openPie(Context c) {
		// TODO Auto-generated method stub
		
	}
}
