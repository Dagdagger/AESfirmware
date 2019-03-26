package stream18.aescp.view.button;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import stream18.aescp.Browser;
import stream18.aescp.model.TestModeCfgManager;

public abstract class Button extends JPanel {
	private static final long serialVersionUID = 1L;

	// Set, to avoid duplicates
	protected Set<ActionListener> listeners = new HashSet<ActionListener>();
	
	public void addActionListener(ActionListener l) {
		listeners.add(l);
	
	}

	public final static int START_TEST_REQ = 0;
	public final static int STOP_TEST_REQ = 1;
	public final static int AUTHENTICATE_REQ = 2;
	
	protected int x, y;
	protected float width;
	protected float height;
	
	boolean isDisabled = false;
		
	final static int BUTTON_B_HEIGHT = 128;
    final static int BUTTON_B_WIDTH = 128;
    final static int BUTTON_L_HEIGHT = 60;
    final static int BUTTON_L_WIDTH = 60;
	final static int ICON_WIDTH = 100;
	final static int ICON_HEIGHT = 100;
	private static final Color DEFAULT_ICON_COLOR = new Color(0xcccccc);
	private static final Color BUTTON_TEXT_COLOR = new Color(0xeeeeee);
	private static final int BUTTON_CORNER_X_RAD = 45;
	private static final int BUTTON_CORNER_Y_RAD = 45;
	
	protected String imageName;
	protected String text;
	private BufferedImage image;

	boolean highlight = false;

	public static final int BOTTOM_TYPE = 0;
	public static final int LEFT_TYPE = 1;

	private int type = BOTTOM_TYPE;
	
	private Color color1 = new Color(0xcccccc);
	private Color color2 = new Color(0xeeeeee);
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
                
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int w = getWidth();
        int h = getHeight();
        GradientPaint gp;
        if (highlight) {
        	gp = new GradientPaint(0, 0, color1, 0, h, color2);
        } else {
        	gp = new GradientPaint(0, 0, color2, 0, h, color1);
        }
        g2d.setPaint(gp);
        g2d.fillRoundRect(0, 0, w, h, BUTTON_CORNER_X_RAD, BUTTON_CORNER_Y_RAD);
        
        // The original image in file must be 100x100px
        if (type == BOTTOM_TYPE) {
        	int desiredIconWidth = (int)(BUTTON_B_WIDTH*width) / 2;
        	int w_margin = ((int)(BUTTON_B_WIDTH*width) - desiredIconWidth ) / 2;
            g.drawImage(image,
            		w_margin, 15*(int)(BUTTON_B_HEIGHT*height)/100, (int)(BUTTON_B_WIDTH*width) - w_margin, 65*(int)(BUTTON_B_HEIGHT*height)/100,
            		0, 0, ICON_WIDTH, ICON_HEIGHT, null);
        } else {
        	g.drawImage(image, 10, 10, 50, 50, 0, 0, 100, 100, null);
        }
        
        if (type == BOTTOM_TYPE) {
        	g.setColor(BUTTON_TEXT_COLOR);
        	g.setFont(Browser.FONT);
        	FontMetrics fm = g.getFontMetrics(Browser.FONT);
        	int tw = fm.stringWidth(text);
        	int th = fm.getAscent();
        	if (tw > (int)(BUTTON_B_WIDTH*width) * 0.8) {
        		g.setFont(Browser.FONT_2);
            	fm = g.getFontMetrics(Browser.FONT_2);
            	tw = fm.stringWidth(text);
            	th *= 1.5;
        	}

        	g.drawString(text, ((int)(BUTTON_B_WIDTH*width)/2)-(tw/2), (int)(BUTTON_B_HEIGHT*height)-(th/2));
        }    	
    }
	
    public Button(int type) {
    	this.type  = type;
    	setLayout(null);
    	setOpaque(false);
    	
    	addMouseListener(new MouseAdapter() {
    		@Override
    		public void mousePressed(MouseEvent e) {
    			if (isDisabled) return;
    			super.mousePressed(e);
    			// Highlite the button by repainting with clear background
    			highlight = true;
    			repaint();
    		}
    		
    		@Override
    		public void mouseReleased(MouseEvent e) {
    			if (isDisabled) return;
    			super.mouseReleased(e);
    			highlight = false;
    			repaint();
    		}    		
    	});
    }

    protected void moveTo(int x, int y, float width) {
    	moveTo(x, y, width, 1);
    }
    
    protected void moveTo(int x, int y, float width, float height) {
    	if (this.x == x && this.y == y && this.width == width && this.height == height) {
    		return;
    	}
    	
    	this.x = x;
    	this.y = y;
    	this.width = width;
    	this.height = height;
    	
    	if (type == BOTTOM_TYPE) {
    		setBounds(new Rectangle(x,  y, (int)(width * BUTTON_B_WIDTH), (int)(height * BUTTON_B_HEIGHT)));
    	} else {
    		setBounds(new Rectangle(x,  y, (int)(width * BUTTON_L_WIDTH), (int)(height * BUTTON_L_HEIGHT)));
    	}

    	this.repaint();
    }

    protected void config(String imageName, String text) {
    	config(imageName, text, null);
    	
    	//Border thickBorder = new LineBorder(Color.BLACK, 6);
    	//setBorder(thickBorder);
    }
    
    protected void config(String imageName, String text, Color color) {
    	this.imageName = imageName;
        try {
        	InputStream file = Button.class.getClassLoader().getResourceAsStream(imageName);
            image = ImageIO.read(file);
         } catch (IOException ex) {
        	 ex.printStackTrace();
         }
        
    	this.text = text;
    	
    	color1 = color==null?DEFAULT_ICON_COLOR:color;
    	int newRed = color1.getRed() + 0x22;
    	int newGreen = color1.getGreen() + 0x22;
    	int newBlue = color1.getBlue() + 0x22;
    	color2 = new Color(newRed>255?255:newRed, newGreen>255?255:newGreen, newBlue>255?255:newBlue);
    	
    	this.repaint();
	}
    
	protected void fireActionEvent(int id) {
		
	    ActionEvent event = new ActionEvent(this, id, null);
	    for (ActionListener l : listeners) {
	      l.actionPerformed(event);
	   
	      
	   	    }
	   
	}
}
