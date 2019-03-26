package stream18.aescp.model;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Contains the persistent data related to a program, included the options.
 * Viewable and editable using Setup Screen (the Screen is notified of changes)
 * Persistent using Open and Save Screens
 * (the Screens call load() and save(), which will call loadObject() and saveObject())
 * 
 * @author egarcia
 *
 */

public class Program extends Persistent implements Serializable {
	private static final long serialVersionUID = 1L;

	private static Program theProgram;
	
	// If a Program has program (name), we consider
	// it LOADED and ready to run a Test
	private String program;
	private String testNum;
	private String testLot;
	private String operator;
	private Boolean saveAllPlot;
	private Boolean autoSwResults;
	private Boolean saveAllModes;
	private Boolean autoReset;
	
	private String clampTimer;
	private Boolean invertedClamp;
	private String sealTimer;
	private Boolean invertedSeal;
	private String markTimer;
	private Boolean invertedMark;
	private String bleedTime;
 

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getTestNum() {
		return testNum;
	}

	public void setTestNum(String testNum) {
		this.testNum = testNum;
	}

	public String getTestLot() {
		return testLot;
	}

	public void setTestLot(String testLot) {
		this.testLot = testLot;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Boolean getSaveAllPlot() {
		return saveAllPlot;
	}

	public void setSaveAllPlot(Boolean saveAllPlot) {
		this.saveAllPlot = saveAllPlot;
	}

	public Boolean getAutoSwResults() {
		return autoSwResults;
	}

	public void setAutoSwResults(Boolean autoSwResults) {
		this.autoSwResults = autoSwResults;
	}

	public Boolean getSaveAllModes() {
		return saveAllModes;
	}

	public void setSaveAllModes(Boolean saveAllModes) {
		this.saveAllModes = saveAllModes;
	}

	public Boolean getAutoReset() {
		return autoReset;
	}

	public void setAutoReset(Boolean autoReset) {
		this.autoReset = autoReset;
	}

	public String getClampTimer() {
		return clampTimer;
	}

	public void setClampTimer(String clampTimer) {
		this.clampTimer = clampTimer;
	}

	public Boolean getInvertedClamp() {
		return invertedClamp;
	}

	public void setInvertedClamp(Boolean invertedClamp) {
		this.invertedClamp = invertedClamp;
	}

	public String getSealTimer() {
		return sealTimer;
	}

	public void setSealTimer(String sealTimer) {
		this.sealTimer = sealTimer;
	}

	public Boolean getInvertedSeal() {
		return invertedSeal;
	}

	public void setInvertedSeal(Boolean invertedSeal) {
		this.invertedSeal = invertedSeal;
	}

	public String getMarkTimer() {
		return markTimer;
	}

	public void setMarkTimer(String markTimer) {
		this.markTimer = markTimer;
	}

	public Boolean getInvertedMark() {
		return invertedMark;
	}

	public void setInvertedMark(Boolean invertedMark) {
		this.invertedMark = invertedMark;
	}

	public String getBleedTime() {
		return bleedTime;
	}

	public void setBleedTime(String bleedTime) {
		this.bleedTime = bleedTime;
	}

	/**
	 * Called from Persistent.load(), before notifying listeners (Forms)
	 * that new data has arrived
	 */
	@Override
	protected void loadObject(InputStream is) {
		XMLDecoder d = new XMLDecoder(is);
		Program temp = (Program) d.readObject();
		try {
			BeanUtils.copyProperties(this, temp);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		d.close();
	}

	/**
	 * Called from Persistent.save(), after notifying listeners (Forms)
	 * that data is going to be saved
	 */
	@Override
	protected void saveObject(OutputStream os) {
		XMLEncoder e = new XMLEncoder(os);
		e.writeObject(this);
		e.close();
	}

	public static Program getInstance() {
		if (theProgram == null) {
			theProgram = new Program();
		}
		return theProgram;
	}	
}
