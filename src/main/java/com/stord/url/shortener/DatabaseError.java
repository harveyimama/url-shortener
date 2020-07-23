package com.stord.url.shortener;

/**
 * Class for nesting specific database errors
 *
 * @author Harvwy Imama
 * 
 */

public class DatabaseError extends RuntimeException {
	
	private String message;

	public DatabaseError(String message, Exception e) {
		super();
		this.message= message;
	}

}
