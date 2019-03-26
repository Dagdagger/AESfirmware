package stream18.aescp.view.form;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import stream18.aescp.Browser;

// This will be a JPanel
public class BigLabel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static final Color DEFAULT_BL_COLOR = Color.GREEN;

	private static final Color BUTTON_TEXT_COLOR = Color.BLACK;
	Color color1;
	Color color2;


	boolean highlight = false;

	private String text;

	private int width;

	private int height;
	
	public BigLabel(String text, Color color, int x, int y, int width, int height) {
		this.text = text;
		this.color1 = color != null ? color : DEFAULT_BL_COLOR;
		this.width = width;
		this.height = height;
		
    	setLayout(null);
    	
    	int newRed = color1.getRed() + 0x22;
    	int newGreen = color1.getGreen() + 0x22;
    	int newBlue = color1.getBlue() + 0x22;
    	color2 = new Color(newRed>255?255:newRed, newGreen>255?255:newGreen, newBlue>255?255:newBlue);
    	
    	setBounds(x, y, width, height);
	}
	
	
	public void setText(String text) {
		this.text = text;
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
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(4));
        g2d.drawRoundRect(2, 2, w-4, h-4, 25, 25);
        
    	g.setColor(BUTTON_TEXT_COLOR);
    	g.setFont(Browser.FONT_BIG);
    	FontMetrics fm = g.getFontMetrics(Browser.FONT_BIG);
    	int tw = fm.stringWidth(text);
    	int th = fm.getAscent();
    	g.drawString(text, (width - tw) / 2, (height+th) / 2);

    }	
}
