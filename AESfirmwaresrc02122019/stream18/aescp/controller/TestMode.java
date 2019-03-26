package stream18.aescp.controller;

public class TestMode {
	public enum Mode {
		NONE, FLOW, LEAK, OCCLUSION, VACUUM,PRESSURECHAMBER, VACUUMCHAMBER, CAL
	}
	
	Mode theTestMode;
	
	public TestMode() {
		theTestMode = Mode.NONE;	 
	}
	
	public void setTestMode(Mode newTestMode) {
		this.theTestMode = newTestMode;
	}
	
	public Mode getTestMode() {
		return this.theTestMode;
	}

	public String getTestModeAsText() {
		switch(theTestMode) {
			case NONE:
				return "-";
			case FLOW:
				return "Flow Test";
			case LEAK:
				return "Leak Test";
			case OCCLUSION:
				return "Occlusion test";
		   case VACUUM:
			   	return "Vacuum Test";
			case PRESSURECHAMBER: 
				return "P-Chamber Test";
			case VACUUMCHAMBER:
				return "VChamber";
			case CAL:
				return "Calibration";
		}
		return "?";
	}	
}
