package com.stord.url.shortener;


/**
 * For nesting specific Conversion errors
 *
 * @author Harvwy Imama
 * 
 */
public class ConversionError extends DatabaseError {
	
	private String message;

	public ConversionError(String message, Exception e) {
		super(message,e);
		this.message = message;
}

}
