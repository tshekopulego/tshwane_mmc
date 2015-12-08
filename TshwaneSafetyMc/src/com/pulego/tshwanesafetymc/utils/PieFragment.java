package com.pulego.tshwanesafetymc.utils;

import java.util.ArrayList;
import java.util.List;


import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.pulego.tshwanesafetymc.R;
import com.pulego.tshwanesafetymc.pojos.ObjectStatus;
import com.pulego.tshwanesafetymc.urlconnectors.UrlConnectHome;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

public class PieFragment extends Fragment {
	
	private View rootView;
	
	private LinearLayout chartLayout;
	   
	private View mcChart;
	UrlConnectHome urlconnect;
	
	private PieChart mChart;
    private float[] yData = {5,10,15,30,40};
    private String[] xData ={"Sony","Huawei","LG","Apple","Samsung"};
	
public PieFragment(){
	
}
  @Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	 rootView = inflater.inflate(R.layout.pie_chat_layout, container, false);
	// chartLayout=(LinearLayout)rootView.findViewById(R.id.piechart);
	 urlconnect = new UrlConnectHome(rootView.getContext()); 
	// openChart(rootView.getContext(), chartLayout);
	 //*********************************************************************
	 getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
             WindowManager.LayoutParams.FLAG_FULLSCREEN);
	 

     mChart=(PieChart) rootView.findViewById(R.id.chart2);
     mChart.setUsePercentValues(true);
     mChart.setDescription("Status weekly summary ");
     mChart.setExtraOffsets(5, 10, 5, 5);
     
     mChart.setDrawHoleEnabled(true);
     mChart.setHoleColorTransparent(true);
     mChart.setHoleRadius(35);
    mChart.setTransparentCircleRadius(10);
    // mChart.setTransparentCircleAlpha(110);
     //mChart.setHoleRadius(58f);
    // mChart.setTransparentCircleRadius(61f);
     
     mChart.setRotationAngle(0);
     mChart.setRotationEnabled(true);
     
     mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
			
			@Override
			public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
				// TODO Auto-generated method stub
				if(e==null){
					return;
				}else{
					Toast.makeText(getActivity().getApplicationContext(), xData[e.getXIndex()]+" "+e.getVal(), Toast.LENGTH_SHORT).show();
				}
			}
			
			@Override
			public void onNothingSelected() {
				// TODO Auto-generated method stub
				
			}

		});
     //add data
     addData();
     
     //customize
     Legend l =mChart.getLegend();
     l.setPosition(LegendPosition.RIGHT_OF_CHART);
     l.setXEntrySpace(7);
     l.setYEntrySpace(5);
     
	 
	 //*********************************************************************
	return rootView;
}
  
  private void addData() {
		// TODO Auto-generated method stub
	  List<ObjectStatus> listSATUS=urlconnect.populateTblStatus();
	  
	/*  for (int i = 0; i < listSATUS.size(); i++) {
          // Adding a slice with its values and name to the Pie Chart
          distributionSeries.add(listSATUS.get(i).getStatusName(), listSATUS.get(i).getStatusTotal());
      }*/
	  
		ArrayList<Entry> yVals1 =new ArrayList<Entry>();
		
		for(int i=0; i < listSATUS.size() ;i++)
			yVals1.add(new Entry(listSATUS.get(i).getStatusTotal(), i));
		
		ArrayList<String> xVals1 = new ArrayList<String>();
		for(int i=0; i < listSATUS.size() ; i++)
			xVals1.add(listSATUS.get(i).getStatusName());
		
		PieDataSet dataSet = new PieDataSet(yVals1, "Status");
	    dataSet.setSliceSpace(3);
	    dataSet.setSelectionShift(5);
	    
	    //add colors
	    ArrayList<Integer> colors =new ArrayList<Integer>();
	    
	    for(int c : ColorTemplate.VORDIPLOM_COLORS)
	    	colors.add(c);
	    
	    for(int c : ColorTemplate.JOYFUL_COLORS)
	    	colors.add(c);
	    
	    for(int c : ColorTemplate.COLORFUL_COLORS)
	    	colors.add(c);
	    
	    for(int c : ColorTemplate.LIBERTY_COLORS)
	    	colors.add(c);
	    
	    for(int c : ColorTemplate.PASTEL_COLORS)
	    	colors.add(c);
	    
	    colors.add(ColorTemplate.getHoloBlue());
	    dataSet.setColors(colors);
	    
	    PieData data = new PieData(xVals1,dataSet);
	    data.setValueFormatter(new PercentFormatter());
	    data.setValueTextSize(11f);
	    data.setValueTextColor(Color.GRAY);
	    
	    mChart.setData(data);
	    
	    mChart.highlightValue(null);
	    
	    //update pie
	    mChart.invalidate();
	    
	    
	}
 
}
