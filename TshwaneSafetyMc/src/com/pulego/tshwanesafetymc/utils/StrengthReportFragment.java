package com.pulego.tshwanesafetymc.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.pulego.tshwanesafetymc.R;
import com.pulego.tshwanesafetymc.helper.DatabaseOpenHelper;
import com.pulego.tshwanesafetymc.pojos.DiploymentCalc;
import com.pulego.tshwanesafetymc.pojos.StrengthReport;
import com.pulego.tshwanesafetymc.urlconnectors.UrlConnectStrengthReport;

import android.app.ActionBar;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class StrengthReportFragment extends Fragment {
    private View rootView;
    private ListView listStrengthReport;
    private SimpleAdapter mSchedule;
    
    DatabaseOpenHelper db;
    
	private ProgressDialog pDialog; 
	
	private Dialog dialog;
    
	UrlConnectStrengthReport urlcreatereport;
	
	public StrengthReportFragment() {
		super();	
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		rootView=inflater.inflate(R.layout.fragment_strength_layout, container, false);

		ActionBar actionBar = getActivity().getActionBar();
		//actionBar.setDisplayOptions(
		//	    ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
		if(actionBar.isShowing()){
		 actionBar.removeAllTabs();
		// actionBar.setDisplayOptions(
			//	    ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
		// actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		}
		 actionBar.setDisplayShowCustomEnabled(false);
        // Screen handling while hiding ActionBar icon.
         actionBar.setDisplayShowHomeEnabled(true);
         
        // Screen handling while hiding Actionbar title.
        actionBar.setDisplayShowTitleEnabled(true);
        //actionBar.setHomeButtonEnabled(isVisible());
        // Creating ActionBar tabs.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
 
		listStrengthReport=(ListView)rootView.findViewById(R.id.listStrengthReport);
		
		 urlcreatereport = new UrlConnectStrengthReport(rootView.getContext());
		
		db = new DatabaseOpenHelper(rootView.getContext());
		
		//load data on the list view
		populateStrengthReportListView(rootView.getContext());
		
		//++++++++++++++++++++++++++++++++++++++++++++++
			/*	try{
				  new LoadStrengthReport().execute();
				}catch(Exception e){
					Toast.makeText(getActivity(), "Error occured :"+e.getMessage(), Toast.LENGTH_SHORT);
					Log.d("Error Occured", e.getMessage());
				}*/
				//+++++++++++++++++++++++++++++++++++++++++++++
		
		
		return rootView;
	}
	public void populateStrengthReportListView(Context c){
		final ListView list = (ListView) listStrengthReport;
		 
		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
		
		HashMap<String, String> map = new HashMap<String, String>();
	
		//List<StrengthReport> strengthReportList= db.getAllStrengthReportRecords();
		
		List<DiploymentCalc> diploymentCalcList = db.getDiploymentCalcRecords();
		if(diploymentCalcList !=null){
		Log.d("Data here", diploymentCalcList.get(0).getDate());
		
		db.closeDB();
		
		if(diploymentCalcList != null){
			for(DiploymentCalc diployment:diploymentCalcList){
				
				map.put("shift", diployment.getDate()+" "+diployment.getShift());
				map.put("progress", diployment.getProgress());
				map.put("members", String.valueOf(diployment.getTotal_members()));
				map.put("bikes", String.valueOf(diployment.getTotal_bikes()));
				map.put("vehicles", String.valueOf(diployment.getTotal_vehicles()));
				mylist.add(map);
				map = new HashMap<String, String>();
			}
		}else{
			Toast.makeText(rootView.getContext(), "reports not found", Toast.LENGTH_LONG).show();
		}
		// ...
		mSchedule = new SimpleAdapter(c, mylist, R.layout.listview_layout_design,
		            new String[] {"shift","progress", "members", "bikes","vehicles"}, new int[] {R.id.txtshiftdate,R.id.txtPrograss, R.id.txtNoPerson, R.id.txtNoMotorCycle,R.id.txtNoCar});
		list.setAdapter(mSchedule);
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				 HashMap<String, String> obj = (HashMap<String, String>) list.getAdapter().getItem(position);
		           String data = (String) obj.get("shift");
		           String date = data.substring(0, data.indexOf(" "));
		           String shift = data.substring(data.indexOf(" "), data.length());
			    
		           String memberNo = (String) obj.get("members");
		           String bikesNo = (String) obj.get("bikes");
		           String vehiclesNo = (String) obj.get("vehicles");
		           
		           Log.d("Test Data :", memberNo +" "+ bikesNo +" "+vehiclesNo);
		           
		           onSelectedIntem(shift,date,memberNo,bikesNo,vehiclesNo);
		           
		          // Toast.makeText(rootView.getContext(), date+" "+shift, Toast.LENGTH_LONG).show();
			     
			     
			}
		});
		}else{
			 Toast.makeText(rootView.getContext(),"diployment is empty", Toast.LENGTH_LONG).show();
		     
		}
	}
	public void onSelectedIntem(String shift,String date,String memberNo,String bikesNo,String vehiclesNo){
		Bundle args = new Bundle();
		
		FragmentManager frgManager = getFragmentManager();
		
		Fragment detailsReport = new DetailStrengthReportFragment();
		
		args.putString(DetailStrengthReportFragment.KEY_SHIFT, shift);
		args.putString(DetailStrengthReportFragment.KEY_DATE, date);
		
		args.putInt(DetailStrengthReportFragment.KEY_MEMBERS, Integer.parseInt(memberNo));
		args.putInt(DetailStrengthReportFragment.KEY_BIKES, Integer.parseInt(bikesNo));
		args.putInt(DetailStrengthReportFragment.KEY_VEHICLE, Integer.parseInt(vehiclesNo));
		
		detailsReport.setArguments(args);
		
		frgManager.beginTransaction().replace(R.id.content_frame, detailsReport)
		.commit();
	}
	public class LoadStrengthReport extends AsyncTask<String, String, String> {
   	 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(rootView.getContext());
            pDialog.setMessage("Loading please wait..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
 
        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {
        	try {
        		//load data on the list view
        		//populateStrengthReportListView(rootView.getContext());
        		//populate strength report on the db
        		//urlcreatereport.populateStrengthReportResult();
        		//populate deployment on the db
				//urlcreatereport.populateDiploymentTB();
				
			} catch (Exception e) {
				// TODO: handle exception
			//	Toast.makeText(rootView.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
				Log.d("Strength Report Frag Error :", e.getMessage());
			}
           return null;
        }
 
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }
        
    }

}
