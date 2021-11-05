package loggerDuration.model;

import java.io.Serializable;

public class EventId implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private String state;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
