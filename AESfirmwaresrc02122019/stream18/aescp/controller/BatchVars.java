package stream18.aescp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.math.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class BatchVars {
	protected static BatchVars theBatchVars;
	public static ArrayList<Double> Pressures = new ArrayList<Double>();
	static int passes = 0;
	static int failures = 0;
	static String BatchVarstestTime = "";
	static double averagePressures = 0.0;
	static String BatchName = "";
	static int numofTests = 0;
	
	public static void setStartTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		BatchVarstestTime = (dateFormat.format(date));
	}
	
	public static String getStartTime() {
		return BatchVarstestTime;
	}
	public static void addOneTest() {
		numofTests += 1;
	}
	
	public static void addOnePass() {
		passes += 1;
	}
	
	public static void addOneFail() {
		failures += 1;
	}

	public static int getNumofTests() {
		return numofTests;
	}
	public static void setNumofTests(int numofTests) {
		BatchVars.numofTests = numofTests;
	}
	public static ArrayList<Double> getPressures() {
		return Pressures;
	}
	public static void setPressures(ArrayList<Double> pressures) {
		Pressures = pressures;
	}
	public static int getPasses() {
		return passes;
	}
	public static void setPasses(int passes) {
		BatchVars.passes = passes;
	}
	public static int getFailures() {
		return failures;
	}
	public static void setFailures(int failures) {
		BatchVars.failures = failures;
	}
	public static double getAveragePressures() {
		return getAverage();
	}
	public static void setAveragePressures(int averagePressures) {
		BatchVars.averagePressures = averagePressures;
	}
	
	public static Double getAverage() {
        int size = Pressures.size();
        Double total = 0.0;
        if(size > 0){
        	for (int i = 0; i < size; i++) {
        		total += Pressures.get(i);
        	}
        }
        Double average = total/size;

		return average;
		
	}
	
	public static void setBatchName(String batchname) {
		BatchVars.BatchName = batchname;
		
	}
	
	public static String getBatchName() {
		return BatchName;
	}
	
	public static void resetValues() {
		  passes = 0;
		  failures = 0;
		  numofTests = 0;
		  averagePressures = 0.0;
		  Pressures.clear();
		  
		  
				
	}
	
	public static double getStandardDeviation() {
		double mean = getAverage();
		double temp = 0.0;
		if(Pressures.size() > 0) {
			for( int i = 0; i < Pressures.size(); i++) {
				Double val = Pressures.get(i);
				double squareDiffToMean = Math.pow(val - mean,  2);
				temp += squareDiffToMean;
			}
		}	
			
			double meanOfDiffs = (double) temp / (double) Pressures.size();
			
			return (double)Math.sqrt(meanOfDiffs);
	
	}
	
	public static double getRange() {
		double lastValue = 0.0;
		double firstValue = 0.0;
		double range = 0.0;
		if(Pressures.size() > 1) {
			lastValue = Pressures.get(Pressures.size()-1);
			firstValue = Pressures.get(0);
			range = lastValue - firstValue;
		}
		return range;
		
		
	}

}
