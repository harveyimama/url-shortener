package com.stord.url.shortener;

import org.springframework.stereotype.Component;

/**
 *Implementation for the Base 62 converter
 *@author Harvey Imama
 * 
 */
@Component
class Base62Converter implements UrlConverter {

	/**
	 *converts id from database to base 62 string
	 *
	 * @params ID from database
	 * @return shortURL
	 */

	@Override
	public String convertToShortURL(final long id) {
		
		try {
			
			String[] characterArray = characterSet.split("");
			String url = "";
			long remainder = 0;
			long coefficeint = id;

			while (coefficeint > 0) {
				remainder = coefficeint % 62;
				url = url.concat(characterArray[(int) remainder]);
				coefficeint = coefficeint / 62;
			}

			return new StringBuilder(url).reverse().toString();
			
		} catch (Exception e) {
			throw new ConversionError("Failure when converting Id to URL ", e);
		}

	}

	/**
	 *converts String form user to id 
	 *
	 * @params ID from database
	 * @return shortURL
	 */
	@Override
	public long convertToLongURL(final String url) {
		
		try {
			
			final String[] urlList = url.split("");
			int len = urlList.length - 1;
			long id = 0;

			for (String key : urlList) {
				
				id = id + (int) (characterSet.indexOf(key) * Math.pow(62, len));

				len--;
			}

			return id;
			
		} catch (Exception e) {
			throw new ConversionError("Failure when converting URL to id ", e);
		}

	}
	
	private static final String characterSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	
}