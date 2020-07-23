package com.stord.url.shortener;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Entity for url
 *
 * @author Harvwy Imama
 * 
 */
@Service
public interface UrlEntity extends JpaRepository<UrlModel,Long> {
	

}
