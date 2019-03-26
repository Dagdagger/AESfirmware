package stream18.aescp.controller.phaser;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javafx.scene.control.Alert;
import stream18.aescp.controller.TestLogger;
import stream18.aescp.controller.TestMode;
import stream18.aescp.controller.TestMode.Mode;
import stream18.aescp.controller.TestPhase;
import stream18.aescp.controller.TestVars;
import stream18.aescp.controller.TestPhase.Phase;
import stream18.aescp.drivers.ADCDriver;
import stream18.aescp.model.ADCValuesBuffer;
import stream18.aescp.model.PhaseCfg;
import stream18.aescp.model.TestModeCfg;
import stream18.aescp.model.TestModeCfgManager;
import stream18.aescp.view.button.StopButton;
import stream18.aescp.view.form.Form;
import stream18.aescp.view.form.StatusForm;
import stream18.aescp.view.form.TopForm;
import stream18.aescp.view.form.mode.ResultsForm;
import stream18.aescp.view.form.mode.VacuumChamberResultsForm;
import stream18.aescp.view.screen.logs.ShowLogsScreen2;
import stream18.aescp.view.screen.mode.VacuumChamberScreen;

/**
 * @author egarcia
 * This class takes care of the appropriate timing of the test, and opens/closes
 * the appropriate valves on each phase
 */
public class TestPhaser extends Thread {
	long initialTestTime;
	long totalTestTime;
	private TestModeCfg theTestModeCfg;
	private TestPhase phaseVar;
	private ResultsForm theresultsform;
	double adcValues[];
	boolean pass = true;
	boolean filled = false;
	String errorMessage = "Failed";
	
	public void setPhaseVar(Phase var) {
		phaseVar.setPhase(var);
	}
 public ResultsForm getResultsForm() {
	 return theresultsform;
 }
 

	public TestPhaser(TestPhase testPhaseVar) {
		this.phaseVar = testPhaseVar;	
	 
	}

	public void startNewTest(Mode mode) {
		
	 
 		// Find the TestModeCfg for the TestMode
theTestModeCfg = TestModeCfgManager.find(mode);	
theresultsform = (ResultsForm) VacuumChamberResultsForm.getInstance(null);


		
		System.out.println("theTestModeCfg " + theTestModeCfg);
		
		initialTestTime = System.currentTimeMillis();
		// Setup to start with the first phase
		phaseVar.setPhase(Phase.H_FWD);
		// Finally start a new Execution thread for this Test
		// and return	
		this.start();
	}
	    
