package stream18.aescp.view.form.mode;

import java.util.HashMap;

public class Unitsclass {
	
	   private HashMap<String, Double> _units;

	    public Unitsclass()
	    {
	        _units = new HashMap<String, Double>();
	        
	        _units.put("Psig ",     1.0);//------------PRESSURE UNITS (base units=PSI)
	        _units.put("InH2O",  27.681);
	        _units.put("kPa  ",  6.8948);
	        _units.put("mBar ",  68.948);
	        _units.put("Kgcm2",0.070307);
	        _units.put("cmH2O",  70.309);
	        _units.put("mmH2O",   703.9);
	        _units.put("InHg ",   2.036);
	        _units.put("mmHg ",  51.715);
	        _units.put("Bar  ", 0.06805);

	        _units.put("CCM  ",     1.00);//-------------- HI-FLOW UNITS(base units=CCM)
	        _units.put("LPM  ",     1.00);
	        _units.put("CFM  ", 3.53e-5);
	        _units.put("CFH  ", 2.12e-3);
	        _units.put("GPM  ", 2.64e-4);
	        _units.put("CIM  ",   0.061);

 
	       
	    }
	 
 
}
