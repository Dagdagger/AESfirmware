package stream18.aescp.view.form;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
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
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.OptionalDouble;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;

import stream18.aescp.Browser;
import stream18.aescp.view.Plot;
import stream18.aescp.view.Plot.AxisFormat;
import stream18.aescp.view.Plot.AxisOptions;
import stream18.aescp.view.Plot.Data;
import stream18.aescp.view.Plot.LegendFormat;
import stream18.aescp.view.Plot.PlotOptions;
import stream18.aescp.view.button.Button;
import stream18.aescp.view.button.StartButton;
import stream18.aescp.view.screen.Screen;
import stream18.aescp.view.screen.VKScreen;
//import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public abstract class Form extends JPanel {
	private static final long serialVersionUID = 1L;

	private static final int UNITS_GAP = 8;

	protected final int GRAPH_WIDTH = (Browser.SCREEN_WIDTH - Screen.LEFT_WIDTH) / 3 * 2;
	
	protected Screen parentScreen;

	static public MyPlotPanel plotPanel;
	static public StartButton but;

	public Form(Screen parentScreen) {
		this.parentScreen = parentScreen;
		setLayout(null);
	}
	
	public void setParentScreen(Screen parentScreen) {
		this.parentScreen = parentScreen;		
	}
	
	
	protected JCheckBox createcommandCheckBox(String text, int x, int y, int w, int h) {
		CommandCheckBox bCheckBox = new CommandCheckBox(text);
		bCheckBox.setBounds(new Rectangle(x, y, w, h));
		add(bCheckBox);
		
		return bCheckBox.getCheckBox();
	}
	protected JButton createUnitButton(String btnText, int x, int y,int h,int w ) {
		JButton bUnitBox = new JButton(btnText);
		bUnitBox.setLayout(null);
		bUnitBox.setBounds(new Rectangle(x, y, w, h));
		add(bUnitBox);
		return bUnitBox;
	}
	
	/**
	 * @param Button

	 * @return
	 */
	
	protected JComboBox<String> createComboField(String labelText,
			int x, int y,
			int labelsRight,
			int tfCols,
			String[] values) {
		
		FontMetrics fm = this.getFontMetrics(Browser.FONT);
		int tw = fm.stringWidth("0") * tfCols;
		int th = fm.getAscent();

		JLabel l = new JLabel(labelText, JLabel.TRAILING);
		l.setBounds(new Rectangle(x, y, labelsRight, (int) (th*1.4)));
		l.setFont(Browser.FONT);
		add(l);
		
		JComboBox<String> comboField;
		comboField = new JComboBox<String>(values);
		comboField.setBounds(new Rectangle(x + labelsRight + 10, y, tw, (int) (th*1.4)));
		comboField.setFont(Browser.FONT);
		add(comboField);

		return comboField;
	}
	
	/**
	 * @param labelText
	 * @param x
	 * @param y
	 * @param labelsRight x position of label's rightmost 
	 * @param tfCols input text columns
	 * @param hasVK	is editable via Virtual Keyboard?
	 * @return
	 */
	

	
	protected JTextField createTextField(String labelText,
										int x, int y,
										int labelsRight,
										int tfCols,
										boolean hasVK) {
		return createTextFieldInternal(labelText, x, y, labelsRight, tfCols, hasVK, false);
	}
	
	/**
	 * @param labelText
	 * @param x
	 * @param y
	 * @param labelsRight x position of label's rightmost 
	 * @param tfCols input text columns
	 * @param hasVK	is editable via Virtual Keyboard?
	 * @return
	 */
	protected JTextField createPasswordField(String labelText,
										int x, int y,
										int labelsRight,
										int tfCols,
										boolean hasVK) {
		return createTextFieldInternal(labelText, x, y, labelsRight, tfCols, hasVK, true);
	}
	
	protected JTextField createUserField(String labelText,

			int x, int y,int labelsRight,
				int tfCols,
				boolean hasVK) {
	return createTextFieldInternal(labelText, x, y, labelsRight, tfCols, hasVK, true);
	}
	protected JTextField createTextFieldInternal(String labelText,
										int x, int y,
										int labelsRight,
										int tfCols,
										boolean hasVK,
										boolean isPassword) {
    	FontMetrics fm = this.getFontMetrics(Browser.FONT);
    	int tw = fm.stringWidth("0") * tfCols;
    	int th = fm.getAscent();
		
	    JLabel l = new JLabel(labelText, JLabel.TRAILING);
	    l.setBounds(new Rectangle(x, y, labelsRight, (int) (th*1.4)));
	    l.setFont(Browser.FONT);
	    add(l);
	    JTextField textField;
	    if (isPassword) {
		    textField = new JPasswordField(tfCols);
	    } else {
		    textField = new JTextField(tfCols);
	    }
	    textField.setBounds(new Rectangle(x + labelsRight + 10, y, tw, (int) (th*1.4)));
	    textField.setFont(Browser.FONT);
	    add(textField);
	    
	    // Add the listener to start a VKScreen when the field is touched
	    if (hasVK) {
	    	textField.addMouseListener(new MouseAdapter() {
	    		@Override
	    		public void mousePressed(MouseEvent e) {
	    			super.mousePressed(e);
					// I must communicate in some way the field that we are editing
					VKScreen.getInstance(textField).setActive(parentScreen);
	    		};
			});
	    }
	    
	    return textField;
	}
	
	/**
	 * @param labelText
	 * @param x
	 * @param y
	 * @param labelsRight x position of label's rightmost 
	 * @param tfCols input text columns
	 * @param hasVK	is editable via Virtual Keyboard?
	 * @return
	 */
	protected JTextField createTextFieldWithUnits(String labelText,
										int x, int y,
										int labelsRight,
										int tfCols,
										boolean hasVK,
										String units) {
		JTextField retVal = createTextField(labelText, x, y, labelsRight, tfCols, hasVK);
		
		// Now add the units
	    JLabel l = new JLabel(units, JLabel.LEADING);
	    l.setBounds(retVal.getBounds().x + retVal.getBounds().width + UNITS_GAP, y,
	    			retVal.getBounds().width, retVal.getBounds().height);
	    l.setFont(Browser.FONT);
	    add(l);
		
		return retVal;
	}

	
	/**
	 * @param labelText
	 * @param x
	 * @param y
	 * @param size
	 * @return
	 */
	protected JTextField createVTextField(String labelText,
										int x, int y,
										int width,
										boolean bigFont) {
    	FontMetrics fm = this.getFontMetrics(Browser.FONT);
    	int tw = fm.stringWidth(labelText);
    	int th = fm.getAscent();
		
	    JLabel l = new JLabel(labelText, JLabel.TRAILING);
	    l.setBounds(new Rectangle(x, y, tw, (int) (th*1.4)));
	    l.setFont(Browser.FONT);
	    add(l);
	    JTextField textField = new JTextField(10);
	    if (bigFont) {
	    	fm = this.getFontMetrics(Browser.FONT_BIG);
	    	th = fm.getAscent();
		    textField.setBounds(new Rectangle(x, (int) (y + th*0.8), width, (int) (th*1.4)));
		    textField.setFont(Browser.FONT_BIG);
	    } else {
		    textField.setBounds(new Rectangle(x, (int) (y + th * 1.5), width, (int) (th*1.4)));	    	
		    textField.setFont(Browser.FONT);
	    }
	    add(textField);
	    
	    return textField;
	}
	
	protected JTextField createStatusText(String text,
			int x, int y, int width) {
		FontMetrics fm = this.getFontMetrics(Browser.FONT);
		int th = fm.getAscent();

		JTextField textField = new JTextField(text.length());
		textField.setEditable(false);
		textField.setFont(Browser.FONT);
		textField.setText(text);
		textField.setBackground(Color.WHITE);
		textField.setBounds(new Rectangle(x, y, width, (int) (th*1.2)));
		
		
		add(textField);

		return textField;
	}
	
	/**
	 * @param text
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @return
	 */	
	

