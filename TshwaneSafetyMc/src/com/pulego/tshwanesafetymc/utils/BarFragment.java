package com.pulego.tshwanesafetymc.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.YAxisLabelPosition;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.pulego.tshwanesafetymc.R;
import com.pulego.tshwanesafetymc.pojos.MyYAxisValueFormatter;
import com.pulego.tshwanesafetymc.pojos.ObjectType;
import com.pulego.tshwanesafetymc.urlconnectors.UrlConnectHome;

import android.app.Fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class BarFragment extends Fragment {
    private View rootView;
	
	private LinearLayout chartLayout;
	   
	private View mcChart; 
	
 
    UrlConnectHome urlConnect;
    
    protected BarChart mChart;
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;

    private Typeface mTf;
    
    protected String[] mMonths = new String[] {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"
    };
    
	public BarFragment(){
		
	}
	   @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		   rootView = inflater.inflate(R.layout.bar_chat_layout, container, false);
		   
		   urlConnect = new UrlConnectHome(rootView.getContext());
		   
		   //chartLayout=(LinearLayout)rootView.findViewById(R.id.barchart);
			 
		   //openChart(rootView.getContext(), chartLayout);
		   //***************************************************************
		   getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		   
		   mChart = (BarChart) rootView.findViewById(R.id.chart3);
	        //mChart.setOnChartValueSelectedListener(this);

	        mChart.setDrawBarShadow(false);
	        mChart.setDrawValueAboveBar(true);

	        mChart.setDescription("Weekly summary of types");

	        // if more than 60 entries are displayed in the chart, no values will be
	        // drawn
	        mChart.setMaxVisibleValueCount(60);

	        // scaling can now only be done on x- and y-axis separately
	        mChart.setPinchZoom(false);

	        mChart.setDrawGridBackground(false);
	        // mChart.setDrawYLabels(false);

	        mTf = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Regular.ttf");

	        XAxis xAxis = mChart.getXAxis();
	        xAxis.setPosition(XAxisPosition.BOTTOM);
	        xAxis.setTypeface(mTf);
	        xAxis.setDrawGridLines(false);
	        xAxis.setSpaceBetweenLabels(2);

	        YAxisValueFormatter custom = new MyYAxisValueFormatter();

	        YAxis leftAxis = mChart.getAxisLeft();
	        leftAxis.setTypeface(mTf);
	        leftAxis.setLabelCount(8, false);
	        leftAxis.setValueFormatter(custom);
	        leftAxis.setPosition(YAxisLabelPosition.OUTSIDE_CHART);
	        leftAxis.setSpaceTop(15f);

	        YAxis rightAxis = mChart.getAxisRight();
	        rightAxis.setDrawGridLines(false);
	        rightAxis.setTypeface(mTf);
	        rightAxis.setLabelCount(8, false);
	        rightAxis.setValueFormatter(custom);
	        rightAxis.setSpaceTop(15f);

	        Legend l = mChart.getLegend();
	        l.setPosition(LegendPosition.BELOW_CHART_LEFT);
	        l.setForm(LegendForm.SQUARE);
	        l.setFormSize(9f);
	        l.setTextSize(11f);
	        l.setXEntrySpace(4f);
	        // l.setExtra(ColorTemplate.VORDIPLOM_COLORS, new String[] { "abc",
	        // "def", "ghj", "ikl", "mno" });
	        // l.setCustom(ColorTemplate.VORDIPLOM_COLORS, new String[] { "abc",
	        // "def", "ghj", "ikl", "mno" });

	        setData(12, 50);
		   
		   //*******************************************************************

		return rootView;
	}
	   

	    private void setData(int count, float range) {
			// TODO Auto-generated method stub
	    	//getting an object of type
	    	List<ObjectType> listTYPE= urlConnect.populateTblType();
	    	
	    	ArrayList<String> xVals = new ArrayList<String>();
	        for (int i = 0; i <  listTYPE.size(); i++) {
	            xVals.add(listTYPE.get(i).getTypeName());
	        }

	        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

	        for (int i = 0; i < listTYPE.size(); i++) {
	           // float mult = (range + 1);
	           // float val = (float) (Math.random() * mult);
	            yVals1.add(new BarEntry(listTYPE.get(i).getTotalType(), i));
	        }

	        BarDataSet set1 = new BarDataSet(yVals1, "DataSet");
	        set1.setBarSpacePercent(35f);

	        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
	        dataSets.add(set1);

	        BarData data = new BarData(xVals, dataSets);
	        data.setValueTextSize(10f);
	        data.setValueTypeface(mTf);

	        mChart.setData(data);
		}
}
