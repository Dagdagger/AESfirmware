package stream18.aescp.view.screen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.filechooser.FileSystemView;

import stream18.aescp.Browser;
import stream18.aescp.model.Program;
import stream18.aescp.view.button.HomeButton;
import stream18.aescp.view.form.BigFileChooser;
import stream18.aescp.view.fs.DirectoryRestrictedFileSystemView;

public class SaveScreen extends Screen {

	// Singleton
	static SaveScreen theSaveScreen = null;
	BigFileChooser fc;

	public SaveScreen() {
		super(false, true);

		//Create a file chooser
		String homeDir = System.getProperty("user.home");
		FileSystemView fsv = new DirectoryRestrictedFileSystemView(new File(homeDir + "/CP_Programs"));
		fc = new BigFileChooser(fsv.getHomeDirectory(),fsv, this);
		fc.setApproveButtonText("Save");

		fc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (event.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
					// TODO: save the file
					// checks:
					//  -1. The extension is ".prg" (or add it if not present)
					File fileToSave = fc.getSelectedFile();
					if (fileToSave.exists()) {
						// TODO: confirmation
						System.out.println("Ready to overwrite " + fileToSave);
					}
					String fileName = fileToSave.toString();
					if (!fileName.endsWith(".prg")) {
						fileName = fileName + ".prg";
					}

					try {
						OutputStream os = new FileOutputStream(fileName);
						Program.getInstance().save(os);
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
		desktop.add(jif);
		bottom.add(desktop);

		//like any other secondary screen (Not the home) this one will
		// display  a back button on the left bar
		leftSide.add(new HomeButton(HomeButton.DEFAULT_X, HomeButton.DEFAULT_Y));
	}

	public static SaveScreen getInstance() {
		if (theSaveScreen == null) {
			theSaveScreen = new SaveScreen();
		}
		// Refresh the FileChooser
		theSaveScreen.fc.rescanCurrentDirectory();
		
        // This has to be done every time, as the top bar is shared among
        // all the Screens that have a status
        theSaveScreen.addTop();
		
		return theSaveScreen;
	}

	// This class has a Singleton, to avoid instantiating and removing the
	// Screen more than once
	public void setActive(Screen previousScreen) {
		theSaveScreen = SaveScreen.getInstance();

		// Now ask the browser to make this screen active
		Browser.getInstance().setScreen(theSaveScreen, previousScreen);
	}	
}
