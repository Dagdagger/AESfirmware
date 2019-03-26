package stream18.aescp.view.button.mode;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.view.button.Button;
import stream18.aescp.view.button.ReadyScreenButton;
// import stream18.aescp.view.screen.ModeScreen;
import stream18.aescp.view.screen.mode.FlowScreen;

public class FlowButton extends Button {
	private static final long serialVersionUID = 1L;
	private static String testMode;
	
public static String getTestMode() {
		return testMode;
	}

	public static void setTestMode(String testMode) {
		FlowButton.testMode = testMode;
		ReadyScreenButton.setTestMode("Flow"); 
	}



	//	protected static ReadyScreenButton theReadyScreenButton=null;
	protected static FlowButton theFlowButton = null;
//	protected static ReadyScreenButton theReadyButton = null;
	//theReadyScreenButton.setTestMode("Flow");
	
	
	public FlowButton(int x, int y) {
		super(BOTTOM_TYPE);
		
		moveTo(x, y, 1);
		config("resources/mode/Flow.png", "Flow", new Color(0xFF6F3B));
		
		addMouseListener(new MouseAdapter() {
			@Override
			
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// Select a new Screen for the browser
				FlowScreen.getInstance().setActive(null);
				//setTestMode("Flow");
				//AnyTestScreen.getInstance().setActive(null);
	 
			//	System.out.println("Current Test is:" + FlowButton.getTestMode().toString());
					
			}
		
			
		});
		
	}
		


	public static FlowButton getInstance(int x, int y) {
		if (theFlowButton == null) {
			theFlowButton = new FlowButton(x, y);		
			}
	
		return theFlowButton;
	}
}
