package stream18.aescp.view.screen;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import stream18.aescp.Browser;
import stream18.aescp.model.Program;
import stream18.aescp.view.button.HomeButton;
import stream18.aescp.view.form.BigFileChooser;
import stream18.aescp.view.fs.DirectoryRestrictedFileSystemView;

public class OpenScreen extends Screen {

	// Singleton
	static OpenScreen theOpenScreen = null;
	BigFileChooser fc;
	
	public OpenScreen() {
    	super(false, true);

		// Create a file chooser
		String homeDir = System.getProperty("user.home");
		FileSystemView fsv = new DirectoryRestrictedFileSystemView(new File(homeDir + "/CP_Programs"));
		fc = new BigFileChooser(fsv.getHomeDirectory(),fsv, this);
		fc.setApproveButtonText("Open");
		
		fc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (event.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
					File fileToLoad = fc.getSelectedFile();
					String fileName = fileToLoad.toString();

					try {
						InputStream is = new FileInputStream(fileName);
						Program.getInstance().load(is);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					
					// If everything went ok, go back to Home
					HomeScreen.getInstance().setActive(null);
				} else if (event.getActionCommand().equals(JFileChooser.CANCEL_SELECTION)) {
					System.out.println("Cancel");
					// Just go back to Home
					HomeScreen.getInstance().setActive(null);
				} else {
					// Unsupported action
					System.out.println("Action: " + event.getActionCommand());					
				}
			}
		});

		JDesktopPane desktop = new JDesktopPane();
		desktop.setBounds(0,  0, Browser.SCREEN_WIDTH-LEFT_WIDTH,  BOTTOM_HEIGHT+STATUS_HEIGHT);

		JInternalFrame jif = new JInternalFrame();
		jif.add(fc);
		jif.setBounds(0, 0, Browser.SCREEN_WIDTH-LEFT_WIDTH,  BOTTOM_HEIGHT+STATUS_HEIGHT);

		jif.setVisible(true);
		setNonDragable(jif);
		desktop.add(jif);
		bottom.add(desktop);
    	    	
    	//like any other secondary screen (Not the home) this one will
    	// display  a back button on the left bar
    	leftSide.add(new HomeButton(HomeButton.DEFAULT_X, HomeButton.DEFAULT_Y));    	
    }
		
	private void setNonDragable(JInternalFrame jif) {
		BasicInternalFrameUI ui = (BasicInternalFrameUI)jif.getUI();

		Component north = ui.getNorthPane();
		MouseMotionListener[] actions =
		(MouseMotionListener[])north.getListeners(MouseMotionListener.class);

		for (int i = 0; i < actions.length; i++)
			north.removeMouseMotionListener( actions[i] );

	}

	public static OpenScreen getInstance() {
        if (theOpenScreen == null) {
        	theOpenScreen = new OpenScreen();
        }
        
		// Refresh the FileChooser
		theOpenScreen.fc.rescanCurrentDirectory();
		
        // This has to be done every time, as the top bar is shared among
        // all the Screens that have a status
        theOpenScreen.addTop();
		
        return theOpenScreen;
	}

    // This class has a Singleton, to avoid instantiating and removing the
    // Screen more than once
	public void setActive(Screen previousScreen) {
		theOpenScreen = OpenScreen.getInstance();
		
		// Now ask the browser to make this screen active
		Browser.getInstance().setScreen(theOpenScreen, previousScreen);
	}
}
