package com.stord.url.shortener;

import java.net.URI;
import java.net.URL;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller class responsible for routing 
 *
 * @author Harvey Imama
 * 
 */

@Controller
public class UrlController {
	@Autowired
	UrlEntity urlEntity;
	@Autowired
	Base62Converter base62converter;
	
/**
 * Receives POST requests from the customer containing long URL
 *
 * @params URL String,Model
 * @return return page
 */
	@PostMapping(path = "/shortenURL", produces = MediaType.APPLICATION_JSON_VALUE)
	public String shortenURL(final String url, Model model, 
		    HttpServletRequest request) {

		try {
			if(testURLValidity(url))
			{
			String shortURL = base62converter.convertToShortURL(persistURLToModel(url));
			
			if(checkReturnURL(url))
			model.addAttribute(RETURN_PARAMETER, request.getRequestURL().toString().replace("shortenURL", "L/"+shortURL));
			else
				model.addAttribute(ERROR_PARAMETER, "Sorry, URL could not be processed");
			
			}else
			 model.addAttribute(ERROR_PARAMETER, "Sorry, URL cannot be reached");
			
		} catch (Exception e) {
			model.addAttribute(ERROR_PARAMETER, e.getMessage());
		}

		return "processor";
	}
	
	/**
	 * Receives GET requests from the customer containing short URL
	 *
	 * @params URL String,Model
	 * @return redirect entity
	 */
	@GetMapping(path = "/L/{url}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> expandAndRouteURL(@PathVariable final String url, Model model) {

		try {
			
			String longURL = getURLFromModel(base62converter.convertToLongURL(url));
			return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(longURL)).build();

		} catch (Exception e) {

			return null;
		}
	}
	
	/**
	 * routes to the home page
	 *
	 * 
	 * @return view name
	 */
	
	@GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public String home( Model model) {

			return "processor";
		
	}
	
		
	
	/**
	 *Sends URL to back-end for persistence
	 *
	 * @params URL String
	 * @return auto incremented ID from database
	 */
	private long persistURLToModel(final String url) {

		try {

			UrlModel model = new UrlModel();
			model.setUrl(url);
			model.setCreateTime(new Date());
			return urlEntity.save(model).getId();

		} catch (Exception e) {
			throw new DatabaseError("Database Persistence Error ", e);
		}

	}
	
	/**
	 *Gets previously stored URLs from database
	 *
	 * @params ID from database
	 * @return long URL form database
	 */

	private String getURLFromModel(final long id) {

		try {

			return urlEntity.findById(id).get().getUrl();

		} catch (Exception e) {
			throw new DatabaseError("Database Failed to retrive URL ", e);
		}
	}
	
	/**
	 *Tests the validity of the URL passed
	 *
	 * @params url from user
	 * @return true or false
	 */

	
	private boolean testURLValidity(String url)
	{
		try {
		  new URL(url).toURI();
		  return true;
			
		} catch (Exception e) {
			return false;
		} 
	}
	
	
	/**
	 *Tests to see if we get a valid url
	 *
	 * @params url from user
	 * @return true or false
	 */
	
	private boolean checkReturnURL(String url)
	{
		if("".equals(url) || url ==null)
			return false;
		else
			return true;
	}

	private final static String RETURN_PARAMETER = "url";
	private final static String ERROR_PARAMETER = "error";
	 
	

}

//UI
//validations

