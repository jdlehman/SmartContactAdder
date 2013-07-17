package edu.wm.Android.Logger;

//import java.io.File;

import android.os.Debug;

public class TraceLogger {
	
	private String traceName;

	public TraceLogger(String traceName){
//		String fileName =  traceName + ".trace";
//		 File file = new File("/mnt/sdcard/" + fileName);
//		    
//		//do not overwrite old tracefile
//		try{
//	        // Create file if it does not exist
//	        boolean success = file.createNewFile();
//	        if (!success) {//file already exists
//	        	int ctr = 1;
//	        	while(!success){
//	        		ctr++;
//	        		fileName = traceName + ctr + ".trace";
//	        		file = new File("/mnt/sdcard/" + fileName);
//	        		success = file.createNewFile();
//	        	}
//	        } 
//		}
//		catch(Exception ex){
//			
//		}
        
		this.traceName = traceName;
	}
	
	public void startTrace(){
		Debug.startMethodTracing(traceName);
	}
	
	public void stopTrace(){
		Debug.stopMethodTracing();
	}
}
