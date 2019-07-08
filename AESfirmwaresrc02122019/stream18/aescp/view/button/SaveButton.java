package stream18.aescp.view.button;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import stream18.aescp.view.screen.SaveScreen;

public class SaveButton extends Button {
	private static final long serialVersionUID = 1L;
	private static SaveButton theSaveButton;
	
	public SaveButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 1);
		config("resources/Save.png", "Set/Save", new Color(0x3C90CB));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				// Select a new Screen for the browser
				System.out.println("Saving values");
				SaveScreen.getInstance().setActive(null);
			}	
		});
	}	

	public static SaveButton getInstance(int x, int y) {
		if (theSaveButton == null) {
			theSaveButton = new SaveButton(x, y);
		}
		return theSaveButton;
	}
}
