package loggerDuration.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@IdClass(EventId.class)
@JsonInclude(value = Include.NON_NULL)
public class Event implements Serializable {

	private static final long serialVersionUID = 1L;

	public Event() {
	}

	public Event(String id, String state, Long timestamp) {
		this(id, state, timestamp, null, null);
	}

	public Event(String id, String state, Long timestamp, String type, String host) {
		this.id = id;
		this.state = state;
		this.timestamp = timestamp;
		this.type = type;
		this.host = host;
	}

	@Id
	private String id;

	@Id
	private String state;
	private Long timestamp;
	private String type;
	private String host;

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", state=" + state + ", timestamp=" + timestamp + ", type=" + type + ", host=" + host
				+ "]";
	}

}
