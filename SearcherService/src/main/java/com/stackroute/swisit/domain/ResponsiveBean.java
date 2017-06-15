package com.stackroute.swisit.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@Document
public class ResponsiveBean {
	
	
	String queries;
	
	public String getQueries() {
		return queries;
	}

	public void setQueries(String queries) {
		this.queries = queries;
	}

	@JsonProperty("queries")
    public String getNestedObject(Map<String, Object> queries)
    {
        
        ArrayList<Object> al = (ArrayList<Object>) queries.get("request");
        Map<Object,Object> map = (Map<Object,Object>)al.get(0);
        setQueries((String) map.get("searchTerms"));
        return (String) map.get("searchTerms");
    }

	@JsonProperty("items")
	SwisitBean[] s;

	public SwisitBean[] getS() {
		return s;
	}

	public void setS(SwisitBean[] s) {
		this.s = s;
	}
	

	@Override
	public String toString() {
		return "Responsive [s=" + Arrays.toString(s) + "]";
	}
	

}
