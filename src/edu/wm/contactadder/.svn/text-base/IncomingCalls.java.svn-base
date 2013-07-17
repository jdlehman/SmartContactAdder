package edu.wm.contactadder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class IncomingCalls extends BroadcastReceiver{

	public String phoneNum = "";
	public final static String TAG = "BroadcastReceiver.IncomingCalls";
	
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras(); 
		
		if(bundle.getString("state").equalsIgnoreCase("RINGING")) { 
			
			 //try to get phone number of incoming call
			phoneNum = bundle.getString("incoming_number"); 
			
			Log.v(TAG, "Incoming call from: " + phoneNum);
			
			//check if contact exists, if does not, add to contacts
			if(phoneNum!= null && !phoneNum.equals("")){
				Log.v(TAG, "check if the phone number, " + phoneNum + ", exists in contacts");
				Intent checkContact = new Intent(context, CheckIfContactExists.class);
				checkContact.putExtra("phone_number", phoneNum);
				context.startService(checkContact);
			}
			
				
		}
		
	}

}
