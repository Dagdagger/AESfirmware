package stream18.aescp.model;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;

import stream18.aescp.controller.TestMode;
import stream18.aescp.controller.TestMode.Mode;
import stream18.aescp.controller.TestPhase.Phase;
import stream18.aescp.controller.TestVars;
import stream18.aescp.controller.phaser.TestPhaser;
import stream18.aescp.view.screen.logs.ShowLogsScreen2;
 /**
 * 
 * @author egarcia
 *
 * This class will hold initial configuration data,
 * that will be overridden by the data set on the Mode forms
 *
 */
public class TestModeCfgManager {	
	
	
	
			static double chargeTime = 0.0;
			static double settleTime = 0.0;
			static double testTime = 0.0;
			static double clampTime = 0.0;
			static double bleedTime = 0.0;
			static double sliderTime = 0.0;
			
			public static void setTimers() {
		        chargeTime = TestVars.getChargevar();
				settleTime = TestVars.getSettlevar();
				testTime = TestVars.getTestvar();
				clampTime = TestVars.getClampTime();
				bleedTime = TestVars.getBleedTime();
				sliderTime = TestVars.getSliderTime();
				}
			
	
	static Hashtable<Mode, TestModeCfg> testModeCfgs = new Hashtable<Mode,TestModeCfg>();
	static {				
		boolean cleanValves[] = {false, false, false, false, false,false, false, false, false, false};
		boolean emptyValves[] = {false, false, false, false, false,false, false, false, false, false};
		
		TestModeCfg testModeVacuumChamber = new TestModeCfg(Mode.VACUUMCHAMBER);
		
		
		setTimers();
		
		
		
		
		/* The valves in the array are as follows:
		 * Clamp = 0
		 * Slider = 1
		 * Ev8"Not set yet" = 2
		 * "Reference" = 3
		 * Ev6 "Not set yet" = 4
		 * Green Light = 5
		 * Red Light = 6
		 * Fill = 7
		 * Vacuum = 8
		 * Bleed = 9
		 */
		
		
		
		//--Start of Vacuum Chamber Test		
		/* Start off with setting off and turning on the slider
		 * slider is set with index 1 and clamp is set with index 0
		 * in the valve array. Reference and Vacuum are also turned on
		   */

		System.out.println("Starting Cycle");
		PhaseCfg phaseH_FWD2 = new PhaseCfg(Phase.H_FWD, testModeVacuumChamber);
		phaseH_FWD2.setValves(cleanValves);
		phaseH_FWD2.setValve(1, true);
		phaseH_FWD2.setValve(8, true);
		phaseH_FWD2.setValve(3, true);
		phaseH_FWD2.setPhaseTime(sliderTime*1000);
		cleanValves = phaseH_FWD2.getValves();
		phaseH_FWD2.setNextPhase(Phase.V_FWD);
		
		/* This Phase activates the clamp and then exhausts to atmosphere a little*/
		
		PhaseCfg phaseV_FWD2 = new PhaseCfg(Phase.V_FWD, testModeVacuumChamber);
		phaseV_FWD2.setValves(cleanValves);
		phaseV_FWD2.setValve(0, true);
		phaseV_FWD2.setPhaseTime(clampTime*1000);		
		cleanValves = phaseV_FWD2.getValves();

		phaseV_FWD2.setNextPhase(Phase.EXHAUST);
		
		
		/* Vent to atmosphere for a little bit after clamp sequence */
		
		PhaseCfg phaseV_BLEED1 = new PhaseCfg(Phase.EXHAUST, testModeVacuumChamber);
		phaseV_BLEED1.setValves(cleanValves);
		phaseV_BLEED1.setValve(9, true);
		phaseV_BLEED1.setPhaseTime(bleedTime*1000);		
		phaseV_BLEED1.enableADC(3);
		cleanValves = phaseV_BLEED1.getValves();
		phaseV_BLEED1.setNextPhase(Phase.FILL);
		
		
		/* Set off valves for fill and vacuum until desired pressure */
		/* make sure to measure during fill  Turn off reference Chamber
		 * and dump it into the Reservoir */
		PhaseCfg phaseFILL2 = new PhaseCfg(Phase.FILL, testModeVacuumChamber);
		phaseFILL2.setValves(cleanValves);
		phaseFILL2.setValve(9, false);
		phaseFILL2.setValve(8, false); //
		phaseFILL2.setValve(7, true); // fill on
		phaseFILL2.setValve(3, false);
		phaseFILL2.setPhaseTime(chargeTime*1000);
		phaseFILL2.enableADC(3);
		cleanValves = phaseFILL2.getValves();

		phaseFILL2.setNextPhase(Phase.SETTLE);
		
		
		/* This Phase you just measure from the ADCs, at the same time fill chamber valve */

		/* CHAMBER AND VACUUM */
		
		PhaseCfg phaseSETTLE2 = new PhaseCfg(Phase.SETTLE, testModeVacuumChamber);
		phaseSETTLE2.setValves(cleanValves);
		phaseSETTLE2.setValve(7, false);
		phaseSETTLE2.setValve(8, true);
		//phaseSETTLE2.setValve(3, true);
		phaseSETTLE2.setPhaseTime(settleTime*1000);
		phaseSETTLE2.enableADC(3);
		cleanValves = phaseSETTLE2.getValves();

		phaseSETTLE2.setNextPhase(Phase.TEST);
		
		
		/* Also measure from the ADC but this time apply Test parameters */
		
		PhaseCfg phaseTEST2 = new PhaseCfg(Phase.TEST, testModeVacuumChamber);
		phaseTEST2.setValves(cleanValves);
		phaseTEST2.setValve(3, false);
		phaseTEST2.setADC(0, false);
		phaseTEST2.setADC(1, false);
		phaseTEST2.setADC(2, false);
		phaseTEST2.enableADC(3);
		phaseTEST2.setPhaseTime(testTime*1000);
	    cleanValves = phaseTEST2.getValves();

	    phaseTEST2.setNextPhase(Phase.VENT);

	    
	    
	    /* Now we need to vent to atmosphere a second time */
		
		PhaseCfg phaseVENT2 = new PhaseCfg(Phase.VENT, testModeVacuumChamber);
		phaseVENT2.setValves(cleanValves);
		phaseVENT2.setValve(9, true);
		phaseVENT2.setValve(8, false);
		phaseVENT2.enableADC(3);
		phaseVENT2.setPhaseTime(bleedTime*1000);
		cleanValves = phaseVENT2.getValves();
		phaseVENT2.setNextPhase(Phase.V_BACK);
		
		/* Release the Clamp */

		PhaseCfg phaseV_BACK2 = new PhaseCfg(Phase.V_BACK, testModeVacuumChamber);
		phaseV_BACK2.setValves(cleanValves);
		phaseV_BACK2.setValve(9,  false);
		phaseV_BACK2.setValve(0, false);
		phaseV_BACK2.setPhaseTime(5000);
		cleanValves = phaseV_BACK2.getValves();
		phaseV_BACK2.setNextPhase(Phase.H_BACK);
		
		/* Release the Slider */

		PhaseCfg phaseH_BACK2 = new PhaseCfg(Phase.H_BACK, testModeVacuumChamber);
		//phaseH_BACK2.setValves(cleanValves);
		phaseH_BACK2.setValves(emptyValves);
		phaseH_BACK2.setValve(1, false);
		phaseH_BACK2.setValve(9, true);
		phaseH_BACK2.setPhaseTime(2000);
		phaseH_BACK2.setNextPhase(Phase.RESULTS);	
		
PhaseCfg phase_FAIL = new PhaseCfg(Phase.FAIL, testModeVacuumChamber);
		 //phase_FAIL.setValves(emptyValves);
		phase_FAIL.setValve(1, true);
		 phase_FAIL.setValve(9, true);
		 phase_FAIL.setValve(0, true);
		 phase_FAIL.enableADC(3);
		 phase_FAIL.setPhaseTime(bleedTime*1000);		
		 phase_FAIL.setNextPhase(Phase.V_BACK);
		 
		 PhaseCfg phase_RESULTS = new PhaseCfg(Phase.RESULTS, testModeVacuumChamber);
		 phase_RESULTS.setValves(emptyValves);
		 phase_RESULTS.setPhaseTime(1000);		
		 phase_RESULTS.disableADCs();
		 phase_RESULTS.setNextPhase(Phase.FINISHED);
		 
		 
		 PhaseCfg phase_STOPPED = new PhaseCfg(Phase.STOPPED, testModeVacuumChamber);
		 //phase_FAIL.setValves(emptyValves);
		phase_STOPPED.setValve(1, true);
		 phase_STOPPED.setValve(9, true);
		 phase_STOPPED.setValve(0, true);
		 phase_STOPPED.setPhaseTime(bleedTime*1000);		
		 phase_STOPPED.disableADCs();
		 phase_STOPPED.setNextPhase(Phase.STOP_BACK);
		 
		 PhaseCfg phase_STOPBACK = new PhaseCfg(Phase.STOP_BACK, testModeVacuumChamber);
		 //phase_FAIL.setValves(emptyValves);
		 phase_STOPBACK.setValves(cleanValves);
		 phase_STOPBACK.setValve(9,  false);
		 phase_STOPBACK.setValve(0, false);
		 phase_STOPBACK.setPhaseTime(5000);
			cleanValves = phase_STOPBACK.getValves();
			phase_STOPBACK.setNextPhase(Phase.STOP_HBACK);
			
			 PhaseCfg phase_STOPHBACK = new PhaseCfg(Phase.STOP_HBACK, testModeVacuumChamber);
			 //phase_FAIL.setValves(emptyValves);
			 phase_STOPHBACK.setValves(cleanValves);
			 phase_STOPHBACK.setValve(1,  false);
			 phase_STOPHBACK.setValve(9, true);
			 phase_STOPHBACK.setPhaseTime(2000);
			 cleanValves = phase_STOPHBACK.getValves();
			 phase_STOPHBACK.setNextPhase(Phase.FINISHED);


		 
		
		
		testModeCfgs.put(testModeVacuumChamber.getMode(), testModeVacuumChamber);	
			
			String timeStamp = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").format(Calendar.getInstance().getTime());
			TestVars.setTestTimeStampvar(timeStamp);
			TestVars.setTestModevar(testModeVacuumChamber.getMode().toString());
			sendTestData();
	 
	}
	
	
	
	
	

	public static TestModeCfg find(Mode mode) {
		return testModeCfgs.get(mode);
	}
	
	public void addTestModeCfg(TestModeCfg testModeCfg) {
		testModeCfgs.put(testModeCfg.getMode(), testModeCfg);
	}
	static public void sendTestData() {
		TestModeCfg testModeVacuumChamber = new TestModeCfg(Mode.VACUUMCHAMBER);
		PhaseCfg phaseH_FWD2 = new PhaseCfg(Phase.H_FWD, testModeVacuumChamber);
		phaseH_FWD2.setNextPhase(Phase.V_FWD);
		System.out.println("User: "+ TestVars.getTestUservar() );
		System.out.println("Fill Timer: " + TestVars.getChargevar());
		System.out.println("Settle Timer: " + TestVars.getSettlevar());
		System.out.println("Settle Timer: " + TestVars.getTestvar());
		System.out.println("Mode: " + TestVars.getTestModevar());
		System.out.println("Time Stamp: "+ TestVars.getTestTimeStampvar());		
	}
}
