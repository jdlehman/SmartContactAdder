package edu.wm.contactadder;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.util.Log;

public class CheckIfContactExists extends Service{

	
	public final static String TAG = "Service.CheckIfContactExists";
	public String num = "";
	public boolean exists = false;
	
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void onCreate(){
		super.onCreate();
	}

	public void onStart(Intent intent, int startId){
		super.onStart(intent, startId);
		
		Bundle bundle = intent.getExtras(); 
		
		num = bundle.getString("phone_number").replace("(","").replace(")", "").replace("-", "").replace("+", "").replaceAll("\\b\\s{1,}\\b", "").trim();
		
		//check if contact already exists
		exists = checkIfExists();
		
		//add to contacts if contact does not exist
		if(!exists){
			Log.v(TAG, "try to fill in address data");
			
			Intent fillAddress = new Intent(CheckIfContactExists.this, FillInAddressData.class);
			fillAddress.putExtra("phone_number", num);
			CheckIfContactExists.this.startService(fillAddress);
		
		}
		
	}
	
	public void onDestroy(Intent intent){
		super.onDestroy();
	}
	
	public boolean checkIfExists() {
		Log.v(TAG, "checking if contact already exists..");
		
    	//check if there is valid data for number and last name
    	if(!num.equals("")){
	    	
    		ContentResolver cr = getContentResolver();
	    	String[] cols = new String[] {"number" };
	    	Uri contactUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(num));
	    	Cursor cur = cr.query(contactUri, cols, null, null, null);
	    	if (cur.getCount() > 0) {
			    while (cur.moveToNext()) {
			        //get and format phone number of contact
			        String number = cur.getString(cur.getColumnIndex(ContactsContract.PhoneLookup.NUMBER)).replace("(","").replace(")", "").replace("-", "").replace("+", "").replaceAll("\\b\\s{1,}\\b", "").trim();
			       
			        //check if numbers are equal
			        if (num.equals(number)) {
			        	
			        	Log.v(TAG, "contact exists: do not prompt user to add to contacts");
						return true;
		 	        }
		        }
	        }
    	}
    	
    	Log.v(TAG, "contact does not exist: prompt user to add to contacts");
		return false;
	}

}
