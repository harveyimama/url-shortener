package com.stord.url.shortener;

public class HostLocationError extends RuntimeException {
	private String message;

	public HostLocationError(String message, Exception e) {
		super();
		this.message= message;
	}

}
