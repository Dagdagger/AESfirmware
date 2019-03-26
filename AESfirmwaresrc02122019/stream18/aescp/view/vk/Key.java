package stream18.aescp.view.vk;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

import stream18.aescp.Browser;
import stream18.aescp.view.button.Button;

public class Key extends JPanel {
	private static final long serialVersionUID = 1L;
	static final int KEY_WIDTH = 56;
	public static final int KEY_HEIGHT = KEY_WIDTH * 4/3;

	static final Border lineBorder = BorderFactory.createLineBorder(Color.GRAY, 2);
	static final  Border borderBase = BorderFactory.createBevelBorder(BevelBorder.RAISED);
	static final  Border border = new CompoundBorder(lineBorder, borderBase);
	static final  Border borderPressed = BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED);
	static final  Border borderP = new CompoundBorder(lineBorder, borderPressed);

	private String text1, text2;
    private boolean shifted = false;
	private boolean showText = true;;
	
	private FontMetrics fm;
	private String imageName;
	private BufferedImage image;
	private int width;

    private void setText(String text1, String text2, int x, int y, int width) {
    	this.text1 = text1;
    	this.text2 = text2;
    	this.width = width;
    	setBounds(new Rectangle(x, y, width, KEY_HEIGHT));
        addBorder();
	}
    
    private void setImage(String imageName) {
        this.imageName = imageName;
    	if (imageName != null) {
        	try {
        		InputStream file = Button.class.getClassLoader().getResourceAsStream(imageName);
        		image = ImageIO.read(file);
        	} catch (IOException ex) {
        		ex.printStackTrace();
        	}    		
    	}
    }
	
	public Key(String text1, String text2, int x, int y) {
    	setText(text1, text2, x, y, KEY_WIDTH);
	}

    public Key(String imageName, String text, int x, int y, int width) {
    	setText(text, text, x, y, width);
    	setImage(imageName);
	}

    // Text must not be shown sometimes, for instance for keys with
    // only icon. But still it is needed, as it is the key identifier.
	public Key(String imageName, String text, int x, int y, int width, boolean showText) {
    	setText(text, text, x, y, width);
    	setImage(imageName);
    	this.showText = showText;
	}

	private void addBorder() {
    	setBorder(border);
    	
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				setBorder(borderP);
			}
		
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				setBorder(border);
			}
		});
	}

	protected void paintComponent(Graphics g) {
    	super.paintComponent(g);

    	String text = shifted ? text2 : text1;

    	Graphics2D g2 = (Graphics2D)g;

    	if (imageName != null) {
    		// The original image in file must be 100x100px
    		g.drawImage(image, 10, 10, KEY_WIDTH-10, KEY_HEIGHT-10, 0, 0, 100, 100, null);
    	}

    	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
    			RenderingHints.VALUE_ANTIALIAS_ON);

    	g2.setFont(Browser.FONT);

    	if (fm == null)
    		fm = g2.getFontMetrics(Browser.FONT);

    	int tw = fm.stringWidth(text);
    	int th = fm.getAscent();

    	g2.setColor(Color.BLACK);
    	if (imageName != null) {
        	if (showText) {
            	g2.setFont(new Font(Browser.FONT.getFontName(), Browser.FONT.getStyle(), (int)(Browser.FONT.getSize() * 3 / 4)));
        		g2.drawString(text, (width + KEY_WIDTH - tw) / 2, (KEY_HEIGHT + th) / 2);
        	}
    	} else {
        	if (showText) {
        		g2.drawString(text, (width - tw) / 2, (KEY_HEIGHT + th) / 2);
        	}
    	}
    }

	public String getText() {
		return shifted ? text2 : text1;
	}

	public void setShifted(boolean shifted) {
		this.shifted = shifted;
	}		
}
