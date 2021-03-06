package edu.wm.contactadder;

import java.io.*;
import java.util.*;

import edu.wm.Android.Logger.Logger;
import edu.wm.addressExtractor.Address;
import edu.wm.addressExtractor.AddressFromYellowPages;
import edu.wm.weightedNames.ComparatorByName;
import edu.wm.weightedNames.NamesTreeSet;
import edu.wm.weightedNames.SetOfWeightedNames;
import edu.wm.weightedNames.WeightedName;


import android.app.Activity;
import android.content.*;
import android.database.*;
import android.net.Uri;
import android.os.*;
import android.provider.*;
import android.text.*;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.*;

public class addContact extends Activity{
	public static String ACCOUNT_NAME = "edu.wm.contactadder.addContact.ACCOUNT_NAME";
	public static String ACCOUNT_TYPE = "edu.wm.contactadder.addContact.ACCOUNT_TYPE";
	public final static String TAG = "Activity.addContact";
	public static String[] autoNames ={""};
	public static SetOfWeightedNames wNames = new NamesTreeSet(new ComparatorByName());
	public boolean isTRunning = false;
	public boolean exists = false;
	private Thread t;
	
	
	private AutoCompleteTextView fName;
	private EditText lName;
	private EditText bName;
	private EditText num;
	private EditText strt;
	private EditText city;
	private EditText state;
	private EditText zip;
	private EditText cntry;
	
	private final int mode = Activity.MODE_PRIVATE;
	private final String APP_PREFS = "App_Prefs";
	
	private Logger a;
		
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
    	//generate trace
    	//Debug.startMethodTracing("addContact");
    	ArrayList<String> list = new ArrayList<String>();
    	list.add("edu.wm.contactadder");
    	list.add("edu.wm.weightedNames");
    	list.add("edu.wm.addressExtractor");
    	a = new Logger("contactadder", list);
    	a.startLog();
    	
    	 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //assign variables to objects in the layout
        fName = (AutoCompleteTextView) findViewById(R.id.firstName);
    	lName = (EditText) findViewById(R.id.lastName);
    	bName = (EditText) findViewById(R.id.busName);
    	num = (EditText) findViewById(R.id.phoneNum);
    	strt = (EditText) findViewById(R.id.street);
    	city = (EditText) findViewById(R.id.city);
    	state = (EditText) findViewById(R.id.state);
    	zip = (EditText) findViewById(R.id.zipcode);
    	cntry = (EditText) findViewById(R.id.country);
    	
    	//load shared preferences
    	SharedPreferences prefs = getSharedPreferences(APP_PREFS, mode);
    	//set any shared preferences
    	fName.setText(prefs.getString("firstName", null));
    	lName.setText(prefs.getString("lastName", null));
    	bName.setText(prefs.getString("busName", null));
    	num.setText(prefs.getString("phoneNum", null));
    	strt.setText(prefs.getString("street", null));
    	city.setText(prefs.getString("city", null));
    	state.setText(prefs.getString("state", null));
    	zip.setText(prefs.getString("zipCode", null));
    	cntry.setText(prefs.getString("country", null));
//    	
//    	//get address info if number is loaded at apps start
//    	if(num.getText() != null && !num.getText().toString().equals("")){
//    		fillInYellowPagesData();
//    	}
    	
    	//read names to SetOfWeightedNames
        readNames();
    	
