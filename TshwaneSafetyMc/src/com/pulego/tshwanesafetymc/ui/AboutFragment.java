package com.pulego.tshwanesafetymc.ui;

import com.pulego.tshwanesafetymc.R;
import com.pulego.tshwanesafetymc.pojos.AboutType;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.os.Bundle;
//import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class AboutFragment extends Fragment {
	View rootView;
    public AboutFragment(){
    	
    }
    @Override
   	public View onCreateView(LayoutInflater inflater, ViewGroup container,
   			Bundle savedInstanceState) {
   	   rootView=inflater.inflate(R.layout.about,container, false);
   		
   		ActionBar actionBar = getActivity().getActionBar();
   		 
          // Screen handling while hiding ActionBar icon.
         actionBar.setDisplayShowHomeEnabled(true);

          // Screen handling while hiding Actionbar title.
          actionBar.setDisplayShowTitleEnabled(true);

          // Creating ActionBar tabs.
          actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
          

  		ListView listView1 = (ListView) rootView.findViewById(R.id.listView1);
  		
  		PackageInfo pInfo;
  		String version;
  		try {
  			pInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
  			version = pInfo.versionName;
  		} catch (NameNotFoundException e) {
  			version = "";
  		}
  		
  		final AboutType[] items = { new AboutType("About Tshwane Safety",""), new AboutType("Terms of Use",""), new AboutType("Version",version) };

  		ArrayAdapterItem adapter = new ArrayAdapterItem(rootView.getContext(),R.layout.about_item, items);
  	
  		listView1.setOnItemClickListener(new OnItemClickListener() {

  			@Override
  			public void onItemClick(AdapterView<?> parent, View view,
  					int position, long arg3) {
  				view.setSelected(true);
  				
  				AboutType about = items[position];

  				if (about.getName() == "About Tshwane Safety") {

  					final FragmentTransaction ft = getFragmentManager()
  							.beginTransaction();
  					ft.replace(R.id.content_frame, new About(), "About");

  					ft.addToBackStack("About Tshwane Safety");
  					ft.commit();
  				} else if (about.getName() == "Terms of Use") {
  					final  FragmentTransaction ft = getFragmentManager()
  							.beginTransaction();
  					ft.replace(R.id.content_frame, new Terms(), "Terms");

  					ft.addToBackStack("Terms of Use");
  					ft.commit();
  				}

  			}
  		});

  		listView1.setAdapter(adapter);
        
          return rootView;
   	}
		class ArrayAdapterItem extends ArrayAdapter<AboutType> {

	    Context mContext;
	    int layoutResourceId;
	    AboutType data[] = null;

	    public ArrayAdapterItem(Context mContext, int layoutResourceId, AboutType[] data) {

	        super(mContext, layoutResourceId, data);

	        this.layoutResourceId = layoutResourceId;
	        this.mContext = mContext;
	        this.data = data;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {

	        /*
	         * The convertView argument is essentially a "ScrapView" as described is Lucas post 
	         * http://lucasr.org/2012/04/05/performance-tips-for-androids-listview/
	         * It will have a non-null value when ListView is asking you recycle the row layout. 
	         * So, when convertView is not null, you should simply update its contents instead of inflating a new row layout.
	         */
	        if(convertView==null){
	            // inflate the layout
	            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
	            convertView = inflater.inflate(layoutResourceId, parent, false);
	        }

	        // object item based on the position
	        AboutType objectItem = data[position];

	        // get the TextView and then set the text (item name) and tag (item ID) values
	        TextView textViewItem = (TextView) convertView.findViewById(R.id.aboutitem);
	        textViewItem.setText(objectItem.getName());
	        
	        TextView versionViewItem = (TextView) convertView.findViewById(R.id.version);
	        versionViewItem.setText(objectItem.getVersion());
	       

	        return convertView;

	    }

	}
}
