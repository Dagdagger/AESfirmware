package stream18.aescp.view.button;

import java.awt.Color;




public class SaveParmsButton extends Button {
	private static final long serialVersionUID = 1L;
	private static SaveParmsButton theSaveButton;

	
	public SaveParmsButton(int x, int y) {
		super(BOTTOM_TYPE);
		moveTo(x, y, 1);
		config("resources/Save.png", "Save", new Color(0x3C90CB));

	}	

	public static SaveParmsButton getInstance(int x, int y) {
		if (theSaveButton == null) {
			theSaveButton = new SaveParmsButton(x, y);
		}
		return theSaveButton;
	}
	

	
 
	

	
}
