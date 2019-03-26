package stream18.aescp.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import stream18.aescp.controller.TestStatus;
import stream18.aescp.view.form.mode.ResultsForm;

public class Test {
	private static final long serialVersionUID = 1L;
 	private static Test theTest;
 	boolean isTestStarted = false;
 		
 	public void getTestStatus(TestStatus object) {
 		object.getStatus();	
 		System.out.println(object.getTestStatusAsText());
 		 
 	}
 	
	private  int testNumber;
	private  float testFillTimer;
	private String testResultPass;
	boolean START_CLICKED=false;
	boolean STOP_CLICKED=false;
	 
 
		private void runTest() {
			
			TestModeCfgManager t = new TestModeCfgManager();
			
		}
	public Test() {
		super();
		TestStatus ts = new TestStatus();
		this.getTestStatus(ts);	
		System.out.println("Fill running");
		System.out.println(getTestFillTimer());
		runTest();
	}

	public String getTestResultPass() {
		return testResultPass;
	}

//	public Test() {
	//	super();
	//	testNumber++;
//	}

	public void setTestResultPass(String testResultPass) {
		this.testResultPass = testResultPass;
	}

	public String getTestResultFail() {
		return testResultFail;
	}

	public void setTestResultFail(String testResultFail) {
		this.testResultFail = testResultFail;
	}


	private String testResultFail;
	
	
	public String getTestTimeStamp() {	
	  	Date dNow = new Date( );
	    SimpleDateFormat ft = 
	    new SimpleDateFormat ("yyyy.MM.dd 'at' hh:mm:ss");
	   // System.out.println("Current Date: " + ft.format(dNow));
		return ft.format(dNow);
	}
	
	public int updateTestCounter() {
	
		for (int i = 0; i <= 5; i++) {
	         try {
	             Thread.sleep(100);
	         } catch(InterruptedException e) {
	             e.printStackTrace();
	         }
	         System.out.println(i);
	        
	      //   count.setText(Integer.toString(i))
	     }
		return testNumber+1;
	}
	
	
	public float getTestFillTimer() {
		return testFillTimer;
	}

	public void setTestFillTimer(float testFillTimer) {
		this.testFillTimer = testFillTimer;
	}



	private  float testSettleTimer;
	private  float testTestTimer;

 

	public int getTestNumber() {
		return testNumber;
	}



	public void setTestNumber(int testNumber) {
		this.testNumber = testNumber;
	}



	public static Test getInstance() {
		if (theTest == null) {
			theTest = new Test();
		}
		return theTest;
	}	


}
