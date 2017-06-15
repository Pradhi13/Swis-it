package com.stackroute.swisit.searchservice;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stackroute.swisit.domain.ResponsiveBean;
import com.stackroute.swisit.domain.SwisitBean;
import com.stackroute.swisit.exception.SearcherServiceException;


public interface SearchServiceInterface {
	
	public Iterable<SwisitBean> save() throws SearcherServiceException, JsonProcessingException;

}
