package stream18.aescp.view.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JTextField;

import stream18.aescp.Browser;
import stream18.aescp.view.button.BackButton;
import stream18.aescp.view.button.CancelButton;
import stream18.aescp.view.vk.Key;
import stream18.aescp.view.vk.VKListener;
import stream18.aescp.view.vk.VirtualKeyboard;

public class VKScreen extends Screen implements VKListener {

	private static final int INPUT_TEXT_HEIGHT = Browser.FONT.getSize() * 3/2;

	private static final int MARGIN = 20;

	protected JPanel vk_top;
	
	// Singleton
	static VKScreen theVKScreen = null;
	private static VirtualKeyboard virtualKeyboard;
	private CancelButton cancelButton;
	private JTextField textField;
	private JTextField textFieldBuffer;
	
	public VKScreen() {
    	super(false, false);

    	cancelButton = new CancelButton(BackButton.DEFAULT_X, BackButton.DEFAULT_Y);
    	leftSide.add(cancelButton);
    	
    	virtualKeyboard = new VirtualKeyboard();
		bottom.add(virtualKeyboard);
		
		textFieldBuffer = new JTextField();
		textFieldBuffer.setBounds(new Rectangle(MARGIN,
//												(Browser.SCREEN_HEIGHT - BOTTOM_HEIGHT - INPUT_TEXT_HEIGHT) / 2,
												(TOP_HEIGHT - INPUT_TEXT_HEIGHT) / 2,
												Browser.SCREEN_WIDTH - LEFT_WIDTH - 2*MARGIN,
												INPUT_TEXT_HEIGHT));
		
		Font font = new Font(Browser.FONT.getFontName(), Browser.FONT.getStyle(), Browser.FONT.getSize() * 3/2);
		textFieldBuffer.setFont(font);
		textFieldBuffer.setBorder(null);
		textFieldBuffer.setBackground(Color.gray);
		textFieldBuffer.setForeground(Color.white);

vk_top = new JPanel();
vk_top.setLayout(null);
vk_top.setBackground(new Color(0xaaaaaa));
vk_top.setBounds(new Rectangle(LEFT_WIDTH,  0,  Browser.SCREEN_WIDTH, TOP_HEIGHT));
screenCanvas.add(vk_top);
vk_top.setVisible(false);		
		vk_top.add(textFieldBuffer);
vk_top.setVisible(true);
    }
	
	public static VKScreen getInstance(JTextField textField) {
        if (theVKScreen == null) {
        	theVKScreen = new VKScreen();
        }
        theVKScreen.textField = textField;
        theVKScreen.textFieldBuffer.setText(theVKScreen.textField.getText());
        return theVKScreen;
	}

    // This class has a Singleton, to avoid instantiating and removing the
    // Screen more than once
	public void setActive(Screen previousScreen) {
		cancelButton.setPreviousScreen(previousScreen);
		
		virtualKeyboard.setVKListener(this);

		// Now ask the browser to make this screen active
		Browser.getInstance().setScreen(this, previousScreen);
	}

	@Override
	public void keyTyped(Key key) {
		// Just add the typed key to the buffer, except for special keys
		String keyText = key.getText();
		String prevText = textFieldBuffer.getText();
		if (keyText.equals("CLEAR")) {
			textFieldBuffer.setText("");
		}else if (keyText.equals("DONE")) {
			// Update the field value and close the screen
			textField.setText(textFieldBuffer.getText());
			cancelButton.navigatePrevious();
		} else if (keyText.equals("DELETE")) {
			int len = prevText.length();
			if (len > 0) {
				textFieldBuffer.setText(prevText.substring(0, len-1));
			}
		} else {
			if (textFieldBuffer.getText().length() < textField.getColumns()) {
				textFieldBuffer.setText(prevText + keyText);
			}
		}
	}
}
