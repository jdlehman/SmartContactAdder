package edu.wm.Android.Logger;

import java.util.ArrayList;

public class Logger {
	private SystemLogger sysLog;
	private TraceLogger traceLog;
	//private String traceName;

	public Logger(String traceName, ArrayList<String> packages){
		//this.traceName = traceName;
		sysLog = new SystemLogger(traceName, packages);
		traceLog = new TraceLogger(traceName);
	}
	
	public void startLog(){
		//start Android trace
		traceLog.startTrace();
		sysLog.setStop(false);
		sysLog.LogSystem();		
	}
	
	public void stopLog(){
		//stop Android trace
		sysLog.setStop(true);
		traceLog.stopTrace();
		
	}
}
