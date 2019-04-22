package stream18.aescp.controller.phaser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import stream18.aescp.view.button.StartButton;
import stream18.aescp.view.button.StopButton;
import stream18.aescp.view.form.Form;
import stream18.aescp.view.form.StatusForm;
import stream18.aescp.view.form.TopForm;
import stream18.aescp.view.form.mode.ResultsForm;
import stream18.aescp.view.form.mode.VacuumChamberResultsForm;
import stream18.aescp.view.screen.logs.ShowLogsScreen2;
import stream18.aescp.view.screen.mode.VacuumChamberScreen;
import stream18.aescp.model.DBConnection;

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
	boolean retreatCase = false; // case for stopping before slider goes in
	int jumps = 0;
	int numberOfReadings;
	double 	filltoSettle; // last Fill phase number read
	double [] valuesRead;
	int valueOfJump = 0;
	double lastValue;
	DecimalFormat df = new DecimalFormat("0.000##");
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
	public void setRetreatCase(boolean setting) {
	this.retreatCase= setting;
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
			theresultsform.setResultFieldTesting(true);
			PhaseCfg phaseCfg;
			long phaseStartTime;
			double phaseCfgTime; 
			// Cleans the buffer, just setting the length to 0
			ADCValuesBuffer.reset();
			
			//while(!Thread.interrupted()) {	
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
				
				
			/*	if( phaseVar.getPhase() == Phase.FAIL) {
					pass = false;
				}*/
				
				if( phaseVar.getPhase() == Phase.STOPPED) {
					theresultsform.setResultFieldStopping(true);
				}
				
				
				if (phaseVar.getPhase() == Phase.H_FWD) {
					theresultsform.setResultFieldDecay(" Waiting...");
				}
				phaseCfgTime = setTimer();
				numberOfReadings = 0;
				if (phaseCfgTime == -1000) {
					phaseCfgTime = phaseCfg.getPhaseTime();
				}
				phaseCfg.fireValves(phaseCfg.getValves());
				
				
				long timer = (long)phaseCfgTime;
				double firstValue = 0;
				int i = 2;
				int samplesToFill =0*(int)(setTimer());
				//int samplesToFill = 7;
				System.out.println("Samples to Fill " + samplesToFill);
				/*if (samplesToFill <= 3) {
					samplesToFill = 1;
				}*/
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
						
						if (lastValue != adcValues[3]) {
							jumps++;
						}
						
						lastValue = adcValues[3];
						
						/* Count a jump during reading the values */
						
						
						num.add(adcValues[3]);
						numberOfReadings++;
						/* If the pressure for filling is not met enough times during the fill sequence reject the pressure */
						
						if (phaseVar.getPhase() == Phase.FILL) {
							double pressure =  TestVars.getpressureVar();
							
							
							
							
							
							
						/* this line does need to be changed to the ArrayList 	*/
						/*	if ((adcValues[3]  > (pressure - (TestVars.getpressureToleranceVar()*pressure))) && (adcValues[3]  < (pressure + (TestVars.getpressureToleranceVar()*pressure)))) {
								System.out.println("Reached once");
								samplesToFill--;
								System.out.println(samplesToFill);
								if(samplesToFill == 0) {*/
								filled = true;
							/*	System.out.println("Reached Pressure");
								break;
								}
							}*/ 
						}
						
						
						/* End of checking for fill values */
						
						
						storeAdcValues(adcValues);
						TopForm.getInstance(null).setPressure(Double.toString(adcValues[3]));	
						// Wait until at least 100 millisecond have elapsed
						while (System.currentTimeMillis() - adcStartTime < 100) {};
					} /*else {
						sleep(timer); // Wait 1 us, in case there is nothing else to do	
					 
					}*/
				}
			
				long timeElapsed = System.currentTimeMillis() - phaseStartTime; 
				System.out.println("Time Elapsed" + timeElapsed);
				/* END OF ALL PHASES AND WHERE WE CAN CHECK FOR PRESSURE VALUES AND RESULTS 
				 * MANIPULATABLE VALUES ARE STORED IN the num ArrayList.
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 */
				
				/*maybe write a while loop here */
				
				
				if(phaseVar.getPhase() == Phase.FILL) {
			if(num.size() > 2) {	
				if (num.get(num.size()-1) != 0) {
					/* If the reading acquired during the last fill of phase,
					 * is equal to zero discard the reading.
					 */
				   filltoSettle = num.get(num.size()-1);	
				} else {
					filltoSettle = adcValues[3];
				}
					
			}
				}
					
				
				/* this line gets the Pressure set in the TestVars */
				double pressure =  TestVars.getpressureVar();
				if (phaseCfg.anyAdcEnabled()) {
				}
				 if (phaseVar.getPhase() == Phase.SETTLE) {
					 
					 
					/* if (adcValues[3] < filltoSettle - (filltoSettle*(0.01*TestVars.getpressureToleranceVar()))){
						    System.out.println("LOST TOO MUCH FROM FILL TO SETTLE");
							pass = false;
							TestVars.setdidPass("Gross Leak in Settle");
							theresultsform.setResultFieldDecay(" Gross Leak");
							String temp = df.format(adcValues[3]);
							errorMessage = "GROSS LEAK";
							theresultsform.setResult("FAIL GROSS LEAK " + temp);
						 
					 }
					 if (adcValues[3] < TestVars.getmaxPressureDrop());{
						    System.out.println("LOST TOO MUCH according to pressure drop in SETTLE");
							pass = false;
							TestVars.setdidPass("Gross Leak in Settle");
							String temp = df.format(adcValues[3]);
							theresultsform.setResultFieldDecay(" Gross Leak");
							errorMessage = "GROSS LEAK";
							theresultsform.setResult("FAIL GROSS LEAK " + temp);
						 
					 } */
						 
					 
						
					 /*
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
					  	*/
					 if( num.size()-1 > 10) {
					 for (int k = 0; k < 10; k++) {
						 num.remove(k);
					 }
					 }
					
					 double averageSettle = getTotalAverage(num);
					 System.out.println("");
					 System.out.println("Average Settle Pressure: " + averageSettle);
					 if(averageSettle < TestVars.getmaxPressureDrop() ) {
						 
						 System.out.println("FAILED DURING SETTLE");
							pass = false;
							String temp = df.format(averageSettle);
							theresultsform.setResultFieldDecay(" Gross Leak");
							 DBConnection.insertAlarm("Fail Gross Leak", ("Test ran by " + TestVars.getTestUservar()));
							errorMessage = "GROSS LEAK";
							theresultsform.setResult("FAIL GROSS LEAK " + temp);
						 
					 }
					 
					/* else if(adcValues[3] > TestVars.getminPressureDrop()) {
						 System.out.println("FAILED DURING SETTLE");
							pass = false;
							String temp = df.format(adcValues[3]);
							errorMessage = "NOT ENOUGH DROP";
							theresultsform.setResult("Failure " + temp);
						 
					 } */
						
						else {
						pass = true;
						//theresultsform.setResultFieldWait(false);
                     //theresultsform.setResult(Double.toString(adcValues[3]) + TopForm.getUnits());
						theresultsform.setResult(df.format((firstValue - adcValues[3]))+ " " + TopForm.getUnits());
						theresultsform.setResult("Passed Settle Phase");

						}
					 
					 
				 }
				
				
				if (phaseVar.getPhase() == Phase.FILL) {
					
					for (Iterator<Double> iterator = num.iterator(); iterator.hasNext();) {
					    Double number = iterator.next();
					    if (number  == 0.0) {
					       // System.out.println("This is Even Number: " + number);
					        iterator.remove();
					    }
					}
					/* Once criteria for fill is met wait an extra half second to finish filling*/
					
					 
					if(filled && (setTimer() > 1000)) {
						long waitTime = System.currentTimeMillis();
					while (System.currentTimeMillis() - waitTime < 500) {};
					}

					/* Prints whatever was stored in the temp arrayList */
					int SizetoRead = 0;
					if(numberOfReadings >= 3 ) {
					SizetoRead = numberOfReadings/3;
					}else {
						SizetoRead = numberOfReadings;
					}
					System.out.println("sizeToread" + SizetoRead);
					double averageOfLastValues = 0;
					System.out.println("Number of things read " + num.size());
					if(num.size()  >= SizetoRead) {
						System.out.println("Calculating the Average");
					int index = num.size()-SizetoRead;
					double avgTotal = 0; 
					//averageOfLastValues;
					
					for (int j = index; j< num.size(); j++) {
						if ( !(j < 0)) {
						avgTotal= num.get(j) + avgTotal;
						}
						
					}
					
					
					
					averageOfLastValues = avgTotal/SizetoRead;
					
					System.out.println("Average of Last Values" + averageOfLastValues);
					}
					
					if (!filled && (averageOfLastValues < (pressure - ((0.01*TestVars.getmaxDropPercentage())*pressure)))) {
							//phaseVar.setPhase(Phase.FAIL);
						System.out.println("PRESSURE DROP" + (0.01*TestVars.getminDropPercentage())*pressure);
							phaseCfg = theTestModeCfg.getPhaseCfg(phaseVar.getPhase());
							System.out.println(averageOfLastValues);
							System.out.println("FAILED DURING FILL DIDNT REACH PRESSURE");
							pass = false;
							TestVars.setdidPass("Low Fill");
							theresultsform.setResultFieldDecay(" Low Fill");
						 
							String temp = df.format(adcValues[3]);
							errorMessage = "LOW FILL";
							theresultsform.setResult("LOW FILL " + df.format(averageOfLastValues));
					}
					else if (!filled && (averageOfLastValues  > (pressure - ((0.01*TestVars.getminDropPercentage())*pressure)))) {
						//phaseVar.setPhase(Phase.FAIL);
						System.out.println(TestVars.getmaxDropPercentage()*pressure);
						System.out.println(averageOfLastValues);
						phaseCfg = theTestModeCfg.getPhaseCfg(phaseVar.getPhase());
						System.out.println("FAILED DURING FILL WENT ABOVE PRESSURE");
						pass = false;
						TestVars.setdidPass("High Fill");
						theresultsform.setResultFieldDecay(" High Fill");
						String temp = df.format(adcValues[3]);
						errorMessage = "HIGH FILL";
						theresultsform.setResult("HIGH FILL " + df.format(averageOfLastValues));
				    }
				
					else {
					pass = true;
					//theresultsform.setResultFieldWait(false);
					theresultsform.setResult("Passed Fill Phase");
					//theresultsform.setResult("Delta P" + Double.toString((firstValue - adcValues[3]))+ " " + TopForm.getUnits());
					}
					
				}
				
				
				
		/* TEST PHASE CHECK  */
				
			if (phaseVar.getPhase() == Phase.TEST) {
				
				//System.out.println("jumps: " + jumps);
				/* delete all 0s */
				for (Iterator<Double> iterator = num.iterator(); iterator.hasNext();) {
				    Double number = iterator.next();
				    if (number  == 0.0) {
				        iterator.remove();
				    }

				}
					
					double ddecay = 0.0;
					if(num.size() > 1) {
					ddecay = num.get(0) - num.get(num.size() - 1);
					}
				
					if(ddecay < 0) {
						ddecay = 0.0;
					/*	ddecay = -1*(ddecay);
						System.out.println(ddecay);*/
					}
					if (ddecay > TestVars.getTestDecayvar()) {
						
						
						//phaseVar.setPhase(Phase.FAIL);
						phaseCfg = theTestModeCfg.getPhaseCfg(phaseVar.getPhase());
						System.out.println("FAILED DURING DECAY TEST");
						pass = false;
						
						String temp = df.format(ddecay);
						errorMessage = "Fail Decay Leak";
						theresultsform.setResult("FAIL Decay LEAK " + temp );
					 DBConnection.insertAlarm("Fail Decay Leak", ("Test ran by " + TestVars.getTestUservar()));
						theresultsform.setResultFieldDecay(df.format(ddecay));
					}  else {
						//totalDecay = (firstValue - adcValues[3]);
						pass = true;
						theresultsform.setResultFieldPassing(true, "PASSED");
					//theresultsform.setResultFieldWait(false);
					//theresultsform.setResult(Double.toString(adcValues[3]) + TopForm.getUnits());
					String temp = df.format(ddecay);
					totalDecay = ddecay;
					theresultsform.setResultFieldDecay(temp);
					}
					
				}
			
			/* RESULTS PHASE CHECK */
				if (phaseVar.getPhase() == Phase.RESULTS) {
					if(!fail) {
						System.out.print("PASSED TEST");
						boolean turnGreenLightOn[] = {false, false, false, false, false,true, false, false, false, false};
						boolean off[] = {false, false, false, false, false,false, false, false, false, false};
					//	theresultsform.setResult(Double.toString(adcValues[3]) + TopForm.getUnits());
						
						/*if(totalDecay < 0) {
							totalDecay = -1*(firstValue -adcValues[3]);
							System.out.println(totalDecay);
						}*/
						theresultsform.setResultFieldDecay(df.format(totalDecay));
						theresultsform.updateResultFieldPasses();
						//theresultsform.setResult(df.format(totalDecay)+ " " + TopForm.getUnits());
						theresultsform.setResult("Passed last Test");
						ADCDriver.sendData(turnGreenLightOn);
						theresultsform.setResultFieldPass(true);
						TestVars.setdidPass("Pass" + df.format(totalDecay));
						ADCDriver.sendData(off);
						
						
					} else {
						System.out.println("FAILED TEST");
						boolean turnRedLightOn[] = {false, false, false, false, false,false, true, false, false, false};
						boolean off[] = {false, false, false, false, false,false, false, false, false, false};
						ADCDriver.sendData(turnRedLightOn);
						TestVars.setdidPass("Failed Decay Test");
						theresultsform.setResultFieldFail(true, errorMessage);
						ADCDriver.sendData(off);
						fail = false;
				}
					pass = true;
				}
				
				num.clear();
				numberOfReadings = 0;
				jumps = 0;
				 if (stopCase) {
					phaseVar.setPhase(Phase.STOPPED);
					theresultsform.setResult("");
					StartButton.getInstance().setDisabled(true);	
					stopCase = false;
				 }
				 else if(retreatCase) {
					 
					 phaseVar.setPhase(Phase.FINISHED);
					 theresultsform.setResultFieldWait(true);
					 retreatCase = false;
				 }
					
				 else if (pass || phaseVar.getPhase()== Phase.STOPPED || phaseVar.getPhase() == Phase.STOP_BACK || phaseVar.getPhase() == Phase.STOP_HBACK)  {
					 phaseVar.setPhase(phaseCfg.getNextPhase());
				}
				 else if(!pass && phaseVar.getPhase()!= Phase.STOPPED && phaseVar.getPhase() != Phase.STOP_BACK && phaseVar.getPhase() != Phase.STOP_HBACK){
					
					phaseVar.setPhase(Phase.FAIL);
					theresultsform.setResultFieldFailing(true, errorMessage);
					pass = true;
					fail = true;
				}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}		
			
			// The test has finished, this thread just ends
			
			
			StartButton.getInstance().setDisabled(false);	
			theresultsform.setResultFieldWait(true);
	System.out.println("THREAD ENDS RUNNING");
	theresultsform.updateResultFieldCycles();
	
	
	
	/*for (int i=0; i<ADCValuesBuffer.getCount(); i++) {
		System.out.println(ADCValuesBuffer.getVal(i, 0) + ", " + ADCValuesBuffer.getVal(i, 1));
	}*/
	



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
		 DBConnection.insertAudiTrail("Ran Test", ("Test ran by " + TestVars.getTestUservar()));
			
			ShowLogsScreen2.addDataLine();
			
		//interrupt();
			}
			
			


		
		
		public long setTimer() {
			Phase hello = phaseVar.getPhase(); 
			
			switch(hello) {
			case FAIL:
				return (long)(TestVars.getBleedTime()*1000);
			case H_FWD:
				return (long)(TestVars.getSliderTime()*1000);
			case V_FWD:
				return (long)(TestVars.getClampTime()*1000);
			case EXHAUST:
				return (long)(TestVars.getBleedTime()*1000);
			case FILL:			 
				return (long)(TestVars.getChargevar()*1000);
			case SETTLE:
				return (long)(TestVars.getSettlevar()*1000);
			case TEST:
				return (long)(TestVars.getTestvar()*1000);
			case VENT:
				return (long)(TestVars.getBleedTime()*1000);
			case V_BACK:
				return (long)(TestVars.getClampTime()*1000);
			case H_BACK:
				return (long)(TestVars.getSliderTime()*1000);
			default:
				break;
		}
			return -1;
		}
		
		public double getTotalAverage(List<Double> num2) {
				
			double avgTotal = 0.0;
			for (int j = 0; j< num2.size(); j++) {
				if ( !(j < 0)) {
				avgTotal= num2.get(j) + avgTotal;
				}
				
			}
			
			double average = avgTotal/num2.size();
			return average;
			
			
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
		
		
	/*	public static void writeToDB() {
			
		    String User = TestVars.getTestUservar(); 
			boolean pass = TestVars.getdidPass();
			String testTime = TestVars.getTestTimeStampvar();
			byte unit;
			if(pass) {
				unit = 1;
			} else { unit = 0;}
			String PASS = "";
			Date dt = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			
			String currentTime = sdf.format(testTime);
			String startTestTime = sdf.format(testTime);
			
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				 String myUrl = "jdbc:mysql://localhost:3306/aes?ServerTimeZone=UTC";

			      Class.forName("com.mysql.jdbc.Driver");
			      Connection conn = DriverManager.getConnection(myUrl, "root", PASS); 
			
				
			
			
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

