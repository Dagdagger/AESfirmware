package stream18.aescp.view.form.mode;

import java.awt.FontMetrics;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JTextField;

import stream18.aescp.Browser;
import stream18.aescp.view.form.Form;
import stream18.aescp.view.screen.Screen;

public class VerifyCalSettingsForm extends SettingsForm {
	private static final long serialVersionUID = 1L;
	protected static SettingsForm theVerifyCalSettingsForm;
	private JTextField pressure0PCTF;
	private JTextField pressure25PCTF;
	private JTextField pressure50PCTF;
	private JTextField pressure75PCTF;
	private JTextField pressure100PCTF;
	
	protected VerifyCalSettingsForm(Screen parentScreen) {
		super(parentScreen);
		
		// TODO: populate specific fields
		// Also, take into account data holders
		
		int x = X_LEFT, y = Y_TOP;
		
    	FontMetrics fm = this.getFontMetrics(Browser.FONT);
    	int th = fm.getAscent();
	    JLabel l = new JLabel("Pressure sensor:", JLabel.TRAILING);
	    l.setBounds(new Rectangle(x, y, 300, (int) (th*1.4)));
	    l.setFont(Browser.FONT);
	    add(l);

    	x = X_LEFT;
    	y += 40;
    	pressure0PCTF = createTextFieldWithUnits("0%: ", x, y, 150, 6, true, "mBar");
    	pressure0PCTF.setText("0.0000");

    	x = X_LEFT;
    	y += 40;
    	pressure25PCTF = createTextFieldWithUnits("25%: ", x, y, 150, 6, true, "mBar");
    	pressure25PCTF.setText("0.0000");

    	x = X_LEFT;
    	y += 40;
    	pressure50PCTF = createTextFieldWithUnits("50%: ", x, y, 150, 6, true, "mBar");
    	pressure50PCTF.setText("0.0000");

    	x = X_LEFT;
    	y += 40;
    	pressure75PCTF = createTextFieldWithUnits("75%: ", x, y, 150, 6, true, "mBar");
    	pressure75PCTF.setText("0.0000");

    	x = X_LEFT;
    	y += 40;
    	pressure100PCTF = createTextFieldWithUnits("100%: ", x, y, 150, 6, true, "mBar");
    	pressure100PCTF.setText("0.0000");
	}
	
	public static Form getInstance(Screen parentScreen) {
		if (theVerifyCalSettingsForm == null) {
			theVerifyCalSettingsForm = new VerifyCalSettingsForm(parentScreen);
		}
		
		return theVerifyCalSettingsForm;
	}
}
