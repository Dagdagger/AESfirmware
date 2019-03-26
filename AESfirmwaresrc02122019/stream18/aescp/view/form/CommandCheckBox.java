package stream18.aescp.view.form;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import stream18.aescp.Browser;
import stream18.aescp.view.button.Button;

// This will be a JPanel
public class CommandCheckBox extends JPanel {
	private static final long serialVersionUID = 1L;
	private JCheckBox checkBox;
	
	private static final Color DEFAULT_CB_COLOR = new Color(0x5D5C57);
	Color color1;
	Color color2;


	boolean highlight = false;
	
	public CommandCheckBox(String text) {
    	setLayout(null);
    	
    	color1 = DEFAULT_CB_COLOR;
    	int newRed = color1.getRed() + 0x22;
    	int newGreen = color1.getGreen() + 0x22;
    	int newBlue = color1.getBlue() + 0x22;
    	color2 = new Color(newRed>255?255:newRed, newGreen>255?255:newGreen, newBlue>255?255:newBlue);

    	    	
		checkBox = new JCheckBox(text, false);
		
		ImageIcon icon = null;
		try {
			InputStream file = Button.class.getClassLoader().getResourceAsStream("resources/starticon.png");
			BufferedImage image = ImageIO.read(file);
			Image newImage = image.getScaledInstance(36, 36, Image.SCALE_DEFAULT);
			icon = new ImageIcon(newImage);
			checkBox.setIcon(icon);
			

			file = Button.class.getClassLoader().getResourceAsStream("resources/StartD_normal.png");
			image = ImageIO.read(file);
			newImage = image.getScaledInstance(36, 36, Image.SCALE_DEFAULT);
			icon = new ImageIcon(newImage);
			checkBox.setSelectedIcon(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		checkBox.setFocusPainted(false);
		checkBox.setOpaque(false);
    	checkBox.setFont(Browser.FONT);
    	checkBox.setForeground(Color.red);
		
		add(checkBox);
		
		checkBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				// Highlite the button by repainting with clear background
				highlight = true;
				repaint();
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				highlight = false;
				repaint();
			}
			
		});
	}
	
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
        g2d.fillRoundRect(0, 0, w, h, 25, 25);
    }
	
	@Override
	public void setBounds(Rectangle r) {
		super.setBounds(r);
		r.x=2; r.y=2;
		r.width = r.width - 2*2;
		r.height = r.height - 2*2;
		checkBox.setBounds(r);
	}
	
	public JCheckBox getCheckBox() {
		return checkBox;
	}
}
