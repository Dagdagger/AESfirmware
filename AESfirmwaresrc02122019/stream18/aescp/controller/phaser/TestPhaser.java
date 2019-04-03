package stream18.aescp.controller.phaser;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javafx.scene.control.Alert;
import stream18.aescp.controller.TestLogger;
import stream18.aescp.controller.TestMode;
import stream18.aescp.controller.TestMode.Mode;
import stream18.aescp.controller.TestPhase;
import stream18.aescp.controller.TestVars;
import stream18.aescp.controller.TestPhase.Phase;
import stream18.aescp.controller.TestStatus;
import stream18.aescp.controller.TestStatus.Status;
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
	boolean fail = false;
	boolean stopCase = false;
	int jumps = 0;
	double [] valuesRead;
	int valueOfJump = 0;
	double lastValue;
	DecimalFormat df = new DecimalFormat("0.0000##");
	double totalDecay = 0.0000;
	String errorMessage = "Failed";
	
	List<Double> num = new ArrayList<>();
	
	public void setPhaseVar(Phase var) {
		phaseVar.setPhase(var);
	}
 public ResultsForm getResultsForm() {
	 return theresultsform;
 }
 

	public TestPhaser(TestPhase testPhaseVar) {
		this.phaseVar = testPhaseVar;	
	 
	}
	
	public void setStopCase(boolean setting) {
		this.stopCase= setting;
	}
	
	public void resetTimers () {
		TestModeCfgManager.setTimers();
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
			//while(!Thread.interrupted()) {
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
			
			System.out.println(phaseCfgTime);
			
			try {
				
				
			/*	if( phaseVar.getPhase() == Phase.FAIL) {
					pass = false;
				}*/
				phaseCfgTime = 1000*setTimer();
				
				if (phaseCfgTime == -1000) {
					phaseCfgTime = phaseCfg.getPhaseTime();
				}
				System.out.println(phaseCfgTime);
				phaseCfg.fireValves(phaseCfg.getValves());
				
				
				double[] testArray;
				long timer = (long)phaseCfgTime;
				double firstValue = 0;
				int i = 2;
				int samplesToFill = 10;
				// Acquire data on enabled ADCs until the time required has elapsed
				phaseStartTime = System.currentTimeMillis();
				while ((System.currentTimeMillis() - phaseStartTime) < phaseCfgTime) {
					// Acquire data on active ADCs
				//	System.out.println(phaseCfgTime);
					
					
					
					
					if (phaseCfg.anyAdcEnabled()) {
						i--;
						long adcStartTime = System.currentTimeMillis();
						adcValues = ADCDriver.readADCs(phaseCfg.getADCs());
						if(i == 0) {
							firstValue = adcValues[3]; 
						}
						
						lastValue = adcValues[3];
						
						/* Count a jump during reading the values */
						
						if (lastValue != adcValues[3]) {
							jumps++;
							
						}
						
						num.add(adcValues[3]);
						/* If the pressure for filling is not met enough times during the fill sequence reject the pressure */
						
						if (phaseVar.getPhase() == Phase.FILL) {
							double pressure =  TestVars.getpressureVar();
							System.out.println("+ or -" + TestVars.getpressureToleranceVar()*pressure);
							
							
							
							
							
							
							
							if ((adcValues[3]  > (pressure - (TestVars.getpressureToleranceVar()*pressure))) && (adcValues[3]  < (pressure + (TestVars.getpressureToleranceVar()*pressure)))) {
								System.out.println("Reached once");
								samplesToFill--;
								System.out.println(samplesToFill);
								if(samplesToFill == 0) {
								filled = true;
								System.out.println("Reached Pressure");
								break;
								}
							}
						}
						
						
						/* End of checking for fill values */
						
						
						storeAdcValues(adcValues);
						TopForm.getInstance(null).setPressure(Double.toString(adcValues[3]));	
						// Wait until at least 100 millisecond have elapsed
						while (System.currentTimeMillis() - adcStartTime < 100);
					} /*else {
						sleep(timer); // Wait 1 us, in case there is nothing else to do	
					 
					}*/
				}
				double pressure =  TestVars.getpressureVar();
				if (phaseCfg.anyAdcEnabled()) {
				System.out.println("Delta P: " + (firstValue - adcValues[3]));
				}
				 if (phaseVar.getPhase() == Phase.SETTLE) {
						
						if ((firstValue - adcValues[3]) > 40) {
								//phaseVar.setPhase(Phase.FAIL);
								phaseCfg = theTestModeCfg.getPhaseCfg(phaseVar.getPhase());
								System.out.println("FAILED DURING SETTLE");
								pass = false;
								String temp = df.format(adcValues[3]);
								errorMessage = "GROSS LEAK";
								theresultsform.setResult("FAIL GROSS LEAK" + temp);
						}
						
						else {
						pass = true;
						//theresultsform.setResultFieldWait(false);
                        //theresultsform.setResult(Double.toString(adcValues[3]) + TopForm.getUnits());
						theresultsform.setResult("Delta P" + Double.toString((firstValue - adcValues[3]))+ " " + TopForm.getUnits());
						}
						
					}
				
				
				if (phaseVar.getPhase() == Phase.FILL) {
					
					//if (((firstValue - adcValues[3])/firstValue < TestVars.getpressureToleranceVar()) && (!filled)) { 
					
					if (!filled && (adcValues[3]  < (pressure - (TestVars.getpressureToleranceVar()*pressure)))) {
							//phaseVar.setPhase(Phase.FAIL);
							phaseCfg = theTestModeCfg.getPhaseCfg(phaseVar.getPhase());
							System.out.println("FAILED DURING FILL DIDNT REACH PRESSURE");
							pass = false;
						
							String temp = df.format(adcValues[3]);
							errorMessage = "LOW FILL";
							theresultsform.setResult("LOW FILL " + temp);
					}
					else if (!filled && (adcValues[3]  > (pressure + (TestVars.getpressureToleranceVar()*pressure)))) {
						//phaseVar.setPhase(Phase.FAIL);
						phaseCfg = theTestModeCfg.getPhaseCfg(phaseVar.getPhase());
						System.out.println("FAILED DURING FILL DIDNT REACH PRESSURE");
						pass = false;
						String temp = df.format(adcValues[3]);
						errorMessage = "HIGH FILL";
						theresultsform.setResult("HIGH FILL " + temp);
				    }
				
					else {
					pass = true;
					//theresultsform.setResultFieldWait(false);
					theresultsform.setResult(Double.toString(adcValues[3]) + TopForm.getUnits());
					//theresultsform.setResult("Delta P" + Double.toString((firstValue - adcValues[3]))+ " " + TopForm.getUnits());
					}
					
				}
				System.out.println(TestVars.getTestDecayvar());
			if (phaseVar.getPhase() == Phase.TEST) {
				
				
				
				for (Iterator<Double> iterator = num.iterator(); iterator.hasNext();) {
				    Double number = iterator.next();
				    if (number  == 0.0) {
				        System.out.println("This is Even Number: " + number);
				        iterator.remove();
				    }

				}
				
				
					double ddecay = 0.0;
					if(firstValue -adcValues[3] < 0) {
						ddecay = -1*(firstValue -adcValues[3]);
						System.out.println(ddecay);
					}
					if (ddecay > TestVars.getTestDecayvar()) {
						
						
						//phaseVar.setPhase(Phase.FAIL);
						phaseCfg = theTestModeCfg.getPhaseCfg(phaseVar.getPhase());
						System.out.println("FAILED DURING DECAY TEST");
						pass = false;
						
						String temp = df.format(ddecay);
						errorMessage = "Fail Decay Leak";
						theresultsform.setResult("FAIL Decay LEAK " + temp );
					}  else {
						totalDecay = (firstValue - adcValues[3]);
						pass = true;
					//theresultsform.setResultFieldWait(false);
					//theresultsform.setResult(Double.toString(adcValues[3]) + TopForm.getUnits());
					theresultsform.setResult("Delta P" + Double.toString((firstValue - adcValues[3]))+ " " + TopForm.getUnits());
					}
					
				}
				if (phaseVar.getPhase() == Phase.RESULTS) {
					if(!fail) {
						System.out.print("PASSED TEST");
						boolean turnGreenLightOn[] = {false, false, false, false, false,true, false, false, false, false};
						boolean off[] = {false, false, false, false, false,false, false, false, false, false};
					//	theresultsform.setResult(Double.toString(adcValues[3]) + TopForm.getUnits());
						theresultsform.setResultFieldDecay(Double.toString(totalDecay));
						theresultsform.setResult(Double.toString(totalDecay)+ " " + TopForm.getUnits());
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
						fail = false;
				}
					pass = true;
				}
				
				
				 if (stopCase) {
					phaseVar.setPhase(Phase.STOPPED);
					stopCase = false;
				 }
					
				 else if (pass) {
					phaseVar.setPhase(phaseCfg.getNextPhase());
				}
				 else {
					
					phaseVar.setPhase(Phase.FAIL);
					pass = true;
					fail = true;
				}
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
			
		//interrupt();
			//}
			

}
		
		
		public long setTimer() {
			Phase hello = phaseVar.getPhase(); 
			
			switch(hello) {
			case FAIL:
				return (long)TestVars.getBleedTime();
			case H_FWD:
				return (long)TestVars.getSliderTime();
			case V_FWD:
				return (long)TestVars.getClampTime();
			case EXHAUST:
				return (long)TestVars.getBleedTime();
			case FILL:			 
				return (long)TestVars.getChargevar();
			case SETTLE:
				return (long)TestVars.getSettlevar();
			case TEST:
				return (long)TestVars.getTestvar();
			case VENT:
				return (long)TestVars.getBleedTime();
			case V_BACK:
				return (long)TestVars.getClampTime();
			case H_BACK:
				return (long)TestVars.getSliderTime();
			default:
				break;
		}
			return -1;
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
			ADCValuesBuffer.getVals(3, y, limit);
			

			Form.plotPanel.setData(x, y);
		} catch (Exception e) {
			TestLogger.getInstance().logSys("Data buffer overflow, restart adding. Count " + ADCValuesBuffer.getCount() + 
					", Errs " + ADCValuesBuffer.getErrs());
		}
	}
}

