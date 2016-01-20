package com.pulego.tshwanesafetymc.ui;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.FillFormatter;
import com.github.mikephil.charting.interfaces.LineDataProvider;
import com.pulego.tshwanesafetymc.R;
import com.pulego.tshwanesafetymc.helper.DatabaseOpenHelper;
import com.pulego.tshwanesafetymc.pojos.Incidents;
import com.pulego.tshwanesafetymc.urlconnectors.UrlConnectHome;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class LineFragment extends Fragment {
	LinearLayout chartLayout;
	   
	private View mcChart;

	private DatabaseOpenHelper db;
	
	private LineChart mChart;
	 private SeekBar mSeekBarX, mSeekBarY;
	private TextView tvX, tvY;
	    
	private Typeface tf;
	 View rootView;
	public LineFragment(){
	}
   @Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	   rootView = inflater.inflate(R.layout.line_chat_layout, container, false);
		// chartLayout = (LinearLayout) rootView.findViewById(R.id.chart);
		 //objArrays=new IncidentsConverted(rootView.getContext());
		 db = new DatabaseOpenHelper(rootView.getContext());
		// openChart(rootView.getContext(),chartLayout);
		 
		 //**************************************************************************************
		 getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		 
		 mChart =(LineChart) rootView.findViewById(R.id.chart1);
	        mChart.setDescription("Weekly Summary of Incidents");
	        mChart.setViewPortOffsets(0, 20, 0, 0);
	        mChart.setBackgroundColor(Color.rgb(104, 241, 175));
	        
	        // enable touch gestures
	        mChart.setTouchEnabled(true);

	        // enable scaling and dragging
	        mChart.setDragEnabled(true);
	        mChart.setScaleEnabled(true);

	        // if disabled, scaling can be done on x- and y-axis separately
	        mChart.setPinchZoom(false);

	        mChart.setDrawGridBackground(true);
	        
	        tf = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Regular.ttf");
	  
	        XAxis x = mChart.getXAxis();
	        x.setEnabled(true);
	        x.setLabelRotationAngle(135);
	        
	        YAxis y = mChart.getAxisLeft();
	        y.setTypeface(tf);
	        y.setLabelCount(7, false);
	        y.setStartAtZero(true);
	        y.setTextColor(Color.BLACK);
	        y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
	        y.setDrawGridLines(false);
	        y.setAxisLineColor(Color.BLACK);
	        
	        mChart.getAxisRight().setEnabled(false);
	       // mChart.setGridBackgroundColor(Color.GRAY);
	        // add data
	        setData(45, 100);
	        
	        mChart.getLegend().setEnabled(false);
	        
	        mChart.animateXY(2000, 2000);

	        // dont forget to refresh the drawing
	        mChart.invalidate();
		 
		 //***************************************************************************************
	return rootView;
}
   private void setData(int count, float range) {
	// TODO Auto-generated method stub
	   UrlConnectHome incidenrtsObj = new UrlConnectHome(getActivity().getApplicationContext());
       ArrayList<Incidents> incidentsList= (ArrayList<Incidents>) incidenrtsObj.populateTblIncidents();
	   
	   ArrayList<String> xVals = new ArrayList<String>();
      /* for (int i = 0; i < count; i++) {
           xVals.add((1990 +i) + "");
       }*/
       
       for(int i=0; i< incidentsList.size();i++){

	    	xVals.add(incidentsList.get(i).getDateOfIncidents());
             // Log.d("Date of Inc", ""+incidentsList.get(i).getDateOfIncidents());
	   }

       ArrayList<Entry> vals1 = new ArrayList<Entry>();

      /* for (int i = 0; i < count; i++) {
           float mult = (range + 1);
           float val = (float) (Math.random() * mult) + 20;// + (float)
                                                     // ((mult *
                                                          // 0.1) / 10);
          vals1.add(new Entry(val, i));
          // vals1.add(new Entry(incidentsList.get(i).getTotalNoOfIncidents() , i));
       }*/
       
       for(int i=0;i<incidentsList.size();i++){
           
	    	vals1.add(new Entry(incidentsList.get(i).getTotalNoOfIncidents(),i));
	    	

	    }
       
       // create a dataset and give it a type
       LineDataSet set1 = new LineDataSet(vals1, "Incidents");
       set1.setDrawCubic(true);
       set1.setCubicIntensity(0.2f);
       set1.setDrawFilled(true);
       set1.setDrawCircles(true); 
       set1.setLineWidth(1.8f);
       set1.setDrawCircleHole(true);
       set1.setCircleSize(4f);
       set1.setCircleColor(Color.DKGRAY);
       set1.setHighLightColor(Color.rgb(244, 117, 117));
       set1.setColor(Color.WHITE);
       set1.setFillColor(Color.GRAY);
       set1.setFillAlpha(100);
       set1.setDrawHorizontalHighlightIndicator(false);
       set1.setFillFormatter(new FillFormatter() {
           @Override
           public float getFillLinePosition(LineDataSet dataSet, LineDataProvider dataProvider) {
               return -10;
           }
       });
       
       // create a data object with the datasets
       LineData data = new LineData(xVals, set1);
       data.setValueTypeface(tf);
       data.setValueTextSize(9f);
       data.setDrawValues(false);

       // set data
       mChart.setData(data);
   }


   private String getYear() {
       /*SimpleDateFormat dateFormat = new SimpleDateFormat(
               "yyyy-MM-dd HH:mm:ss", Locale.getDefault());*/
	   SimpleDateFormat dateFormat = new SimpleDateFormat(
               "yyyy", Locale.getDefault());
       Date date = new Date();
       return dateFormat.format(date);
   }
}
