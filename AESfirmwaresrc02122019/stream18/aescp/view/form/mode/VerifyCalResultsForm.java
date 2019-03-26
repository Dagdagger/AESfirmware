package stream18.aescp.view.form.mode;

import java.awt.Rectangle;

import javax.swing.JTextField;

import stream18.aescp.view.form.Form;
import stream18.aescp.view.screen.Screen;

public class VerifyCalResultsForm extends ResultsForm {
	private static final long serialVersionUID = 1L;
	private static final int X_LEFT = 10;
	private static final int Y_TOP = 20;

	private JTextField pressure0PCTF;
	private JTextField pressure25PCTF;
	private JTextField pressure50PCTF;
	private JTextField pressure75PCTF;
	private JTextField pressure100PCTF;
	
	protected static ResultsForm theVerifyCalResultsForm;
	
	private static String resultValueText = "Flow Rate:";
	
	protected VerifyCalResultsForm(Screen parentScreen) {
		super(parentScreen);

		// Translate the resultField to the right
		Rectangle rfBounds = resultField.getBounds();
		rfBounds.translate(120, 0);
		resultField.setBounds(rfBounds);
				
		// Now add the remaining fields
		int x = X_LEFT, y = Y_TOP;

//    	FontMetrics fm = this.getFontMetrics(Browser.FONT);
//    	int th = fm.getAscent();
//	    JLabel l = new JLabel("Pressure sensor:", JLabel.TRAILING);
//	    l.setBounds(new Rectangle(x, y, 300, (int) (th*1.4)));
//	    l.setFont(Browser.FONT);
//	    add(l);

    	x = X_LEFT;
    	y += 40;
	    
    	x = X_LEFT;
    	y += 40;
    	pressure0PCTF = createTextFieldWithUnits("0%: ", x, y, 150, 6, true, "mBar");
    	pressure0PCTF.setText("0.0000");
    	pressure0PCTF.setEditable(false);

    	x = X_LEFT;
    	y += 40;
    	pressure25PCTF = createTextFieldWithUnits("25%: ", x, y, 150, 6, true, "mBar");
    	pressure25PCTF.setText("0.0000");
    	pressure25PCTF.setEditable(false);

    	x = X_LEFT;
    	y += 40;
    	pressure50PCTF = createTextFieldWithUnits("50%: ", x, y, 150, 6, true,"mBar");
    	pressure50PCTF.setText("0.0000");
    	pressure50PCTF.setEditable(false);

    	x = X_LEFT;
    	y += 40;
    	pressure75PCTF = createTextFieldWithUnits("75%: ", x, y, 150, 6, true, "mBar");
    	pressure75PCTF.setText("0.0000");
    	pressure75PCTF.setEditable(false);

    	x = X_LEFT;
    	y += 40;
    	pressure100PCTF = createTextFieldWithUnits("100%: ", x, y, 150, 6, true, "psi");
    	pressure100PCTF.setText("0.0000");
    	pressure100PCTF.setEditable(false);

	}

	@Override
	protected String getResultValueText() {
		return resultValueText;
	}
	
	@Override
	protected String getResultValueUnits() {
		return "";
	}
	
	public static Form getInstance(Screen parentScreen) {
		if (theVerifyCalResultsForm == null) {
			theVerifyCalResultsForm = new VerifyCalResultsForm(parentScreen);
		}
		
		return theVerifyCalResultsForm;
	}

}
