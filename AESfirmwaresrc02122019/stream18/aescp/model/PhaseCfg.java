package stream18.aescp.model;

import stream18.aescp.controller.TestPhase.Phase;
import stream18.aescp.drivers.ADCDriver;
 

//Defines the configuration parameters for a specific Phase
public class PhaseCfg {
	Phase phaseId;
	TestModeCfg parent;	 
	
	// 10 valves
	boolean valves[] = new boolean[10];
	// 4 ADCs
	boolean adcs[] = new boolean[4];
	// Time in ms
	double phaseTime;
	// Next Phase
	Phase nextPhase = Phase.FINISHED;
	
	public PhaseCfg(Phase phaseId, TestModeCfg parent) {
		this.phaseId = phaseId;
		this.parent = parent;
		parent.addPhaseCfg(this);
	}	
	public void setValves(boolean vals[]) {
		for( int i=0; i<10; i++) {
			valves[i] = vals[i];
		}
	}
	/*Sends the boolean array of valves that are on
	 * or off and makes the python socket program 
	 * generate the correct hex code
	 */
	public void fireValves(boolean vals[]) {
			ADCDriver.sendData(vals);
		}
		
	
	public boolean[] getValves() {
		return valves;
	}
	public void setValve(int i, boolean val) {
		valves[i] = val;
	}
	public void setADC(int n, boolean val) {
		adcs[n] = val;
	}
	public void setNextPhase(Phase next) {
		nextPhase = next;
	}	
	public Phase getPhase() {
		return phaseId;
	}
	public Phase getNextPhase() {
		return nextPhase;
	}
	public void setPhaseTime(double d) {
		this.phaseTime = d;
	}
	public double getPhaseTime() {
		return phaseTime;
	}
	public boolean isADCEnabled(int n) {
		return adcs[n];
	}
	public boolean[] getADCs() {
		return adcs;
	}
	public void enableADC(int i) {
		adcs[i] = true;
		
	}
	
	public void disableADCs() {
		for (int i = 0; i < adcs.length; i++) {
			adcs[i] = false;
		}
	}

	public boolean anyAdcEnabled() {
		
		return (adcs[0] || adcs[1] || adcs[2] || adcs[3]);
	}
}