//	protected JButton createUnitBox(String text, int x, int y, int w, int h) {
	//	JButton bUnitBox = new JButton(text);
	//	bUnitBox.setBounds(new Rectangle(x, y, w, h));
	//	add(bUnitBox);
		
//		return bUnitBox;
//	}
	

	

	
	protected JCheckBox createCheckBox(String text, int x, int y, int w, int h) {
		BigCheckBox bCheckBox = new BigCheckBox(text);
		bCheckBox.setBounds(new Rectangle(x, y, w, h));
		add(bCheckBox);
		
		return bCheckBox.getCheckBox();
	}
	
	protected JPanel createIcon(String imageName, int x, int y, int w, int h) {
		JPanel panel = new JPanel();

		panel.setLayout(null);
		panel.setBounds(new Rectangle(x, y, w, h));
		
		panel.setOpaque(false);

		BufferedImage image = null;
		
		try {
	    	InputStream file = Button.class.getClassLoader().getResourceAsStream(imageName);
			image = ImageIO.read(file);
			
			BufferedImage resizedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = resizedImage.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.setComposite(AlphaComposite.Src);
			g.drawImage(image, 0, 0, w, h, null);
			g.dispose();
			image = resizedImage;
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		JLabel label = new JLabel(new ImageIcon(image));
		label.setBounds(new Rectangle(0, 0, w, h));
	    panel.add(label);
		
		add(panel);
		
		return panel;
	}
	
	/*
	protected XChartPanel<XYChart> addChart() {
		// This form contains two main areas: the graphic editor and the 
		// results preview
		
		double[] xData = new double[] { 1, 2, 3, 4.00, 5.0, 6.0, 7.0, 8.00, 9, 10, 11.0, 12.0, 13.0, 14.0, 15, 16, 17.0, 18.0, 19.0, 20, 21.0, 22.0, 23, 24, 25, 26, 27, 28, 29, 30};
		double[] yData = new double[] { 1, 4, 6, 6.75, 7.5, 8.0, 8.5, 8.75, 9,  9,  7.5,  6.5,  5.5,  4.8,  4,  3,  2.5,  1.8,  1.5, 1,   0.7,  0.2,  0,  0,  0,  0,  0,  0,  0,  0};

		XYChart chart = new XYChart(GRAPH_WIDTH, Screen.BOTTOM_HEIGHT - Screen.STATUS_HEIGHT);
		chart.addSeries("P", xData, yData);

		chart.getStyler().setLegendVisible(false);
		Color[] seriesColors = {Color.RED};
		chart.getStyler().setSeriesColors(seriesColors);
		Marker[] seriesMarkers = {new None()};
		chart.getStyler().setSeriesMarkers(seriesMarkers );

		XChartPanel<XYChart> chartPanel = new XChartPanel<XYChart>(chart);
		chartPanel.setBounds(0, 0, GRAPH_WIDTH, Screen.BOTTOM_HEIGHT - Screen.STATUS_HEIGHT);
		add(chartPanel);
		
		return chartPanel;
	}
	
	/*
	 * PLOT PANEL
	 */
	protected MyPlotPanel addPlot() {
		plotPanel = new MyPlotPanel();
		plotPanel.setBounds(0, 0, GRAPH_WIDTH, Screen.BOTTOM_HEIGHT - Screen.STATUS_HEIGHT);
		add(plotPanel);
		return plotPanel;
	}
	
	public class MyPlotPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		Plot plot;
		Data data;
		PlotOptions opts;
		AxisOptions xAxisOpts, yAxisOpts;
		
		public void setData(double[] x, double[] y) {
			
			plot = Plot.plot(opts);
			data = Plot.data();
			data.xy(x, y);
			
			
			OptionalDouble xmax = Arrays.stream(x).max();
			OptionalDouble xmin = Arrays.stream(x).max();
			OptionalDouble ymin = Arrays.stream(x).max();
			OptionalDouble ymax = Arrays.stream(x).max();
			
			
			
			double xMaxValue = xmax.orElse(-1);
			double xMinValue = xmin.orElse(-1);
			double yMaxValue = ymax.orElse(-1);
			double yMinValue = ymin.orElse(-1);

			int xMaxValueInt = (int)xMaxValue +1 ;
			int xMinValueInt = (int)xMinValue;
			int yMaxValueInt = (int)yMaxValue;
			int yMinValueInt = (int)yMinValue;
//			xyAxisOpts = Plot.axisOpts();
//			xyAxisOpts.format(AxisFormat.DATE.NUMBER_INT);

//xAxisOpts.range(x[0], x[x.length-1]);
xAxisOpts.range(0, x[x.length-1]);
yAxisOpts.range(0, 700);

			plot.xAxis("", xAxisOpts);
			plot.yAxis("", yAxisOpts);
			plot.series("", data, null);
			
			repaint();
		}
		
		public MyPlotPanel() {
	          super();
	          setOpaque(false);
	          
				opts = Plot.plotOpts();
				opts.width(GRAPH_WIDTH);
				opts.height(Screen.BOTTOM_HEIGHT - Screen.STATUS_HEIGHT);
				opts.padding(0);
				opts.plotPadding(0);
				opts.legend(LegendFormat.NONE);
				opts.titleFont(new Font("Arial", 0, 0));
				opts.labelFont(new Font("Arial", 0, 8));
				opts.grids(8, 8);
				
				plot = Plot.plot(opts);

				xAxisOpts = Plot.axisOpts();
				xAxisOpts.format(AxisFormat.DATE.NUMBER_INT);

				yAxisOpts = Plot.axisOpts();
				yAxisOpts.format(AxisFormat.DATE.NUMBER_INT);

				plot.xAxis("", xAxisOpts);
				plot.yAxis("", yAxisOpts);

				data = Plot.data();

				plot.series("", data, null);
		}
		
		@Override
		  public void paintComponent(Graphics g) {
		    super.paintComponent(g);

			try {
				Image image = plot.getImage();
			    g.drawImage(image, 0, 0, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		  }
		
		
		public JTable createTable(Array row) {

		
			 
			  JTable table = new JTable();
		        table.setFillsViewportHeight(true);
			return createTable(row);
		
		}
		
		
	}
}
