package stream18.aescp.view.vk;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;

import javax.swing.JPanel;

import stream18.aescp.Browser;

public class VirtualKeyboard extends JPanel {
	private static final long serialVersionUID = 1L;

	private final int X_GAP = 2;
	private final int Y_GAP = 2;
	
	HashSet<Key> allKeys = new HashSet<Key>();

	protected boolean shifted;

	static private MouseAdapter defaultMouseAdapter;

	private VKListener vkListener;
	
	public VirtualKeyboard() {
		setBounds(new Rectangle(0, 0, Browser.SCREEN_WIDTH, Browser.SCREEN_HEIGHT));
		setBackground(new Color(0x444444));
		
		setLayout(null);
		
		shifted = false;

		getDefaultMouseAdapter();
		
		// First row
		
		int xp = 10;
		int yp = 10;
		
		register(new Key("1", "!", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("2", "@", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("3", "#", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("4", "$", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("5", "%", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("6", "^", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("7", "&", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("8", "*", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("9", "(", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("0", ")", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("-", "_", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("=", "+", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		
		// Second row
		
		yp += Y_GAP + Key.KEY_HEIGHT;
		xp = 10 + Key.KEY_WIDTH * 1 / 3;
		
		register(new Key("q", "Q", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("w", "W", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("e", "E", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("r", "R", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("t", "T", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("y", "Y", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("u", "U", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("i", "I", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("o", "O", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("p", "P", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("\"", "'", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		
		// This one is special: Delete
		register(new Key("resources/vk/Delete.png", "DELETE", xp, yp, (int)(Key.KEY_WIDTH * 1.33), false));
		xp += X_GAP + Key.KEY_WIDTH;

		// Third row
		
		yp += Y_GAP + Key.KEY_HEIGHT;
		xp = 10 + Key.KEY_WIDTH * 2 / 3;

		register(new Key("a", "A", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("s", "S", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("d", "D", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("f", "F", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("g", "G", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("h", "H", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("j", "J", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("k", "K", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("l", "L", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key(";", ":", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("[", "{", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("]", "}", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		
		// Fourth row
		
		yp += Y_GAP + Key.KEY_HEIGHT;
		xp = 10;

		Key shiftKey = (Key) register(new Key("resources/vk/Shift.png", "", xp, yp, Key.KEY_WIDTH));
		shiftKey.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				shifted = !shifted;
				// Notify all keys that they have been shifted
				for(Key key : allKeys) {
					key.setShifted(shifted);
					key.repaint();
				}
			}
		});
				
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("z", "Z", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("x", "X", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("c", "C", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("v", "V", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("b", "B", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("n", "N", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("m", "M", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key(",", "<", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key(".", ">", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("/", "?", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;
		register(new Key("~", "`", xp, yp));
		xp += X_GAP + Key.KEY_WIDTH;

		// Fifth row
		
		yp += Y_GAP + Key.KEY_HEIGHT;
		xp = 10;

		// Special 
		register(new Key("resources/vk/Trash.png", "CLEAR", xp, yp, (int)(Key.KEY_WIDTH*2.5)));
		
		xp += (X_GAP + Key.KEY_WIDTH) * 3;
		register(new Key(null, " ", xp, yp, (X_GAP + Key.KEY_WIDTH)*6));
		
		xp += (X_GAP + Key.KEY_WIDTH) * 6.5;
		register(new Key("resources/vk/Done.png", "DONE", xp, yp, (X_GAP + Key.KEY_WIDTH)*3));
	}

	// Singleton
	private MouseAdapter getDefaultMouseAdapter() {
		if (defaultMouseAdapter == null) {
			// Default mouse adapter for the keys on this keyboard
			defaultMouseAdapter = new MouseAdapter() {
				private Key lastPressedKey = null;

				@Override
				public void mousePressed(MouseEvent e) {
					super.mousePressed(e);
					if (lastPressedKey == null) {
						lastPressedKey  = (Key) e.getComponent();
					}
				}
				
				@Override
				public void mouseReleased(MouseEvent e) {
					super.mouseReleased(e);
					Key key = (Key) e.getComponent();
					if (key != lastPressedKey) {
						// We started clicking on a different key, so forget it.
						// This could happen, for instance, if we were clicking
						// on an Input text and the screen changed so fast
						// that we are touching a key just as we enter the screen
					} else {
//						System.out.println("Released: " + key.getText());
						// Default behavior is just to notify a VKListener 
						// The VKListener will typically add the value to a textField buffer
						if (vkListener != null) {
							vkListener.keyTyped(key);
						}
					}
					lastPressedKey = null;
				}
			};	
		}
		
		return defaultMouseAdapter;
	}

	/*
	 * Adds the key to the list of keys of this keyboard.
	 * Also, sets a listener
	 */
	public Key register(Key key) {
		Key k = (Key) super.add(key);
		allKeys.add(k);
		
		k.addMouseListener(this.getDefaultMouseAdapter());
		
		return k;
	}

	public void setVKListener(VKListener vkListener) {
		this.vkListener = vkListener;
	}
}
