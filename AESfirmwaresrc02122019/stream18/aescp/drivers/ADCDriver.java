package stream18.aescp.drivers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;


public class ADCDriver {

	static double adcValues[] = new double[4];
	
	public static double[] readADCs(boolean[] adCs) {
		
		// Create a sockets client that will read on port 3000 of localhost	
		try {
Socket s = new Socket("localhost", 3000);
//Socket s = new Socket("192.168.43.202", 3000);
	        BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
	        String response = input.readLine();
	        System.out.println(response);
	        s.close();
	        List<String> items = Arrays.asList(response.split("\\s*,\\s*"));
	        adcValues[0] =  Double.parseDouble(items.get(0));
	        adcValues[1] =  Double.parseDouble(items.get(1));
	        adcValues[2] =  Double.parseDouble(items.get(2));
	        adcValues[3] =  Double.parseDouble(items.get(3));

	        for (int i = 0; i < 4; i++) {
	        	if (Double.toString(adcValues[i]) != null) {
	        	System.out.print(adcValues[i] + ", ");
	        }
	        }
	        System.out.println("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return adcValues;
	}
	
	
	
	/* Send Data is used to take in a valve number as an argument
	 * starting from EvA = [0](AKA Slider Valve). The running
	 * listener will take in the string as command and execute the low
	 * level command
	 */
	
	public static void sendData(boolean[] valve) {
			String hex = "0xF";
			for(int i = 0; i < valve.length-1; i+=2){
				if (valve[i] && valve[i+1]) {
					hex += "A";
				}
				else if (valve[i] && !valve[i+1]) {
					hex += "B";
				}
				else if (!valve[i] && valve[i+1]) {
					hex += "E";
				}
				else if (!valve[i] && !valve[i+1]) {
					
					hex += "0";
				}
					
				}
				
 
			try {
				
				//Socket s = new Socket("192.168.43.202", 90);
		    Socket s = new Socket("localhost", 90);
				String message = (hex);
				
				byte[] hello = message.getBytes();
				OutputStream socketOutputStream = s.getOutputStream();
		        socketOutputStream.write(hello);
		        BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
		        String response = input.readLine();
		        System.out.println(response);
		        s.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		}
	
	


