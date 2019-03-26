package stream18.aescp.view.form.mode;


//import javax.swing.JButton;
//import javax.swing.JPanel;
import javax.swing.JTextField;

import stream18.aescp.Browser;
import stream18.aescp.view.button.StartButton;
import stream18.aescp.view.button.StopButton;
import stream18.aescp.view.form.Form;
import stream18.aescp.view.screen.Screen;

//import stream18.aescp.view.button.ReadyScreenButton;
 
//import stream18.aescp.view.button.HomeButton;;

public class FlowForm extends Form {
	private static final long serialVersionUID = 1L;
	
	private JTextField cycleTime;
	private JTextField set;
	private JTextField flow;
 	private StartButton theStartButton;
	private StopButton theStopButton;
	
	private static FlowForm theFlowForm;
	private Screen screen;
	
	
	protected StartButton resulbtn;
	
	
	public FlowForm(Screen parentScreen) {
		super(parentScreen);

		addPlot();
		
		cycleTime = createVTextField("Cycle time:", GRAPH_WIDTH + 10, 10, (int) ((Browser.SCREEN_WIDTH - Screen.LEFT_WIDTH) / 3 * 0.9), false);
		cycleTime.setText("10.0");

		set = createVTextField("Set:", GRAPH_WIDTH + 10, 10 + 80, (int) ((Browser.SCREEN_WIDTH - Screen.LEFT_WIDTH) / 3 * 0.9), false);
		set.setText("0.0");

		flow = createVTextField("Flow:", GRAPH_WIDTH + 10, 10 + 160, (int) ((Browser.SCREEN_WIDTH - Screen.LEFT_WIDTH) / 3 * 0.9), false);
		flow.setText("0.0");
	 
	
	}	

	public static FlowForm getInstance(Screen parentScreen) {
		if (theFlowForm == null) {
			theFlowForm = new FlowForm(parentScreen);
			
		}
		
		return theFlowForm;
	}
}
