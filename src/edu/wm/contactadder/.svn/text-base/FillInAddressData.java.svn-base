package edu.wm.contactadder;

import edu.wm.addressExtractor.Address;
import edu.wm.addressExtractor.AddressFromYellowPages;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class FillInAddressData extends Service{
	
	private final int mode = Activity.MODE_PRIVATE;
	private final String APP_PREFS = "App_Prefs";
	
	public final static String TAG = "Service.FillInAddressData";
	private static final int NOTE_ID = 1;
	public String num = "";

	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void onCreate(){
		super.onCreate();
	}
	
	public void onDestroy(Intent intent){
		super.onDestroy();
	}
	
	public void onStart(Intent intent, int startId){
		super.onStart(intent, startId);
		
		Bundle bundle = intent.getExtras(); 
		
		num = bundle.getString("phone_number").replace("(","").replace(")", "").replace("-", "").replace("+", "").replaceAll("\\b\\s{1,}\\b", "").trim();
		
		//check if the phone number is null
		if(!num.equals("")){
		
			Log.v(TAG, "create notification to add new contact"); 
			
			//create notification asking if user wants to add contact, using NotificationManager
			String ns = Context.NOTIFICATION_SERVICE;
			NotificationManager noteManager = (NotificationManager) getSystemService(ns);
			
			//set notification icon
			int icon = R.drawable.icon;
			CharSequence tickerText = "Add New Contact";
			long when = System.currentTimeMillis();
	
			Notification notification = new Notification(icon, tickerText, when);
			//notification icon disappears after user looks at it
			notification.flags = Notification.FLAG_AUTO_CANCEL; 
			
			//add additional data and Intent to be launched if user clicks notification
			Context context = getApplicationContext();
			CharSequence contentTitle = "Add number to contacts.";
			CharSequence contentText = "Add new contact: " + num;
			Intent notificationIntent = new Intent(this, addContact.class);
			PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
	
			//get address data such that it is ready when the addContact intent is launched
			fillInYellowPagesData();
			
			notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
			noteManager.notify(NOTE_ID, notification);
			
			Log.v(TAG, "notification launched");
		}
		else{
			Log.v(TAG, "number is null, cannot fill address data");
		}
	}
	
	private void fillInYellowPagesData(){
    	Log.v(TAG, "Begin: Getting Yellowpages data");
    	
    	//check if number is not null
    	if(!num.equals("")){
	    	
	    	AddressFromYellowPages add = new AddressFromYellowPages();
			
			//add address by phone number
			add.setNumber(num);
			Address singleAdd = add.getAddress(num);
			
			//set values from yellowpages if found
			if(singleAdd != null){
				// Store values between instances here
		    	SharedPreferences prefs = getSharedPreferences(APP_PREFS, mode);
			    SharedPreferences.Editor editor = prefs.edit();
			    editor.clear();
			    
			    if(singleAdd.getName() != null){
			    	editor.putString("busName", singleAdd.getName().toString());
			    }
			    editor.putString("phoneNum", num);
			    if(singleAdd.getStreetAddress() != null){
			    	editor.putString("street", singleAdd.getStreetAddress().toString());
			    }
			    if(singleAdd.getCity() != null){
			    	editor.putString("city", singleAdd.getCity().toString());
			    }
			    if(singleAdd.getCity() != null){
			    	editor.putString("state", singleAdd.getState().toString());
			    }
			    if(singleAdd.getZip() != -1){
			    	editor.putString("zipCode", singleAdd.getZip() + "");
			    }
			    if(singleAdd.getCountry() != null){
			    	editor.putString("country", singleAdd.getCountry().toString());
			    }
			    
			    // Commit to storage
			    editor.commit();
				
			}		
			else{
				// Store values between instances here
		    	SharedPreferences prefs = getSharedPreferences(APP_PREFS, mode);
			    SharedPreferences.Editor editor = prefs.edit();
			    editor.clear();
			    
			    editor.putString("phoneNum", num);
			    
			    // Commit to storage
			    editor.commit();
			}
			 
    	}
    	
    	Log.v(TAG, "End: Getting Yellowpages data");
    }
}
