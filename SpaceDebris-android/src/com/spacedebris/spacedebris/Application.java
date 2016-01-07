package com.spacedebris.spacedebris;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import android.util.Base64;

import com.appnext.appnextsdk.AppnextTrack;
import com.flurry.android.FlurryAgent;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.parse.Parse;
import com.parse.ParseCrashReporting;

public class Application extends android.app.Application
{
	String text1;
	// The following line should be changed to include the correct property id.
    private static final String PROPERTY_ID = "UA-58722207-6";

    public static int GENERAL_TRACKER = 0;

	 public enum TrackerName {
	        APP_TRACKER, // Tracker used only in this app.
	        GLOBAL_TRACKER, // Tracker used by all the apps from a company. eg: roll-up tracking.
	        ECOMMERCE_TRACKER, // Tracker used by all ecommerce transactions from a company.
	    }
	 
	 HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();
	@Override
	public void onCreate() 
	{
	super.onCreate();
// Enable Crash Reporting
	ParseCrashReporting.enable(this);
	try {
		byte[] data1 = Base64.decode(Constant.base64, Base64.DEFAULT);
		 text1 = new String(data1, "UTF-8");
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
	String cleartext1,cleartext2;
	try {
		
		cleartext1 = Encrypt.decrypt(text1, "B7251742635E563610BBBF6D144CCD00D96B36F1740F18BA3ED4D1D6D85DAD789DC344B43262D30905A77513F697B2C9");
		cleartext2 =Encrypt.decrypt(text1, "85E51009147C3760B4DEE6758F74CFA8788C5E750348E6C870B70AF96AD2E79139FE4E0048B2E3C4E7B59E72DB51329D");
		Parse.initialize(Application.this, cleartext1, cleartext2);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	AppnextTrack.track(this);

//khomsh
	
	//Parse.initialize(Application.this, "pf5bInfFdMW1dgXqkEagRtCns9kzCAKr1ZQfVhsi","DKyqyXUvngICsu5g8S2X30BjgLvkDlenXjaqm4js");
	
	
	

	//parse biswatma sd test
	//Parse.initialize(Application.this, "kjuVbXmP68G8BMGFt8sfLn9xVaf3URSYmJn3leYK","HNyQRYNVqazHk77ehmIGdJ7FYmGO5AwcwTHnuE3m");

       FlurryAgent.setLogEnabled(false);


       FlurryAgent.init(this,"JJQC7QBB9CC9JSVH2V4P");
	
	}

	
    synchronized Tracker getTracker(TrackerName trackerId) {
        if (!mTrackers.containsKey(trackerId)) {

            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            Tracker t = (trackerId == TrackerName.APP_TRACKER) ? analytics.newTracker(PROPERTY_ID)
                    : (trackerId == TrackerName.GLOBAL_TRACKER) ? analytics.newTracker(
                            R.xml.global_tracker)
                            : analytics.newTracker(R.xml.ecommerce_tracker);
            mTrackers.put(trackerId, t);

        }
        return mTrackers.get(trackerId);
    }
}
