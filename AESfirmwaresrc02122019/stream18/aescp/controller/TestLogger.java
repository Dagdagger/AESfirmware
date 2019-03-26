package stream18.aescp.controller;

public class TestLogger {

	private static TestLogger theTestLogger;
	
	public enum LEVEL {
		SYS_ERROR, USER_ERROR, INFO, DEBUG
	}

	public void log(LEVEL errLevel, String msg) {
		System.out.println(levelAsText(errLevel) + " - " + msg);
	}

	private String levelAsText(LEVEL errLevel) {
		switch (errLevel) {
		case SYS_ERROR:
			return "SYS_ERR";
		case USER_ERROR:
			return "USR_ERR";
		case INFO:
			return "INFO";
		case DEBUG:
			return "DEBUG";
		default:
			return "-";
		}
	}

	public void logSys(String msg) {
		log(TestLogger.LEVEL.SYS_ERROR, msg);
	}

	public void logInfo(String msg) {
		log(TestLogger.LEVEL.INFO, msg);
	}
	
	public static TestLogger getInstance() {
        if (theTestLogger == null) {
        	theTestLogger = new TestLogger();
        }
        return theTestLogger;
	}

}
