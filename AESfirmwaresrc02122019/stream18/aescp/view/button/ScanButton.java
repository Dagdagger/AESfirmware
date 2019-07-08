package stream18.aescp.view.button;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import stream18.aescp.view.button.Button;

public class ScanButton extends Button {
	private static final long serialVersionUID = 1L;
	
	protected static ScanButton theScanButton = null;
	
	public ScanButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 1);
		config("resources/mode/VacuumChamber.png", "Scan", new Color(0x32CD32));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
					
				 Object hello = JOptionPane.showInputDialog(null,"","Please Scan",JOptionPane.PLAIN_MESSAGE,null,null,"");
				 System.out.println(hello);
			}	
		});
	}

	public static ScanButton getInstance(int x, int y) {
		if (theScanButton == null) {
			theScanButton = new ScanButton(x, y);
		}
		return theScanButton;
	}
	

	
 
	

	
}



