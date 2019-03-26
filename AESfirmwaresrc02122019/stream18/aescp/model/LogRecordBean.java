package stream18.aescp.model;

public class LogRecordBean {

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	private String timestamp;
	private String action;

	public LogRecordBean(String timestamp, String action) {
		this.timestamp = timestamp;
		this.action = action;
	}

}
