package edu.wm.Android.Logger;

public class CPUObj {

	private String threadName;
	private String CPU;
	private String process;
	
	public CPUObj(String proc){
		process = proc;
	}
	
	public void setThread(String tName){
		threadName = tName;
	}
	
	public String getThread(){
		return threadName;
	}
	
	public void setCPU(String cName){
		CPU = cName;
	}
	
	public String getCPU(){
		return CPU;
	}
	
	public String getProcess(){
		return process;
	}
}
