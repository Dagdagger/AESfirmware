package stream18.aescp.view.button;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OkButton extends Button {
	private static final long serialVersionUID = 1L;
	private static OkButton theOkButton;
	
	public OkButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 0.75f, 0.75f);
		config("resources/Save.png", "Update", new Color(0x3C90CB));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// TODO: Notify
			}
		});
	}	

	public static OkButton getInstance(int x, int y) {
		if (theOkButton == null) {
			theOkButton = new OkButton(x, y);
		}
		return theOkButton;
	}
}
