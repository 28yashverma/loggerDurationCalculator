package loggerDuration.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonInclude(value = Include.NON_NULL)
public class EventDuration {

	public EventDuration() {
		// TODO Auto-generated constructor stub
	}

	public EventDuration(String eventId, Long duration, String type, String host, Boolean alert) {
		this.eventId = eventId;
		this.eventDuration = duration;
		this.type = type;
		this.host = host;
		this.alert = alert;
	}

	@Id
	private String eventId;
	private Long eventDuration;
	private String type;
	private String host;
	private Boolean alert;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public Long getEventDuration() {
		return eventDuration;
	}

	public void setEventDuration(Long eventDuration) {
		this.eventDuration = eventDuration;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Boolean getAlert() {
		return alert;
	}

	public void setAlert(Boolean alert) {
		this.alert = alert;
	}

	@Override
	public String toString() {
		return "EventDuration [eventId=" + eventId + ", eventDuration=" + eventDuration + ", type=" + type + ", host="
				+ host + ", alert=" + alert + "]";
	}

}
