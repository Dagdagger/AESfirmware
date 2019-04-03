package stream18.aescp.controller;


public class TestVars {
	protected static TestVars theTestVars;
	static double  chargevar;
	static double  settlevar;
	static double  testvar;
	static double  pressureVar;
	static double  TemperatureVar;
	static double  pressureToleranceVar;
	static double  minDropPercentage;
	static double  maxDropPercentage;
	
	static double bleedTime;
	static double sliderTime;
	static double clampTime; 
	static double programNumber;
	
	static String  testUservar;
	static String  testTimeStampvar;
	static String  testModevar;
	static int     testCountervar;
	static double  testDecayvar;
	
	
	
	public static double getmaxDropPercentage() {
		return maxDropPercentage;
	}


	public static void setmaxDropPercentage(double maxDropPercentage) {
		TestVars.maxDropPercentage= maxDropPercentage;
	}
	
	
	
	public static double getminDropPercentage() {
		return minDropPercentage;
	}


	public static void setminDropPercentage(double minDropPercentage) {
		TestVars.minDropPercentage= minDropPercentage;
	}

	
	public static double getProgramNumber() {
		return programNumber;
	}
	
	public static void setProgramNumber(Double double1) {
		TestVars.programNumber = double1;
	}
public static double getBleedTime() {
		return bleedTime;
	}


	public static void setBleedTime(double bleedTime) {
		TestVars.bleedTime = bleedTime;
	}


	public static double getSliderTime() {
		return sliderTime;
	}


	public static void setSliderTime(double sliderTime) {
		TestVars.sliderTime = sliderTime;
	}


	public static double getClampTime() {
		return clampTime;
	}


	public static void setClampTime(double clampTime) {
		TestVars.clampTime = clampTime;
	}


	public static double getpressureToleranceVar() {
		return pressureToleranceVar;
	}
	
	
	public static void setpressureToleranceVar(double pressureToleranceVar) {
		TestVars.pressureToleranceVar = pressureToleranceVar;
	}
		

public static double getTemperatureVar() {
		return TemperatureVar;
	}
	
	
	public static void setTemperatureVar(double TemperatureVar) {
		TestVars.TemperatureVar = TemperatureVar;
	}
	
	
	public static double getpressureVar() {
		return pressureVar;
	}
	
	public static void setpressureVar(double pressureVar) {
		TestVars.pressureVar = pressureVar;
	}
 

	public static double getTestDecayvar() {
		return testDecayvar;
	}


	public static void setTestDecayvar(double testDecayvar) {
		TestVars.testDecayvar = testDecayvar;
	}


	public static String getTestModevar() {
		return testModevar;
	}


	public static void setTestModevar(String mode) {
		TestVars.testModevar = mode;
	}

	public static String getTestTimeStampvar() {
		return testTimeStampvar;
	}

	public static void setTestTimeStampvar(String testTimeStampvar) {
		TestVars.testTimeStampvar = testTimeStampvar;	
	}


	public static String getTestUservar() {
		return testUservar;
	}


	public static void setTestUservar(String testUservar) {
		TestVars.testUservar = testUservar;
	}

	static int  testcountervar;
	private static Double minPressureDrop;
	private static Double maxPressureDrop;

	public static double getChargevar() {
		return chargevar;
	}


	public static void setChargevar(double chargevar) {
		TestVars.chargevar = chargevar;
	}


	public static double getSettlevar() {
		return settlevar;
	}


	public static void setSettlevar(double settlevar) {
		TestVars.settlevar = settlevar;
	}


	public static double getTestvar() {
		return testvar;
	}


	public static void setTestvar(double testvar) {
		TestVars.testvar = testvar;
	}
	
	public static void setDecay(double decay) {
		TestVars.testDecayvar = decay;
	}
	
	public static void setminPressureDrop(Double minPressureDrop) {
		TestVars.minPressureDrop = minPressureDrop;
	}


	public static void setmaxPressureDrop(Double maxPressureDrop) {
		TestVars.maxPressureDrop = maxPressureDrop;
		
	}

 

 
	
 
	public static TestVars getInstance() {
		if (theTestVars == null) {
			theTestVars = new TestVars();
			
		
		}
		return theTestVars;
	}



 
}
