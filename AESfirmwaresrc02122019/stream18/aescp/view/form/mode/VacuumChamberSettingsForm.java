package stream18.aescp.view.form.mode;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import stream18.aescp.Browser;
import stream18.aescp.controller.TestVars;
import stream18.aescp.model.Program;
import stream18.aescp.model.tempTestVars;
import stream18.aescp.view.button.SaveParmsButton;
import stream18.aescp.view.form.Form;
import stream18.aescp.view.form.TopForm;
import stream18.aescp.view.screen.Screen;

public class VacuumChamberSettingsForm extends SettingsForm {
	private static final long serialVersionUID = 1L;
	protected static VacuumChamberSettingsForm theVacuumChamberSettingsForm;
 	private static Program theProgram;
	private static JTextField pressureTF;
	private static JTextField toleranceTF;
	private JTextField pressureDropMinTF;
	private static JTextField pressureDropMinPercentageTF;
	private JTextField pressureDropMaxTF;
	private JTextField pressureDropMaxPercentageTF;
	private static JTextField clampTimerTF;
	private static JTextField programNumber;
	private static JTextField bleedTimerTF;
	private static JTextField sliderTimerTF;
	private static JTextField chargeTimeTF;
	private static JTextField settleTimeTF;
	private static JTextField testTimeTF;
	private static JTextField testDecayMin;
	private static JTextField testDecay;
	protected static final int X_LEFT = 10;
	protected static final int Y_TOP = 10; 
	private static TestVars theTestVars;
	
	Font font = new Font("Courier", Font.BOLD,7);
	Border border = BorderFactory.createLineBorder(new Color(0xeeeeee));
	Border Guageborder = BorderFactory.createLineBorder(new Color(0x5D5C57));

