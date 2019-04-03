package stream18.aescp.model;

import java.io.Serializable;

import stream18.aescp.controller.TestVars;

public class tempTestVars implements Serializable {
	private static final long serialVersionUID = 1L;
	double  chargevar;
	double  settlevar;
	double  testvar;
	double  pressureVar;
	double  TemperatureVar;
	double  pressureToleranceVar;
	
	double bleedTime;
	double sliderTime;
	double clampTime; 
	double programNumber;
	
	String  testUservar;
	String  testTimeStampvar;
	String  testModevar;
	int     testCountervar;
	double  testDecayvar;
	private Double minPressureDrop;
	private Double maxPressureDrop;
	
	
	
	public double getProgramNumber() {
		return this.programNumber;
	}
	
	public void setProgramNumber(Double double1) {
		this.programNumber = double1;
	}
	public double getBleedTime() {
		return this.bleedTime;
	}


	public void setBleedTime(double bleedTime) {
		this.bleedTime = bleedTime;
	}


	public double getSliderTime() {
		return this.sliderTime;
	}


	public void setSliderTime(double sliderTime) {
		this.sliderTime = sliderTime;
	}


	public double getClampTime() {
		return this.clampTime;
	}


	public void setClampTime(double clampTime) {
		this.clampTime = clampTime;
	}


	public double getpressureToleranceVar() {
		return this.pressureToleranceVar;
	}
	
	
	public void setpressureToleranceVar(double pressureToleranceVar) {
		this.pressureToleranceVar = pressureToleranceVar;
	}
		

public double getTemperatureVar() {
		return this.TemperatureVar;
	}
	
	
	public void setTemperatureVar(double TemperatureVar) {
		this.TemperatureVar = TemperatureVar;
	}
	
	
	public double getpressureVar() {
		return this.pressureVar;
	}
	
	public void setpressureVar(double pressureVar) {
		this.pressureVar = pressureVar;
	}
 

	public double getTestDecayvar() {
		return this.testDecayvar;
	}


	public void setTestDecayvar(double testDecayvar) {
		this.testDecayvar = testDecayvar;
	}


	public String getTestModevar() {
		return this.testModevar;
	}


	public void setTestModevar(String mode) {
		this.testModevar = mode;
	}

	public String getTestTimeStampvar() {
		return this.testTimeStampvar;
	}

	public void setTestTimeStampvar(String testTimeStampvar) {
		this.testTimeStampvar = testTimeStampvar;	
	}


	public String getTestUservar() {
		return this.testUservar;
	}


	public void setTestUservar(String testUservar) {
		this.testUservar = testUservar;
	}

	static int  testcountervar;

	public double getChargevar() {
		return this.chargevar;
	}


	public void setChargevar(double chargevar) {
		this.chargevar = chargevar;
	}


	public double getSettlevar() {
		return this.settlevar;
	}


	public void setSettlevar(double settlevar) {
		this.settlevar = settlevar;
	}


	public double getTestvar() {
		return this.testvar;
	}


	public void setTestvar(double testvar) {
		this.testvar = testvar;
	}

	public void setDecay(double valueOf) {
			this.testDecayvar = valueOf;
	}

	public double getDecayVar() {
		return this.testDecayvar;
	}

	public void setminPressureDrop(Double minPressureDrop) {
		this.minPressureDrop = minPressureDrop;
	}


	public void setmaxPressureDrop(Double maxPressureDrop) {
		this.maxPressureDrop = maxPressureDrop;
		
	
		
	}
	public double getminPressureDrop(){
		return this.minPressureDrop;
	}


	public double getmaxPressureDrop() {
		return this.maxPressureDrop;
		
	
		
	}

 
 
			
		

 
}