    	//add text changed listener to first name input 
    	fName.addTextChangedListener(new TextWatcher(){

    		//implementation not needed
			public void afterTextChanged(Editable text) {		
				// TODO Auto-generated method stub
			}
			
			//implementation not needed
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
				
			}
			
			//generate new set of names to suggest in AutoCompleteTextEdit based on prefix if text changed
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
				String text = fName.getText().toString();
				
				//check that fName input has more than one letter 
				if(!text.equals("") && text != null && text.length() > 1){
					Log.v(TAG, "BEGIN: first name text changed");
					
					SetOfWeightedNames tempSet = wNames;
					
					Log.v(TAG, "BEGIN: calculating top names");
					//set autoNames to top 5 names with prefix
					autoNames = tempSet.getTopNamesWithMatchingPrefix(text, 5);
					
					//get string of names being used in set
					String set = "";
					for(int i = 0; i < autoNames.length; i++){
						set += autoNames[i] + " ";
					}
					Log.v(TAG, "Set being used for prefix, " + text + ": " + set);
					Log.v(TAG, "END: calculating top names");
					
					//set up AutoCompleteTextView with array of strings, autoNames
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(addContact.this, R.layout.list_item, autoNames);
					fName.setAdapter(adapter);
					
					Log.v(TAG, "END: first name text changed");
				}	
			}
    		
    	});
    	
    	//add onFocusChangeListener to phone number input
    	num.setOnFocusChangeListener(new OnFocusChangeListener() {
    		public void onFocusChange(View v, boolean hasFocus){ 
    			if(!hasFocus){
    				//call new thread
    				 t = new Thread() {

    			          public void run() {
    			        	  //get data from yellow pages using phone number, if thread isnt already running
    			        	  if(!isTRunning){
    			        		  isTRunning = true;
    			        		  fillInYellowPagesData();
    			        		  isTRunning = false;
    			        	  }
    			          }
    			      };
    			      t.start();
    			}
    		}
    	});
    
    	//end trace
       
    }
    
    /** called when done button is clicked**/
    public void btnDone(View vw){
    	//check if thread is still running
    	if(isTRunning){
    		Log.v(TAG, "Waiting for thread to see if it can fill in data from phone number");
	    	while(isTRunning){
	    	}
    	}
    	
    	Log.v(TAG, "BEGIN: Attempting to store contact data");
    	String data = "";
    	
    	//format number
    	num.setText(num.getText().toString().replace("(","").replace(")", "").replace("-", "").replace("+", "").replaceAll("\\b\\s{1,}\\b", "").trim());
    	
    	//combine all non-null data
    	if(fName.getText() != null && !fName.getText().toString().equals("")){
    		data = "First Name: " + fName.getText().toString().trim() + ", ";
    	}
    	if(lName.getText() != null && !lName.getText().toString().equals("")){
    		data += "Last Name: " + lName.getText().toString().trim() + ", ";
    	}
    	if(bName.getText() != null && !bName.getText().toString().equals("")){
    		data += "Business Name: " + bName.getText().toString().trim() + ", ";
    	}
    	if(num.getText() != null && !num.getText().toString().equals("")){
    		data += "Phone Number: " + num.getText().toString().trim() + ", ";
    	}
    	if(strt.getText() != null && !strt.getText().toString().equals("")){
    		data += "Street: " + strt.getText().toString().trim() + ", ";
    	}
    	if(city.getText() != null && !city.getText().toString().equals("")){
    		data += "City: " + city.getText().toString().trim() + ", ";
    	}
    	if(state.getText() != null && !state.getText().toString().equals("")){
    		data += "State: " + state.getText().toString().trim() + ", ";
    	}
    	if(zip.getText() != null && !zip.getText().toString().equals("")){
    		data += "Zipcode: " + zip.getText().toString().trim() + ", ";
    	}
    	if(cntry.getText() != null && !cntry.getText().toString().equals("")){
    		data += "Country: " + cntry.getText().toString().trim() + ", ";
    	}
    	
    	//check if last name and number have values (do not store if they dont
    	if(lName.getText() != null && !lName.getText().toString().equals("") && num.getText() != null && !num.getText().toString().equals("")){
    		//check if phone number and last name already exist in Contacts
        	exists = false;
    		exists = checkIfContactExists();
    		//add contact if it does not exist
    		if(!exists){
    			addAContact(data);
    		}
			else{
				Log.v(TAG, "unable to store contact: " + data);
				Log.v(TAG, "because the contact already exists");
			}
    	}
    	else{
    		Log.v(TAG, "unable to store contact: " + data);
    		Log.v(TAG, "because phone number and last name were invalid");
    	}
    	
    	//clear data from EditTexts
    	fName.setText("");
    	bName.setText("");
    	lName.setText("");
    	num.setText("");
    	strt.setText("");
    	city.setText("");
    	state.setText("");
    	zip.setText("");
    	cntry.setText("");
    	
    	// Store values between instances here
    	SharedPreferences prefs = getSharedPreferences(APP_PREFS, mode);
	    SharedPreferences.Editor editor = prefs.edit();
	    editor.clear();
	    
	    editor.putString("firstName", fName.getText().toString());
	    editor.putString("lastName", lName.getText().toString());
	    editor.putString("busName", bName.getText().toString());
	    editor.putString("phoneNum", num.getText().toString());
	    editor.putString("street", strt.getText().toString());
	    editor.putString("city", city.getText().toString());
	    editor.putString("state", state.getText().toString());
	    editor.putString("zipCode", zip.getText().toString());
	    editor.putString("country", cntry.getText().toString());
	    
	    // Commit to storage
	    editor.commit();
    	
    	
    	Log.v(TAG, "END: Attempting to store contact data");
    }
    
   

	/**called when cancel button is pushed**/
    public void btnCancel(View vw){
    	SharedPreferences prefs = getPreferences(MODE_PRIVATE);
    	SharedPreferences.Editor editor = prefs.edit();
    	editor.clear();
    	editor.commit();
    	
    	//clear data from EditTexts
    	fName.setText("");
    	lName.setText("");
    	bName.setText("");
    	num.setText("");
    	strt.setText("");
    	city.setText("");
    	state.setText("");
    	zip.setText("");
    	cntry.setText("");
    	
    	//close app without saving data
    	this.finish();
    }
    
    protected void onPause(){
    	super.onPause();
    
	    // Store values between instances here
    	SharedPreferences prefs = getSharedPreferences(APP_PREFS, mode);
	    SharedPreferences.Editor editor = prefs.edit();
	    editor.clear();
	    
	    editor.putString("firstName", fName.getText().toString());
	    editor.putString("lastName", lName.getText().toString());
	    editor.putString("busName", bName.getText().toString());
	    editor.putString("phoneNum", num.getText().toString());
	    editor.putString("street", strt.getText().toString());
	    editor.putString("city", city.getText().toString());
	    editor.putString("state", state.getText().toString());
	    editor.putString("zipCode", zip.getText().toString());
	    editor.putString("country", cntry.getText().toString());
	    
	    // Commit to storage
	    editor.commit();
	    
	    a.stopLog();
    }
    
    protected void onResume(){
    	super.onResume();
    	//load shared preferences
    	SharedPreferences prefs = getSharedPreferences(APP_PREFS, mode);
    	
    	fName.setText(prefs.getString("firstName", null));
    	lName.setText(prefs.getString("lastName", null));
    	bName.setText(prefs.getString("busName", null));
    	num.setText(prefs.getString("phoneNum", null));
    	strt.setText(prefs.getString("street", null));
    	city.setText(prefs.getString("city", null));
    	state.setText(prefs.getString("state", null));
    	zip.setText(prefs.getString("zipCode", null));
    	cntry.setText(prefs.getString("country", null));
    }
    
    private void readNames(){
    	Log.v(TAG, "Begin: reading names to SetOfWeightedNames");
    	try{
  
    		//buffered reader to read the contents of the file
    		BufferedReader bReader = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.names)));
    		
    		//TreeSet to hold the names that have been added, used to quickly determine if a WeightedName needs to be added
    		//or the weight needs to be updated
    		TreeSet<WeightedName> nameTree = new TreeSet<WeightedName>(new ComparatorByName());
    		
    		String line;

    		//read each line of the file
    		while ((line = bReader.readLine()) != null) {
    			//split data by Name Gender and Weight (separated by commas)
    			String[] lineData = line.split(",");

    			boolean isInvalidDouble = false;

    			//check necessary data is present in line
    			if(lineData.length == 3){

    				//check if double is valid
    				try {
    					Double.parseDouble(lineData[2]);
    				} catch (NumberFormatException e) {
    					isInvalidDouble = true;
    				}

    				//check that the line is in the correct format
    				if(!isInvalidDouble && Double.parseDouble(lineData[2]) > 0  && lineData[0] != null && !lineData[0].equals("") && (lineData[1].trim().equals("M") || lineData[1].trim().equals("F") || lineData[1].trim().equals("U"))){
    					//create a new Name with extracted data
    					WeightedName n = new WeightedName(lineData[0].trim(), Double.parseDouble(lineData[2]));
    					
    					//add weighted name to tree, update weight if name is the same
    					nameTree.add(n);		
    				}
    			}
    		}

    		bReader.close();
    		
    		  for(WeightedName wn : nameTree){
    	    	  wNames.add(wn.getName(), wn.getWeight());
    	      }
        }
        catch(Throwable t){
        	Toast.makeText(this, "Exception: " + t.toString(), 1000).show();
        }
        
        Log.v(TAG, "End: reading names to SetOfWeightedNames");
    }
    
    private void fillInYellowPagesData(){
    	Log.v(TAG, "Begin: Filling in Yellowpages data");
    	
    	//check if number is not null
    	if(num.getText() != null && !num.getText().toString().equals("")){
	    	
	    	AddressFromYellowPages add = new AddressFromYellowPages();
			
			//add address by phone number
			add.setNumber(num.getText().toString());
			Address singleAdd = add.getAddress(num.getText().toString());
			
			//set values from yellowpages if found
			if(singleAdd != null){
				//only fill in values if user has not already entered values, and values not null
				
				if(strt.getText().toString().equals("") && singleAdd.getStreetAddress() != null){
					//strt.setText(singleAdd.getStreetAddress().toString());
					messageHandler.sendMessage(Message.obtain(messageHandler, 0, singleAdd.getStreetAddress().toString())); 
				}
				if(city.getText().toString().equals("") && singleAdd.getCity() != null){
					//city.setText(singleAdd.getCity().toString());
					messageHandler.sendMessage(Message.obtain(messageHandler, 1, singleAdd.getCity().toString())); 
				}
				if(state.getText().toString().equals("") && singleAdd.getState() != null){
					//state.setText(singleAdd.getState().toString());
					messageHandler.sendMessage(Message.obtain(messageHandler, 2, singleAdd.getState().toString())); 
				}
		    	if(zip.getText().toString().equals("") && singleAdd.getZip() != -1){
		    		//zip.setText(singleAdd.getZip() + "");
		    		messageHandler.sendMessage(Message.obtain(messageHandler, 3, singleAdd.getZip() + "")); 
		    	}
		    	if(cntry.getText().toString().equals("") && singleAdd.getCountry() != null){
		    		//cntry.setText(singleAdd.getCountry().toString());
		    		messageHandler.sendMessage(Message.obtain(messageHandler, 4, singleAdd.getCountry().toString())); 
		    	}
		    	if(bName.getText().toString().equals("") && singleAdd.getName() != null){
					messageHandler.sendMessage(Message.obtain(messageHandler, 5, singleAdd.getName().toString())); 
				}
			}
			
			 
    	}
    	
    	Log.v(TAG, "End: Filling in Yellowpages data");
    }
    
    public void addAContact(String data){    	
    	
		ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
		
		//Add contact type
	    ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
	            .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, ACCOUNT_TYPE)
	            .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, ACCOUNT_NAME)
	            .build());
	    
	    //add group
		ops.add(ContentProviderOperation.newInsert(ContactsContract.Groups.CONTENT_URI)
	            .withValue(ContactsContract.Groups.SOURCE_ID, "contacts")
	            .withValue(ContactsContract.Groups.GROUP_VISIBLE, 1)
	            .withValue(ContactsContract.Groups.ACCOUNT_NAME, ACCOUNT_NAME)
	            .withValue(ContactsContract.Groups.ACCOUNT_TYPE, ACCOUNT_TYPE)
	            .build());
	    
		//Add to visible group
	    ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
	            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
	            .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.GroupMembership.CONTENT_ITEM_TYPE)
	            .withValue(ContactsContract.CommonDataKinds.GroupMembership.GROUP_SOURCE_ID, "contacts")
	            .build());		 

	    //Add contact name
	    ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
	            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
	            .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
	            .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, fName.getText().toString() + " " + lName.getText().toString())
	            .withValue(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME, lName.getText().toString())
	            .withValue(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, fName.getText().toString())
	            .build());	    

	    //Add phone number
	    ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
	            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
	            .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
	            .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
	            .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, num.getText().toString())
	            .build());
	    
	    //Add address
	    ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
	            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
	            .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE)
	            .withValue(ContactsContract.CommonDataKinds.StructuredPostal.CITY, city.getText().toString())
	            .withValue(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE, zip.getText().toString())
	            .withValue(ContactsContract.CommonDataKinds.StructuredPostal.STREET, strt.getText().toString())
	            .withValue(ContactsContract.CommonDataKinds.StructuredPostal.REGION, state.getText().toString())
	            .withValue(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY, cntry.getText().toString())
	            .build());
	    
	    //Add business info
	    ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
	            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
	            .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)
	            .withValue(ContactsContract.CommonDataKinds.Organization.COMPANY, bName.getText().toString())
	            .build());
	    
	    
	    
	 
	    
	    //Add contact
	    try {
			addContact.this.getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
			Log.v(TAG, "stored contact: " + data);
		} catch (Exception e) {
			Log.v(TAG, "attempted to store contact: " + data);
			Log.v(TAG, "was unable to store data because: " + e.toString());
		} 
    	
    }
    
    public boolean checkIfContactExists() {
    	//check if there is valid data for number and last name
    	if(num.getText() != null && !num.getText().toString().equals("") && lName.getText() != null && !lName.getText().toString().equals("")){
	    	
    		ContentResolver cr = getContentResolver();
	    	String[] cols = new String[] {"display_name", "number", "has_phone_number" };
	    	//String sqlFilter = "number = '" + num.getText().toString() + "' AND display_name LIKE '%" + lName.getText().toString() + "%'";
	    	Uri contactUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(num.getText().toString()));
	    	Cursor cur = cr.query(contactUri, cols, null, null, null);
	    	if (cur.getCount() > 0) {
			    while (cur.moveToNext()) {
			    	//get name of contact
			        String name = cur.getString(cur.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
			        //get and format phone number of contact
			        String number = cur.getString(cur.getColumnIndex(ContactsContract.PhoneLookup.NUMBER));
			        
			        //check if contact has phone number
			        if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
			        	String[] fullName = name.split(" ");
			  
			        	if(fullName.length > 0){
				        	//check if last names are equal
				        	if(lName.getText().toString().equalsIgnoreCase(fullName[fullName.length - 1])){
				        		Log.v(TAG, "contact exists with same last name");
				        		//check if contains number
				        		if(num.getText().toString().equals(number.replace("(","").replace(")", "").replace("-", "").replace("+", "").replaceAll("\\b\\s{1,}\\b", "").trim())){
					        		
						  	        Log.v(TAG, "contact exists with same last name and number: do not add to contacts");
						  	               			
								    return true;
				        		}
				        	}
			        	}
		 	        }
		        }
	        }
    	}
    	
    	Log.v(TAG, "contact does not exist: add to contacts");
		return false;
	}
    
    //handle messages from other threads so that the main thread can use them
    private Handler messageHandler = new Handler() {

	      public void handleMessage(Message msg) {  
	          switch(msg.what) {
	          	case 0:  strt.setText(msg.obj.toString()); 
	          		break;
	          	case 1:  city.setText(msg.obj.toString()); 
          			break;
	          	case 2:  state.setText(msg.obj.toString()); 
          			break;
	          	case 3:  zip.setText(msg.obj.toString()); 
          			break;
	          	case 4:  cntry.setText(msg.obj.toString()); 
          			break;
	          	case 5: bName.setText(msg.obj.toString());
	          		break;
	          }
	      }
	     
	  };
	
	
}