		public void run() {
			while(!Thread.interrupted()) {
				theresultsform.setResultFieldTesting(true);
		
			PhaseCfg phaseCfg;
			long phaseStartTime;
			double phaseCfgTime; 
			// Cleans the buffer, just setting the length to 0
			ADCValuesBuffer.reset();
			
			
			while(phaseVar.getPhase() != Phase.FINISHED) {
				// Obtain the config for this phase		
				
          phaseCfg = theTestModeCfg.getPhaseCfg(phaseVar.getPhase());
			 
				
			if ( phaseCfg == null) {	
				System.out.println("var is null ");    
			//	theTestPhaser.startNewTest(Mode.VACUUMCHAMBER);		
				  	TestLogger.getInstance().logSys("Next phase undefined, ending test");			   		 	
				 	break;
				 	 		
				}
	
	 
		
				// Finally start a new Execution thread for this Test
				// and return	
			System.out.println(phaseVar.getTestPhaseAsText());  
		 
			
		 
			
			phaseCfgTime = phaseCfg.getPhaseTime();
			
			try {
				
				phaseCfg.fireValves(phaseCfg.getValves());
				long timer = (long)phaseCfgTime;
				double firstValue = 0;
				int i = 2;
				int samplesToFill = 3;
				// Acquire data on enabled ADCs until the time required has elapsed
				phaseStartTime = System.currentTimeMillis();
				while ((System.currentTimeMillis() - phaseStartTime) < phaseCfgTime) {
					// Acquire data on active ADCs
					if (phaseCfg.anyAdcEnabled()) {
						i--;
						long adcStartTime = System.currentTimeMillis();
						adcValues = ADCDriver.readADCs(phaseCfg.getADCs());
						if(i == 0) {
							firstValue = adcValues[3]; 
						}
						if (phaseVar.getPhase() == Phase.FILL) {
							double pressure =  TestVars.getpressureVar();
							if ((adcValues[3]  > (pressure - (0.5*pressure))) && (adcValues[3]  < (pressure + (0.5*pressure)))) {
								System.out.println("Reached Pressure");
								
								if(samplesToFill == 0) {
								boolean filled = true;
								break;
								}
							}
						}
						storeAdcValues(adcValues);
						TopForm.getInstance(null).setPressure(Double.toString(adcValues[3]));	
						// Wait until at least 100 millisecond have elapsed
						while (System.currentTimeMillis() - adcStartTime < 100);
					} else {
						sleep(timer); // Wait 1 us, in case there is nothing else to do	
					 
					}
				}
				if (phaseCfg.anyAdcEnabled()) {
				System.out.println("Delta P: " + (firstValue - adcValues[3]));
				}
				 if (phaseVar.getPhase() == Phase.SETTLE) {
						
						if ((((firstValue - adcValues[3])/firstValue) < 0.70) && (!filled)) {
								phaseVar.setPhase(Phase.FAIL);
								phaseCfg = theTestModeCfg.getPhaseCfg(phaseVar.getPhase());
								System.out.println("FAILED TEST");
								pass = false;
								theresultsform.setResult("FAIL GROSS LEAK " + Double.toString(adcValues[3]));
						}
						
						else {
						pass = true;
						//theresultsform.setResultFieldWait(false);
						theresultsform.setResult(Double.toString(adcValues[3]) + TopForm.getUnits());
						}
						
					}
				
				
				if (phaseVar.getPhase() == Phase.FILL) {
					
					if (((firstValue - adcValues[3])/firstValue < 0.70) && (!filled)) {
							phaseVar.setPhase(Phase.FAIL);
							phaseCfg = theTestModeCfg.getPhaseCfg(phaseVar.getPhase());
							System.out.println("FAILED TEST");
							pass = false;
							theresultsform.setResult("FAIL GROSS LEAK " + Double.toString(adcValues[3]));
					}
					
					else {
					pass = true;
					//theresultsform.setResultFieldWait(false);
					theresultsform.setResult(Double.toString(adcValues[3]) + TopForm.getUnits());
					}
					
				}
				
			if (phaseVar.getPhase() == Phase.TEST) {

					if (firstValue - adcValues[3] >  TestVars.getTestDecayvar()) {
						
						
						phaseVar.setPhase(Phase.FAIL);
						phaseCfg = theTestModeCfg.getPhaseCfg(phaseVar.getPhase());
						System.out.println("FAILED DECAY TEST");
						pass = false;
						theresultsform.setResult("FAIL Decay LEAK " + Double.toString(adcValues[3]));
					}  else {

						pass = true;
					//theresultsform.setResultFieldWait(false);
					theresultsform.setResult(Double.toString(adcValues[3]) + TopForm.getUnits());
					}
					
				}
				if (phaseVar.getPhase() == Phase.RESULTS) {
					if(pass) {
						System.out.print("PASSED TEST");
						boolean turnGreenLightOn[] = {false, false, false, false, false,true, false, false, false, false};
						boolean off[] = {false, false, false, false, false,false, false, false, false, false};
						theresultsform.setResult(Double.toString(adcValues[3]) + TopForm.getUnits());
						ADCDriver.sendData(turnGreenLightOn);
						theresultsform.setResultFieldPass(true);
						ADCDriver.sendData(off);
						
					} else {
						System.out.println("FAILED TEST");
						boolean turnRedLightOn[] = {false, false, false, false, false,false, true, false, false, false};
						boolean off[] = {false, false, false, false, false,false, false, false, false, false};
						ADCDriver.sendData(turnRedLightOn);
						theresultsform.setResultFieldFail(true, errorMessage);
						ADCDriver.sendData(off);
				}
					pass = true;
				}
					phaseVar.setPhase(phaseCfg.getNextPhase());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}		
			// The test has finished, this thread just ends
			
			
			

	System.out.println("THREAD ENDS RUNNING");
	for (int i=0; i<ADCValuesBuffer.getCount(); i++) {
		System.out.println(ADCValuesBuffer.getVal(i, 0) + ", " + ADCValuesBuffer.getVal(i, 1));
	}
	



	/// Only get 100 points
	///int limit = ADCValuesBuffer.getCount();
	//int limit = 100;
	//double[] x = new double[limit];
	//double[] y = new double[limit];
	//ADCValuesBuffer.getVals(0, x, limit);
	//ADCValuesBuffer.getVals(1, y, limit);
	//Form.plotPanel.setData(x, y);

	System.out.println("END DATA");

			String timeStamp = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").format(Calendar.getInstance().getTime());
			TestVars.setTestTimeStampvar(timeStamp);
		 
			
			ShowLogsScreen2.addDataLine();
			
			interrupt();
			}
			
		
		
		
}

	// This is the main loop
	// 1. On each phase one or more valves are set to a specific state
	//    The status for each valve on each phase is defined in another class
	// 2. Once the valves have been setup as required, we just wait until
	//	  the phase completion time elapses
	// 3. Once the time elapses, get ready for next phase
	// 4. And do the transition to next phase
	//	
	
		/*
 
	public void runme() {		
		PhaseCfg phaseCfg;
		long phaseStartTime;
		double phaseCfgTime; 
		// Cleans the buffer, just setting the length to 0
		ADCValuesBuffer.reset();	
				
		while(phaseVar.getPhase() != Phase.FINISHED) {
			// Obtain the config for this phase		
			
	          phaseCfg = theTestModeCfg.getPhaseCfg(phaseVar.getPhase());
				 
					
				if ( phaseCfg == null) {	
					System.out.println("var is null ");    
				//	theTestPhaser.startNewTest(Mode.VACUUMCHAMBER);		
					  	TestLogger.getInstance().logSys("Next phase undefined, ending test");			   		 	
					 	break;
					 	 		
					}
		
		 
			
					// Finally start a new Execution thread for this Test
					// and return	
				System.out.println(phaseVar.getTestPhaseAsText());  
			 
				
			 
				
				phaseCfgTime = phaseCfg.getPhaseTime();
				
				try {
					
					phaseCfg.fireValves(phaseCfg.getValves());
					long timer = (long)phaseCfgTime;
					double firstValue = 0;
					int i = 4;
					// Acquire data on enabled ADCs until the time required has elapsed
					phaseStartTime = System.currentTimeMillis();
					while ((System.currentTimeMillis() - phaseStartTime) < phaseCfgTime) {
						// Acquire data on active ADCs
						if (phaseCfg.anyAdcEnabled()) {
							i--;
							long adcStartTime = System.currentTimeMillis();
							adcValues = ADCDriver.readADCs(phaseCfg.getADCs());
							if(i == 0) {
								firstValue = adcValues[3]; 
							}
							storeAdcValues(adcValues);
							TopForm.getInstance(null).setPressure(Double.toString(adcValues[1]));	
							// Wait until at least 100 millisecond have elapsed
							while (System.currentTimeMillis() - adcStartTime < 100);
						} else {
							sleep(timer); // Wait 1 us, in case there is nothing else to do	
						 
						}
					}
					
					
					if (phaseVar.getPhase() == Phase.FILL) {
						
						if ((firstValue - adcValues[1])/firstValue < 0.70) {
								phaseVar.setPhase(Phase.FAIL);
								phaseCfg = theTestModeCfg.getPhaseCfg(phaseVar.getPhase());
								System.out.println("FAILED TEST");
								break;
						}
						
					}
						phaseVar.setPhase(phaseCfg.getNextPhase());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
		// The test has finished, this thread just ends
System.out.println("THREAD ENDS RUNNING");
for (int i=0; i<ADCValuesBuffer.getCount(); i++) {
	System.out.println(ADCValuesBuffer.getVal(i, 0) + ", " + ADCValuesBuffer.getVal(i, 1));
}

//// Only get 100 points
////int limit = ADCValuesBuffer.getCount();
//int limit = 100;
//double[] x = new double[limit];
//double[] y = new double[limit];
//ADCValuesBuffer.getVals(0, x, limit);
//ADCValuesBuffer.getVals(1, y, limit);
//Form.plotPanel.setData(x, y);

System.out.println("END DATA");

		String timeStamp = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").format(Calendar.getInstance().getTime());
		TestVars.setTestTimeStampvar(timeStamp);
	 
		
		ShowLogsScreen2.addDataLine();
 

	}*/
	
	static int debugCount = 0;
	
	// Store the values in a data structure with timestamp
	// This will be shared with the graphics displayer
	// Also, it will be saved on disk
	private void storeAdcValues(double[] adcValues) {
		try {			
			ADCValuesBuffer.store(System.currentTimeMillis(), adcValues);

			int limit = ADCValuesBuffer.getCount();
//			int limit = 52;
			
			double[] x = new double[limit];
			double[] y = new double[limit];
//			double[] x = new double[512];
//			double[] y = new double[512];
			ADCValuesBuffer.getVals(-1, x, limit);
			ADCValuesBuffer.getVals(1, y, limit);

			Form.plotPanel.setData(x, y);
		} catch (Exception e) {
			TestLogger.getInstance().logSys("Data buffer overflow, restart adding. Count " + ADCValuesBuffer.getCount() + 
					", Errs " + ADCValuesBuffer.getErrs());
		}
	}
}

