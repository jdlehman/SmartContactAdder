package edu.wm.Android.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.os.Environment;
import android.util.Log;

public class SystemLogger {

	private String voltVal;
	private String gpsVal;
	private String bluetoothVal;
	private String volumeVal;
	private String mobileVal;
	private String totalVal;
	private String lcdVal;
	
	private String fileName;
	private String traceName;
	private boolean stop;
	private Thread t;
	private ArrayList<String> packages = new ArrayList<String>();
	
	public SystemLogger(String traceName, ArrayList<String> packages){
		voltVal = null;
		gpsVal = null;
		bluetoothVal = null;
		volumeVal = null;
		mobileVal = null;
		totalVal = null;
		lcdVal = null;
		
		stop = false;
		this.traceName = traceName;
		fileName = "";
		this.packages.addAll(packages);
	}
	
	public void LogSystem(){
		//reset values
		voltVal = null;
		gpsVal = null;
		bluetoothVal = null;
		volumeVal = null;
		mobileVal = null;
		totalVal = null;
		lcdVal = null;
		
		createSystemFile();
		
		//call new thread
		 t = new Thread() {

	          public void run() {
	        	  while(!stop){
	      			collectData();
	        	  }
	          }
	      };
	      t.start();
		
	}
	
	/**
	 * creates file to record system info, if file already exists adds number to end
	 * such that user does not accidentally overwrite an important trace
	 * @param traceName used for name of file
	 */
	private void createSystemFile(){
		try {
			fileName =  traceName + ".syslog";
	        File file = new File("/mnt/sdcard/" + fileName);
	    
	        // Create file if it does not exist
	        boolean success = file.createNewFile();
	        if (!success) {//file already exists
	        	int ctr = 1;
	        	while(!success){
	        		ctr++;
	        		fileName = traceName + ctr + ".syslog";
	        		file = new File("/mnt/sdcard/" + fileName);
	        		success = file.createNewFile();
	        	}
	        } 
	    } catch (IOException e) {
	    	// TODO Auto-generated catch block
			e.printStackTrace();
	    }
	}
	
	private void collectData(){
		
		  getBatteryData();
		  getLCDData();
		  getNetStat();
		  getCPUThreadData();
		  getStatusBarData();
		        
	}

