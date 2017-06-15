package com.stackroute.swisit.domain;


import java.util.Map;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Component
@JsonIgnoreProperties(ignoreUnknown=true)
public class SwisitBean extends ResourceSupport implements org.apache.kafka.common.serialization.Serializer{
	
	@JsonProperty("query")
	String query;
	@JsonProperty("link")
	String url;
	@JsonProperty("title")
	String title;
	@JsonProperty("snippet")
	String description;
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void configure(Map arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public byte[] serialize(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
