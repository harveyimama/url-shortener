package com.stord.url.shortener;



/**
 * Interface that for Converter implementations
 *
 * @author Harvwy Imama
 * 
 */
public interface UrlConverter {
	
	String convertToShortURL(final long id);
	
	long convertToLongURL(final String url);
	
	
	

}
