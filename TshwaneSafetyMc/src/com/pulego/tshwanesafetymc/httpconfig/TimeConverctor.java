package com.pulego.tshwanesafetymc.httpconfig;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import android.net.ParseException;
import android.widget.TextView;

public class TimeConverctor {
	public TimeConverctor(){
		
	}
	public static void getTimeDifference(String pDate, TextView time) {
	    int diffInDays = 0;
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    format.setTimeZone(TimeZone.getTimeZone("GMT"));
	    Calendar c = Calendar.getInstance();
	    String formattedDate = format.format(c.getTime());
	
	    Date d1 = null;
	    Date d2 = null;
	    try {
	
	        d1 = format.parse(formattedDate);
	        d2 = format.parse(pDate);
	        long diff = d1.getTime() - d2.getTime();
	
	        diffInDays = (int) (diff / (1000 * 60 * 60 * 24));
	        if (diffInDays > 0) {
	            if (diffInDays == 1) {
	                time.setText(diffInDays + " day ago");
	            } else {
	                time.setText(diffInDays + " days ago");
	            }
	        } else {
	            int diffHours = (int) (diff / (60 * 60 * 1000));
	            if (diffHours > 0) {
	                if (diffHours == 1) {
	                    time.setText(diffHours + " hr ago");
	                } else {
	                    time.setText(diffHours + " hrs ago");
	                }
	            } else {
	
	                int diffMinutes = (int) ((diff / (60 * 1000) % 60));
	                if (diffMinutes == 1) {
	                    time.setText(diffMinutes + " min ago");
	                } else {
	                    time.setText(diffMinutes + " mins ago");
	                }
	
	            }
	        }
	
	    } catch (ParseException e) {
	        // System.out.println("Err: " + e);
	        e.printStackTrace();
	    } catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
