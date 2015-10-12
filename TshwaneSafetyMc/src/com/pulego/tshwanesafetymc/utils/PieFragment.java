package com.pulego.tshwanesafetymc.utils;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import com.pulego.tshwanesafetymc.R;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class PieFragment extends Fragment {
	
	private View rootView;
	
	private LinearLayout chartLayout;
	   
	private View mChart;
	
public PieFragment(){
	
}
  @Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	 rootView = inflater.inflate(R.layout.pie_chat_layout, container, false);
	 chartLayout=(LinearLayout)rootView.findViewById(R.id.piechart);
	 
	 openChart(rootView.getContext(), chartLayout);
	 
	return rootView;
}
  private void openChart(Context c, LinearLayout l) {
	  
      // Pie Chart Section Names
      String[] code = new String[] { "Froyo", "Gingerbread",
              "IceCream Sandwich", "Jelly Bean", "KitKat" };

      // Pie Chart Section Value
      double[] distribution = { 0.5, 9.1, 7.8, 45.5, 33.9 };

      // Color of each Pie Chart Sections
      int[] colors = { Color.BLUE, Color.MAGENTA, Color.GREEN, Color.CYAN,
              Color.RED };

      // Instantiating CategorySeries to plot Pie Chart
      CategorySeries distributionSeries = new CategorySeries(
              " Status of Incidents");
      for (int i = 0; i < distribution.length; i++) {
          // Adding a slice with its values and name to the Pie Chart
          distributionSeries.add(code[i], distribution[i]);
      }

      // Instantiating a renderer for the Pie Chart
      DefaultRenderer defaultRenderer = new DefaultRenderer();
      for (int i = 0; i < distribution.length; i++) {
          SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
          seriesRenderer.setColor(colors[i]);
          seriesRenderer.setDisplayChartValues(true);
          //Adding colors to the chart
          defaultRenderer.setBackgroundColor(Color.BLACK);
          defaultRenderer.setApplyBackgroundColor(true);
          // Adding a renderer for a slice
          defaultRenderer.addSeriesRenderer(seriesRenderer);
          

      }

      defaultRenderer
              .setChartTitle("Status of Incidents");
      defaultRenderer.setChartTitleTextSize(14);
      defaultRenderer.setZoomButtonsVisible(false);

      // Creating an intent to plot bar chart using dataset and
      // multipleRenderer
      // Intent intent = ChartFactory.getPieChartIntent(getBaseContext(),
      // distributionSeries , defaultRenderer, "AChartEnginePieChartDemo");

      // Start Activity
      // startActivity(intent);

      // this part is used to display graph on the xml
      LinearLayout chartContainer = (LinearLayout) l;
      // remove any views before u paint the chart
      chartContainer.removeAllViews();
      // drawing pie chart
      mChart = ChartFactory.getPieChartView(c,
              distributionSeries, defaultRenderer);
      // adding the view to the linearlayout
      chartContainer.addView(mChart);

  }
}
