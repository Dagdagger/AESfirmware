package stream18.aescp.view.form;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import stream18.aescp.Browser;
import stream18.aescp.view.screen.Screen;
import stream18.aescp.view.screen.VKScreen;

public class BigFileChooser extends JFileChooser {
	private static final long serialVersionUID = 1L;

	void initFileChooser(Screen previousScreen) {
		JList<?> list = findFileList(this);

		for (MouseListener l : list.getMouseListeners()) {
			if (l.getClass().getName().indexOf("FilePane") >= 0)
			{
				list.removeMouseListener(l);
				list.addMouseListener(new MyMouseListener(l));
			}
		}
		
		// Change the font
		setFileChooserFont(this.getComponents(), Browser.FONT);

		JTextField field = findJTextField(this);
		// Fist, avoid entering data directly into this field
		field.setEditable(false);
		// Let it look white, as used to be
		field.setBackground(Color.WHITE);
		
		// Now, open VirtualKeyboard for this field if clicked
		field.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				VKScreen.getInstance(field).setActive(previousScreen);
			}
		});
		
		setFileSelectionMode(JFileChooser.FILES_ONLY);
	}

	public BigFileChooser(Screen previousScreen) {
		super();
		initFileChooser(previousScreen);
	}
	
	public BigFileChooser(FileSystemView fsv, Screen previousScreen) {
		super(fsv);
		initFileChooser(previousScreen);
	}

	public BigFileChooser(File homeDirectory, FileSystemView fsv, Screen previousScreen) {
		super(homeDirectory, fsv);
		initFileChooser(previousScreen);
	}

	/**
	 * Used internally to find the list of files component
	 * 
	 * @param comp
	 * @return
	 */
	private JList<?> findFileList(Component comp) {
		if (comp instanceof JList)
			return (JList<?>)comp;

		if (comp instanceof Container) {
			for (Component child : ((Container)comp).getComponents()) {
				JList<?> list = findFileList(child);
				if (list != null)
					return list;
			}
		}

		return null;
	}

	/**
	 * Used internally to find the text field of the FileChooser
	 * 
	 * @param comp
	 * @return
	 */
	private JTextField findJTextField(Component comp) {
		if (comp instanceof JTextField)
			return (JTextField)comp;

		if (comp instanceof Container) {
			for (Component child : ((Container)comp).getComponents()) {
				JTextField field = findJTextField(child);
				if (field != null)
					return field;
			}
		}

		return null;
	}
	
	/**
	 * Used internally 
	 * 
	 * @param comp
	 * @param font
	 */
	public void setFileChooserFont(Component[] comp, Font font){
		for(int x = 0; x < comp.length; x++) {
			if(comp[x] instanceof Container) setFileChooserFont(((Container)comp[x]).getComponents(), font);
			try {
				comp[x].setFont(font);
			} catch(Exception e){}//do nothing
		}
	}
	
	/**
	 * To avoid entering the "edit" mode of the directory and file names
	 * 
	 * @author egarcia
	 *
	 */
	private class MyMouseListener extends MouseAdapter {
		MyMouseListener(MouseListener listenerChain) {
			m_listenerChain = listenerChain;
		}
		public void mouseClicked(MouseEvent event) {
			if (event.getClickCount() > 1)
				m_listenerChain.mouseClicked(event);
		}
		private MouseListener m_listenerChain;
	}
}