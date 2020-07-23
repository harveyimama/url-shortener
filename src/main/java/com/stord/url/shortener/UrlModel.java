package com.stord.url.shortener;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * Entity for url 
 *
 * @author Harvwy Imama
 * 
 */

@Entity
public class UrlModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(length=2000)
	private String url;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(final Date createTime) {
		this.createTime = createTime;
	}


}
