package com.miniproj.blog.ExceptionHandlers;

import java.util.Date;

public class ExceptionResponse {
	
	public Date datime;
	public String message;
	public String details;
	
	public ExceptionResponse(Date datime, String message, String details) {
		super();
		this.datime = datime;
		this.message = message;
		this.details = details;
	}

	public Date getDatime() {
		return datime;
	}

	public void setDatime(Date datime) {
		this.datime = datime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "ExceptionResponse [datime=" + datime + ", message=" + message + ", details=" + details + "]";
	}	

}