	protected VacuumChamberSettingsForm(Screen parentScreen) {
		super(parentScreen);
		tempTestVars lastVars = new tempTestVars();
		
		try {
		    FileInputStream fileIn = new FileInputStream("/tmp/program"+TopForm.progNumber.getText());
		    ObjectInputStream in = new ObjectInputStream(fileIn);
		    lastVars = (tempTestVars) in.readObject();
		    in.close();
		    fileIn.close();
		   } catch (Exception e) {
		    System.err.println("\nError creating chamber settings Object. None exists?\n"
		      + e.getMessage() + e.getClass());
		   }
		  
	 
		
		int x = X_LEFT, y = Y_TOP;
		x = X_LEFT;
		
		//y = ;
		//bleedTimerTF = createTextFieldWithUnits("BldV: ", x, y, 10, 10, true, "sec");
		//y = 240;
		//sliderTimerTF = createTextFieldWithUnits("SldV: ", x, y, 10, 10, true, "sec");
		
		
    	pressureTF = createTextFieldWithUnits("Pressure: ", x, y, 150, 10, true, "mBar");
    	if ( lastVars == null) {
    	  pressureTF.setText("10");
    	} else {
    		pressureTF.setText(Double.toString(lastVars.getpressureVar()));
    	}
    	 	
    	x = 260;
    	toleranceTF = createTextFieldWithUnits("Tol: ", x, y, x, 6, true,"%");
    	if ( lastVars == null) {
      	  toleranceTF.setText("10");
      	} else {
      		toleranceTF.setText(Double.toString(lastVars.getpressureToleranceVar()));
      	}
      	 	
    	x = X_LEFT;
    	y += 20;    	
    	pressureDropMinTF = createTextFieldWithUnits("Min Drop: ", x, y, 150, 10, true, "mBar");
    	pressureDropMinTF.setText("0");
    	x = 260;    	
    	pressureDropMinPercentageTF = createTextFieldWithUnits("Min Drop: ", x, y, x, 6, true, "%");
    	pressureDropMinPercentageTF.setText("0");
    	x = X_LEFT;
    	y += 20;
    	pressureDropMaxTF = createTextFieldWithUnits("Max Drop: ", x, y, 150, 10, true, "mBar");
    	pressureDropMaxTF.setText("0"); 		   	
    	x = 260;    	
    	pressureDropMaxPercentageTF = createTextFieldWithUnits("Max Drop: ", x, y, x, 6, true, "%");
    	pressureDropMaxPercentageTF.setText("0");   
    	x = X_LEFT;
    	y += 20;    	
    	
    	
    	chargeTimeTF = createTextFieldWithUnits("Fill: ", x, y, 150, 10, true, "Sec");
    	if ( lastVars == null) {
        	  chargeTimeTF.setText("0.0");
        	} else {
        		chargeTimeTF.setText(Double.toString(lastVars.getChargevar()));
        	}
    	
    	
    	
    	x = X_LEFT;
    	y += 20;	

    	settleTimeTF = createTextFieldWithUnits("Settle: ", x, y, 150, 10, true, "Sec");
    	if ( lastVars == null) {
      	  settleTimeTF.setText("0.0");
      	} else {
      		settleTimeTF.setText(Double.toString(lastVars.getSettlevar()));
      	}
    	
    	
    	
    	
    	x = X_LEFT;
    	y += 20;
    	testTimeTF = createTextFieldWithUnits("Test: ", x, y, 150, 10, true, "Sec");
    	if ( lastVars == null) {
        	  testTimeTF.setText("0.0");
        	} else {
        		testTimeTF.setText(Double.toString(lastVars.getTestvar()));
        	}
      	
    	
    	
    	
    	
    	
    	
    	x = X_LEFT;
    	y += 20;
    	testDecay = createTextFieldWithUnits("Decay: ", x, y, 150, 10, true, "mBar");
    	if ( lastVars == null) {
      	  testDecay.setText("0.0");
      	} else {
      		testDecay.setText(Double.toString(lastVars.getDecayVar()));
      	} 
    	y += 20;
		clampTimerTF = createTextFieldWithUnits("Clamp Valve: ", x, y, 150, 10, true, "sec");
		if ( lastVars == null) {
      	  clampTimerTF.setText("0.0");
      	} else {
      		clampTimerTF.setText(Double.toString(lastVars.getClampTime()));
      	}
    	
		
		
		
		
		y += 20;
		bleedTimerTF = createTextFieldWithUnits("Bleed Valve: ", x, y, 150, 10, true, "sec");
		if ( lastVars == null) {
	      	  bleedTimerTF.setText("0.0");
	      	} else {
	      	bleedTimerTF.setText(Double.toString(lastVars.getBleedTime()));
	      	}
		
		y += 20;
		sliderTimerTF = createTextFieldWithUnits("Slider Valve: ", x, y, 150, 10, true, "sec");
		if ( lastVars == null) {
	      	  sliderTimerTF.setText("0.0");
	      	} else {
	      		sliderTimerTF.setText(Double.toString(lastVars.getClampTime()));
	      	}
		y += 20;
    	
    
    	//testDecayMin = createTextFieldWithUnits("Decay Min: ", x, y, 150, 10, true, "PSI");
    	//testDecayMin.setText("");
/* *********************************************************************************************************************
 * *********************************************************************************************************************
 * 
 * SAVE BUTTON THAT UPDATES THE TEST PARAMETERS, EVERYTHING BEING SAVED SHOULD BE HERE
 * CHECK THE UPDATE TEST PARAMETERS METHOD 
 * 
 *
 ***********************************************************************************************************************/
    	x = 260;
    	SaveParmsButton saveBTN = new SaveParmsButton(350 + 100, 150);
    	add(saveBTN);
    	
    	//TestVars.setChargevar(Integer.parseInt(chargeTimeTF.getText()));
    	//Test.getInstance().setTestFillTimer(Integer.parseInt(chargeTimeTF.getText()));
    	
    //	updateTestvars(chargeTimeTF.getText(),settleTimeTF.getText(),testTimeTF.getText());	 	
 		
		
    	saveBTN.addMouseListener(new MouseAdapter() {
    		//	@Override
    		 	public void mouseReleased(MouseEvent e) {
    		 		super.mouseReleased(e);
    		 		  		
    		 		pressureDropMinPercentageTF.setText(CalculateDropPercentage(pressureDropMinTF.getText(),pressureTF.getText()));
    		 		pressureDropMaxPercentageTF.setText(CalculateDropPercentage(pressureDropMaxTF.getText(),pressureTF.getText()));
    		 		 
    		 		updateTestTimers();
    		 		
    		 		int reply = JOptionPane.showConfirmDialog(null, "Would you like to save this configuration?", "Save?", JOptionPane.YES_NO_OPTION);
    		        if (reply == JOptionPane.YES_OPTION) {
    		        	updateTestTimers();
    		        	System.out.println("Saved");
    		          JOptionPane.showMessageDialog(null, "Saved!");
    		        }
    		        else {
    		           JOptionPane.showMessageDialog(null, "Not Saved!");
   
    		        }
    		 		//CurrentTest.setFillTimer(chargeTimeTF.getText());
    		 	}	
    		 });
 
	}
	
	
	public static void updateTestTimers() {
 
		//chargeTimeTF.setText(String.valueOf(TestVars.getChargevar()));
		
		chargeTimeTF.setText(chargeTimeTF.getText());
		settleTimeTF.setText(settleTimeTF.getText());
		testTimeTF.setText(testTimeTF.getText());
		pressureTF.setText(pressureTF.getText());
		toleranceTF.setText(toleranceTF.getText());
		bleedTimerTF.setText(bleedTimerTF.getText());
		clampTimerTF.setText(clampTimerTF.getText());
		sliderTimerTF.setText(sliderTimerTF.getText());
		testDecay.setText(testDecay.getText());
		pressureTF.setText(pressureTF.getText());
		
		
		TestVars.setpressureVar(Double.valueOf(pressureTF.getText()));
		TestVars.setDecay(Double.valueOf(testDecay.getText()));
		TestVars.setChargevar(Double.valueOf(chargeTimeTF.getText()));
		TestVars.setSettlevar(Double.valueOf(settleTimeTF.getText()));
		TestVars.setTestvar(Double.valueOf(testTimeTF.getText()));
		TestVars.setpressureVar(Double.valueOf(pressureTF.getText()));
		TestVars.setpressureToleranceVar(Double.valueOf(toleranceTF.getText()));
		TestVars.setBleedTime(Double.valueOf(bleedTimerTF.getText()));
		TestVars.setClampTime(Double.valueOf(clampTimerTF.getText()));
		TestVars.setSliderTime(Double.valueOf(sliderTimerTF.getText()));
		TestVars.setProgramNumber(Double.valueOf(TopForm.progNumber.getText()));
		TestVars.setminDropPercentage(Double.valueOf(pressureDropMinPercentageTF.getText()));
		
		
		tempTestVars e = new tempTestVars();
		
		e.setChargevar(Double.valueOf(chargeTimeTF.getText()));
		e.setSettlevar(Double.valueOf(settleTimeTF.getText()));
		e.setTestvar(Double.valueOf(testTimeTF.getText()));
		e.setpressureVar(Double.valueOf(pressureTF.getText()));
		e.setpressureToleranceVar(Double.valueOf(toleranceTF.getText()));
		e.setBleedTime(Double.valueOf(bleedTimerTF.getText()));
		e.setClampTime(Double.valueOf(clampTimerTF.getText()));
		e.setSliderTime(Double.valueOf(sliderTimerTF.getText()));
		e.setProgramNumber(Double.valueOf(TopForm.progNumber.getText()));
	    e.setDecay(Double.valueOf(testDecay.getText()));	
		
		 try {
	         FileOutputStream fileOut =
	         new FileOutputStream("/tmp/program"+TopForm.progNumber.getText());
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         
	         out.writeObject(e);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in /tmp/lastProgram.ser");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
		
		
		
	}
	

	public static String CalculateDropPercentage(String pstr, String specifiedPre) {
		double minpercentage;	 		
	   	double convertedtofloatdrop = Double.parseDouble(pstr);
	   	double convertedtofloatpres = Double.parseDouble(specifiedPre);  	
		minpercentage = (double) ((convertedtofloatdrop*100)/convertedtofloatpres);
		String result = String.valueOf(minpercentage);
		return result;
	}
	
	public static String CalculateDrop(String pstr, String specifiedPre) {
		double minpercentage;	 		
		double convertedtofloatdrop = Double.parseDouble(pstr);
		double convertedtofloatpres = Double.parseDouble(specifiedPre); 	
		minpercentage = (double) ((convertedtofloatdrop*convertedtofloatpres)/100);
		String result = String.valueOf(minpercentage);
		return result;			 
		}
				
		
public static Form getInstance(Screen parentScreen) {
					if (theVacuumChamberSettingsForm == null) {
						theVacuumChamberSettingsForm = new VacuumChamberSettingsForm(parentScreen);
					}
					updateTestTimers();
    		 		
					
					return theVacuumChamberSettingsForm;
				}
				
			
}

















/*public static int strToInt( String str ){
    int i = 0;
    int num = 0;
    boolean isNeg = false;
    //Check for negative sign; if it's there, set the isNeg flag
    if (str.charAt(0) == '-') {
        isNeg = true;
        i = 1;
    }
    //Process each character of the string;
    while( i < str.length()) {
        num *= 10;
        num += str.charAt(i++) - '0'; //Minus the ASCII code of '0' to get the value of the charAt(i++).
    }

    if (isNeg)
        num = -num;
    return num;
}
*/