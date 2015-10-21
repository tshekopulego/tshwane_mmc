package com.pulego.tshwanesafetymc.utils;

import java.util.ArrayList;
import java.util.HashMap;

import com.pulego.tshwanesafetymc.R;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class StrengthReportFragment extends Fragment {
    private View rootView;
    private ListView listStrengthReport;
    private SimpleAdapter mSchedule;
    
	public StrengthReportFragment() {
		super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootView=inflater.inflate(R.layout.fragment_strength_layout, container, false);
		listStrengthReport=(ListView)rootView.findViewById(R.id.listStrengthReport);
		return rootView;
	}
	public void populateStrengthReportListView(Context c){
		ListView list = (ListView) listStrengthReport;
		 
		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("train", "101");
		map.put("from", "6:30 AM");
		map.put("to", "7:40 AM");
		map.put("car", "GTI 6 GOLD");
		mylist.add(map);
		map = new HashMap<String, String>();
		map.put("train", "103(x)");
		map.put("from", "6:35 AM");
		map.put("to", "7:45 AM");
		map.put("car", "GTI 7 GOLD");
		mylist.add(map);
		// ...
		mSchedule = new SimpleAdapter(c, mylist, R.layout.listview_layout_design,
		            new String[] {"train", "from", "to","car"}, new int[] {R.id.txtshiftdate, R.id.txtNoPerson, R.id.txtNoMotorCycle,R.id.txtNoCar});
		list.setAdapter(mSchedule);
	}
   
}