	private void getBatteryData(){
		String s = null;
		String val = null;
		long time = System.currentTimeMillis();
		String command = "dumpsys battery";
		try {
			
			Process process = Runtime.getRuntime().exec(command);
			
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // read the output from the command
            while ((s = stdInput.readLine()) != null) {
            	Log.v("###", s);
            	if(s.contains("voltage")){
            		val = s.trim().substring(7);
            	}
            }
            
            process.waitFor();
    	
            if(val != null && !val.equals(voltVal)){
            	voltVal = val;
	            File root = Environment.getExternalStorageDirectory();
	            File actualFile = new File(root, fileName);
	            FileWriter fstream = new FileWriter(actualFile, true);
	            BufferedWriter out = new BufferedWriter(fstream);
	            out.write("<battery voltage=\"" + val + "\" time=\"" + time + "\"/>\n\n");
	            //Close the output stream
	            out.close();
            }
            
            
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void getLCDData(){
		String s = null;
		String val = null;
		long time = System.currentTimeMillis();
		String command = "dumpsys power";
		try {
			
			Process process = Runtime.getRuntime().exec(command);
			
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // read the output from the command
            while ((s = stdInput.readLine()) != null) {
            	Log.v("###", s);
            	if(s.contains("mScreenBrightness: animating")){
            		
            		String tmp[] = s.split(" ");
            		String tmp2[] = tmp[4].split("=");
            		val = tmp2[1];
            	}
            }
            process.waitFor();
            
            if(val != null && !val.equals(lcdVal)){
            	lcdVal = val;
	            File root = Environment.getExternalStorageDirectory();
	            File actualFile = new File(root, fileName);
	            FileWriter fstream = new FileWriter(actualFile, true);
	            BufferedWriter out = new BufferedWriter(fstream);
	            out.write("<lcd brightness=\"" + val + "\" time=\"" + time + "\"/>\n\n");
	            //Close the output stream
	            out.close();
            }
            
            
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void getNetStat(){
		String s = null;
		String mobileTrans = null;
		String mobileRec = null;
		String totalTrans = null;
		String totalRec = null;
		long time = System.currentTimeMillis();
		
		String command = "dumpsys netstat";
		try {
			
			Process process = Runtime.getRuntime().exec(command);
			
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // read the output from the command
            while ((s = stdInput.readLine()) != null) {
            	if(s.contains("Mobile:")){
            		String tmp[] = s.split(" ");
            		String tmp2[] = tmp[1].split("=");
            		mobileTrans = tmp2[1];
            		String tmp3[] = tmp[2].split("=");
            		mobileRec = tmp3[1];
            	}
            	else if(s.contains("Total:")){
            		String tmp[] = s.split(" ");
            		String tmp2[] = tmp[1].split("=");
            		totalTrans = tmp2[1];
            		String tmp3[] = tmp[2].split("=");
            		totalRec = tmp3[1];
            	}
            }
            process.waitFor();
            
    	
            if(mobileTrans != null && totalTrans != null){
	            File root = Environment.getExternalStorageDirectory();
	            File actualFile = new File(root, fileName);
	            FileWriter fstream = new FileWriter(actualFile, true);
	            BufferedWriter out = new BufferedWriter(fstream);
	            out.write("<netstat time=\"" + time + "\">\n");
	            if(mobileTrans != null && !mobileTrans.equals(mobileVal)){
	            	mobileVal = mobileTrans;
	            	out.write("<mobile transmit=\"" + mobileTrans +  "\" receive=\"" + mobileRec + "\"/>\n");
	            }
	            if(totalTrans != null && !totalTrans.equals(totalVal)){
	            	totalVal = totalTrans;
	            	out.write("<total transmit=\"" + totalTrans +  "\" receive=\"" + totalRec + "\"/>\n");
	            }
	            out.write("</netstat>\n\n");
	            //Close the output stream
	            out.close();
            }
                  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private void getCPUThreadData(){
		String s = null;
		ArrayList<CPUObj> data = new ArrayList<CPUObj>();
		long time = System.currentTimeMillis();
		String user = null;
		String sys = null;
		
		String command = "top -t -n 1";
		try {
			
			Process process = Runtime.getRuntime().exec(command);
			
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // read the output from the command
            while ((s = stdInput.readLine()) != null) {
            	if(s.contains(", System ")){
            		String tmp[] = s.split(" ");
            		user = tmp[1].replace(",", "");
            		sys = tmp[3].replace(",", "");
            	}
            	else{
            		for(String pack : packages){
                		if(s.contains(pack)){
                    		String tmp[] = s.split("\\s+");
                    		CPUObj temp = new CPUObj(pack);
                    		//Log.v("###", "0 " + tmp[0] + " 1 " + tmp[1] + " 2 " + tmp[2] + " 3 " + tmp[3] + " 4 " + tmp[4] + " 5 " + tmp[5] + " 6 " + tmp[5] + " 7 " + tmp[7] + " 8 " + tmp[8] + " 9 " + tmp[9]);
                    		temp.setCPU(tmp[3]);
                    		
                    		if(tmp[9].equals("Binder")){
                    			temp.setThread(tmp[9] + " " + tmp[10] + " " + tmp[11]);
                    		}
                    		else{
                    			temp.setThread(tmp[9]);
                    		}
                    		
                    		data.add(temp);
                    	}
                	}
            	}
            }
            process.waitFor();
            
    	
            File root = Environment.getExternalStorageDirectory();
            File actualFile = new File(root, fileName);
            FileWriter fstream = new FileWriter(actualFile, true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write("<detailedcpu user=\"" + user + "\" system=\"" + sys + "\" time=\"" + time + "\"/>\n");
            if(data != null){
		        for(CPUObj o : data){
		        	out.write("<process=\"" + o.getProcess() + "\" thread=\"" + o.getThread() + "\" cpu=\"" + o.getCPU() + "\"/>\n");
		        }
            }
            out.write("</detailedcpu>\n\n");
            //Close the output stream
            out.close();
            
            
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void getStatusBarData(){
		String s = null;
		String gps = null;
		String bluetooth = null;
		String volume = null;
		long time = System.currentTimeMillis();
		String command = "dumpsys statusbar";
		try {
			
			Process process = Runtime.getRuntime().exec(command);
			
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // read the output from the command
            while ((s = stdInput.readLine()) != null) {
            	if(s.contains("gps")){
            		String tmp[] = s.trim().split("//s");
            		String tmp2[] = tmp[3].split("=");
            		gps = tmp2[1];
            	}
            	else if(s.contains("bluetooth")){
            		String tmp[] = s.trim().split("//s");
            		String tmp2[] = tmp[3].split("=");
            		bluetooth = tmp2[1];
            	}
            	else if(s.contains("volume")){
            		String tmp[] = s.trim().split("//s");
            		String tmp2[] = tmp[3].split("=");
            		volume = tmp2[1];
            	}
            }
            process.waitFor();
            
    	
            File root = Environment.getExternalStorageDirectory();
            File actualFile = new File(root, fileName);
            FileWriter fstream = new FileWriter(actualFile, true);
            BufferedWriter out = new BufferedWriter(fstream);
            if(gps != null && !gps.equals(gpsVal)){
            	gpsVal = gps;
            	out.write("<gps level=\"" + gps + "\" time=\"" + time + "\"/>\n\n");
            }
            if(bluetooth != null && !bluetooth.equals(bluetoothVal)){
            	bluetoothVal = bluetooth;
            	out.write("<bluetooth level=\"" + bluetooth + "\" time=\"" + time + "\"/>\n\n");
            }
            if(volume != null && !volume.equals(volumeVal)){
            	volumeVal = volume;
            	out.write("<volume level=\"" + volume + "\" time=\"" + time + "\"/>\n\n");
            }
            //Close the output stream
            out.close();
            
            
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void setStop(boolean val){
		stop = val;
	}
	
}
