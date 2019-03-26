package stream18.aescp.view.button;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import stream18.aescp.view.screen.mode.FlowScreen;
import stream18.aescp.view.screen.mode.VacuumChamberScreen;

public class ReadyScreenButton extends Button {
	private static final long serialVersionUID = 1L;
 
		// Singleton
		static ReadyScreenButton theReadyScrenScreenbtn = null;
	//	static FlowButton btn = null;
		private static String testMode ="VacuumChamber";
	 
	public static String getTestMode() {
			return testMode;
		}

		public static void setTestMode(String testMode) {
			ReadyScreenButton.testMode = testMode;
		}

	public ReadyScreenButton(int x, int y) {
		super(BOTTOM_TYPE);
		 moveTo(x, y, 1);
		config("resources/Mode.png", "Main", new Color(0xFFB510));
	 			
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				
			switch(getTestMode().toString())
				{	   
			 case "Flow" :
				  FlowScreen.getInstance().setActive(null);
				 
				  break;
			  case "VacuumChamber" :
				  VacuumChamberScreen.getInstance().setActive(null);
				 
				  break;
			   
			}
			
//
			}
		});
	}
	
	public static ReadyScreenButton getInstance(int x, int y) {
		if (theReadyScrenScreenbtn == null) {
			theReadyScrenScreenbtn = new ReadyScreenButton(x, y);
		}
		return theReadyScrenScreenbtn;
	}
 
 
 
}
