package com.pulego.tshwanesafetymc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import com.pulego.tshwanesafetymc.R;
import com.pulego.tshwanesafetymc.pojos.ObjectType;
import com.pulego.tshwanesafetymc.urlconnectors.UrlConnectHome;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class BarFragment extends Fragment {
    private View rootView;
	
	private LinearLayout chartLayout;
	   
	private View mChart; 
	
    private String[] mMonth = new String[] { "Jan", "Feb", "Mar", "Apr", "May",
            "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
 
    UrlConnectHome urlConnect;
	public BarFragment(){
		
	}
	   @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		   rootView = inflater.inflate(R.layout.bar_chat_layout, container, false);
		   
		   urlConnect = new UrlConnectHome(rootView.getContext());
		   
		   chartLayout=(LinearLayout)rootView.findViewById(R.id.barchart);
			 
		   openChart(rootView.getContext(), chartLayout);

		return rootView;
	}
	   private void openChart(Context c, LinearLayout l) {
		   
		   
	       // int[] x = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
		   
	      /*  int[] income = { 2000, 2500, 2700, 3000, 2800, 3500, 3700, 3800, 0, 0,
	                0, 0 };
	        int[] expense = { 2200, 2700, 2900, 2800, 2600, 3000, 3300, 3400, 0, 0,
	                0, 0 };
	        int[] x = { 0, 1, 2, 3, 4, 5, 6 };*/
	        List<ObjectType> listTYPE= urlConnect.populateTblType();
	        
	        // Creating an XYSeries for Income
	       // XYSeries incomeSeries = new XYSeries("Income");
	        XYSeries typeSeries = new XYSeries("Type");
	        // Creating an XYSeries for Expense
	       // XYSeries expenseSeries = new XYSeries("Expense");
	        // Adding data to Income and Expense Series
	        for (int i = 0; i < listTYPE.size(); i++) {
	        	typeSeries.add(i, listTYPE.get(i).getTotalType());
	          //  expenseSeries.add(i, expense[i]);
	        }
	 
	        // Creating a dataset to hold each series
	        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
	        // Adding Income Series to the dataset
	        dataset.addSeries(typeSeries);
	        // Adding Expense Series to dataset
	        //dataset.addSeries(expenseSeries);
	 
	        // Creating XYSeriesRenderer to customize incomeSeries
	       // XYSeriesRenderer incomeRenderer = new XYSeriesRenderer();
	        XYSeriesRenderer typeRenderer = new XYSeriesRenderer();
	        typeRenderer.setColor(Color.CYAN); // color of the graph set to cyan
	        typeRenderer.setFillPoints(true);
	        typeRenderer.setLineWidth(2);
	        typeRenderer.setDisplayChartValues(true);
	        typeRenderer.setDisplayChartValuesDistance(10); // setting chart value
	                                                            // distance
	 
	        // Creating XYSeriesRenderer to customize expenseSeries
	       /* XYSeriesRenderer expenseRenderer = new XYSeriesRenderer();
	        expenseRenderer.setColor(Color.GREEN);
	        expenseRenderer.setFillPoints(true);
	        expenseRenderer.setLineWidth(2);
	        expenseRenderer.setDisplayChartValues(true);*/
	 
	        // Creating a XYMultipleSeriesRenderer to customize the whole chart
	        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
	        multiRenderer
	                .setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);
	        multiRenderer.setXLabels(0);
	        multiRenderer.setChartTitle("Weeky Trending Type of Incidents");
	    
	        multiRenderer.setXTitle("Name of Types");
	        multiRenderer.setYTitle("Total Number of Types");
	        multiRenderer.setBackgroundColor(Color.BLACK);
	        /***
	         * Customizing graphs
	         */
	        // setting text size of the title
	        multiRenderer.setChartTitleTextSize(13);
	        // setting text size of the axis title
	        multiRenderer.setAxisTitleTextSize(12);
	        // setting text size of the graph lable
	        multiRenderer.setLabelsTextSize(12);
	        // setting zoom buttons visiblity
	        multiRenderer.setZoomButtonsVisible(false);
	        // setting pan enablity which uses graph to move on both axis
	        multiRenderer.setPanEnabled(false, false);
	        // setting click false on graph
	        multiRenderer.setClickEnabled(false);
	        // setting zoom to false on both axis
	        multiRenderer.setZoomEnabled(false, false);
	        // setting lines to display on y axis
	        multiRenderer.setShowGridY(false);
	        // setting lines to display on x axis
	        multiRenderer.setShowGridX(false);
	        // setting legend to fit the screen size
	        multiRenderer.setFitLegend(true);
	        // setting displaying line on grid
	        multiRenderer.setShowGrid(false);
	        // setting zoom to false
	        multiRenderer.setZoomEnabled(false);
	        // setting external zoom functions to false
	        multiRenderer.setExternalZoomEnabled(false);
	        // setting displaying lines on graph to be formatted(like using
	        // graphics)
	        multiRenderer.setAntialiasing(true);
	        // setting to in scroll to false
	        multiRenderer.setInScroll(false);
	        // setting to set legend height of the graph
	        multiRenderer.setLegendHeight(30);
	        // setting x axis label align
	        multiRenderer.setXLabelsAlign(Align.CENTER);
	        // setting y axis label to align
	        multiRenderer.setYLabelsAlign(Align.LEFT);
	        // setting text style
	        multiRenderer.setTextTypeface("sans_serif", Typeface.NORMAL);
	        // setting no of values to display in y axis
	        multiRenderer.setYLabels(10);
	        // setting y axis max value, Since i'm using static values inside the
	        // graph so i'm setting y max value to 4000.
	        // if you use dynamic values then get the max y value and set here
	        multiRenderer.setYAxisMax(20);
	        // setting used to move the graph on xaxiz to .5 to the right
	        multiRenderer.setXAxisMin(-0.5);
	        // setting max values to be display in x axis
	        multiRenderer.setXAxisMax(7);
	        // setting bar size or space between two bars
	        multiRenderer.setBarSpacing(0.5);
	        // Setting background color of the graph to transparent
	        multiRenderer.setBackgroundColor(Color.BLACK);
	        
	        multiRenderer.setMarginsColor(Color.WHITE);
	        // Setting margin color of the graph to transparent
	       // multiRenderer.setMarginsColor(getResources().getColor(
	            //    R.color.transparent_background));
	        multiRenderer.setApplyBackgroundColor(true);
	        multiRenderer.setXLabelsAngle(300);
	        multiRenderer.setBarWidth(10);
	        // setting the margin size for the graph in the order top, left, bottom,
	        // right
	        multiRenderer.setMargins(new int[] { 30, 30, 30, 30 });
	 
	        for (int i = 0; i <listTYPE.size(); i++) {
	            multiRenderer.addXTextLabel(i, listTYPE.get(i).getTypeName());
	        }
	 
	        // Adding incomeRenderer and expenseRenderer to multipleRenderer
	        // Note: The order of adding dataseries to dataset and renderers to
	        // multipleRenderer
	        // should be same
	        multiRenderer.addSeriesRenderer(typeRenderer);
	        //multiRenderer.addSeriesRenderer(expenseRenderer);
	 
	        // this part is used to display graph on the xml
	        LinearLayout chartContainer = (LinearLayout) l;
	        // remove any views before u paint the chart
	        chartContainer.removeAllViews();
	        // drawing bar chart
	        mChart = ChartFactory.getBarChartView(c, dataset,
	                multiRenderer, Type.DEFAULT);
	 
	        // adding the view to the linearlayout
	        chartContainer.addView(mChart);
	 
	    }
	 
}
