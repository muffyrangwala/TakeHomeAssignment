package com.walmart.search.vo;

import java.util.Date;

public class ErrorDetails {

	private Date timestamp;
	private String status;
	private String message;

	public ErrorDetails(Date timestamp, String status, String message) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.message = message;
	}

	public ErrorDetails() {
		super();
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
