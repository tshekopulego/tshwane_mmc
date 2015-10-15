package com.pulego.tshwanesafetymc.utils;


import java.util.Date;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.BasicStroke;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import com.pulego.tshwanesafetymc.R;
import com.pulego.tshwanesafetymc.convertors.IncidentsConverted;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class LineFragment extends Fragment {
	LinearLayout chartLayout;
	   
	private View mChart;
	private String[] mMonth = new String[] {
			"Jan", "Feb" , "Mar", "Apr", "May", "Jun",
			"Jul", "Aug" , "Sep", "Oct", "Nov", "Dec"};
	//creating an object that will return two arrays, array of integers and arrays of strings
	private IncidentsConverted objArrays;
	
	public LineFragment(){
	}
   @Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	   View rootView = inflater.inflate(R.layout.line_chat_layout, container, false);
		 chartLayout = (LinearLayout) rootView.findViewById(R.id.chart);
		 objArrays=new IncidentsConverted(rootView.getContext());
		 openChart(rootView.getContext(),chartLayout);
	return rootView;
}
   public void openChart(Context c, LinearLayout l) {
		// TODO Auto-generated method stub

		    	//int[] x = { 0,1,2,3,4,5,6,7, 8, 9, 10, 11 };
		    	int[] x = { 0,1,2,3,4,5,6 };

		    	//int[] income = { 2000,2500,2700,3000,2800,3500,3700,3800, 0,0,0,0};

		    	//int[] expense = {2200, 2700, 2900, 2800, 2600, 3000, 3300, 3400, 0, 0, 0, 0 };
                int[] incidents = objArrays.getArrayIncidentsTotal();
                String[] mDate= objArrays.getArrayIncidentsDate();
		    	// Creating an XYSeries for Income
		    	//XYSeries incomeSeries = new XYSeries("Income");
		    	XYSeries incidentsSeries = new XYSeries("Incidents");
		    	// Creating an XYSeries for Expense

		    	//XYSeries expenseSeries = new XYSeries("Expense");

		    	// Adding data to Income and Expense Series

		    	for(int i=0;i<x.length;i++){

		    	incidentsSeries.add(i, incidents[i]);
		    	//incomeSeries.add(i,income[i]);

		    	//expenseSeries.add(i,expense[i]);

		    	}

		    	 

		    	// Creating a dataset to hold each series
                
		    	XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

		    	// Adding Income Series to the dataset
		    	dataset.addSeries(incidentsSeries);
		    	//dataset.addSeries(incomeSeries);

		    	// Adding Expense Series to dataset

		    	//dataset.addSeries(expenseSeries);
              
		    	// Creating XYSeriesRenderer to customize incomeSeries
		    	XYSeriesRenderer incidentsRenderer = new XYSeriesRenderer();

		    	incidentsRenderer.setColor(Color.BLUE); //color of the graph set to cyan

		    	incidentsRenderer.setFillPoints(true);

		    	incidentsRenderer.setLineWidth(2f);

		    	incidentsRenderer.setDisplayChartValues(true);

		    	//setting chart value distance

		    	incidentsRenderer.setDisplayChartValuesDistance(10);

		    	//setting line graph point style to circle

		    	incidentsRenderer.setPointStyle(PointStyle.CIRCLE);

		    	//setting stroke of the line chart to solid

		    	incidentsRenderer.setStroke(BasicStroke.SOLID);
		    	
		    	/*XYSeriesRenderer incomeRenderer = new XYSeriesRenderer();

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

		    	expenseRenderer.setStroke(BasicStroke.SOLID);*/

		    	// Creating a XYMultipleSeriesRenderer to customize the whole chart
               
		    	XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
            
		    	multiRenderer.setXLabels(0);
		    	
		    	multiRenderer.setBackgroundColor(Color.BLACK);

		    	multiRenderer.setChartTitle("Weekly Summary of Incidents");
          
		    //	Date d=new Date();
		    	multiRenderer.setXTitle("Year 2015");

		    	multiRenderer.setYTitle("Number of Incidents");

		    	 

		    	/***

		    	* Customizing graphs

		    	*/

		    	//setting text size of the title

		    	multiRenderer.setChartTitleTextSize(16);

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

		    	multiRenderer.setInScroll(true);

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

		    	multiRenderer.setYAxisMax(60);

		    	//setting used to move the graph on xaxiz to .5 to the right

		    	multiRenderer.setXAxisMin(-0.5);

		    	//setting used to move the graph on xaxiz to .5 to the right

		    	multiRenderer.setXAxisMax(7);

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

		    	multiRenderer.addXTextLabel(i, mDate[i]);

		    	}

		    	// Adding incomeRenderer and expenseRenderer to multipleRenderer

		    	// Note: The order of adding dataseries to dataset and renderers to multipleRenderer

		    	// should be same
		    	multiRenderer.addSeriesRenderer(incidentsRenderer);
		    	//multiRenderer.addSeriesRenderer(incomeRenderer);

		    	//multiRenderer.addSeriesRenderer(expenseRenderer);

		    	//this part is used to display graph on the xml
		    	LinearLayout chartContainer = (LinearLayout) l;

		    	//remove any views before u paint the chart
		    	chartContainer.removeAllViews();

		    	//drawing bar chart
		    	mChart = ChartFactory.getLineChartView(c, dataset, multiRenderer);

		    	//adding the view to the linearlayout
		    	chartContainer.addView(mChart);
	}

   
}
