package stream18.aescp.model;

public class ADCValuesBuffer {

	static final int BUFFER_SIZE=32*1024;
	
	static long timestamps[] = new long[BUFFER_SIZE];
	static double[][] values = new double[BUFFER_SIZE][4];
	
	static int count = 0;
	static long errs = 0;
	
	public static void reset() {
		count = 0;
	}

	public static void store(long ts, double[] adcValues) throws Exception {
		timestamps[count] = ts;
		values[count][0] = adcValues[0];
		values[count][1] = adcValues[1];
		values[count][2] = adcValues[2];
		values[count][3] = adcValues[3];
		if(++count >= BUFFER_SIZE) {
			count = 0;
			errs++;
			throw new Exception();
		}
	}
	
	public static double getVal(int count, int adc) {
		return values[count][adc];
	}

	public static int getCount() {
		return count;
	}
	public static long getErrs() {
		return errs;
	}

	public static void getVals(int n, double[] ret, int limit) {
		int step = count/limit;
//		long range = values[ret.length-1][n] - values[0][n];
		int j = 0;
		for (int i=0; i<limit; i++) {
//			ret[i] = values[j][n] - values[0][n];
			if (n>=0) {
				ret[i] = values[j][n];
			} else {
				ret[i] = i;				
			}
			j += step;
		}
	}
	
	public static void getVals(int n, double[] ret) {
		for (int i=0; i<ret.length; i++) {
			ret[i] = values[i][n];
		}
	}

}
