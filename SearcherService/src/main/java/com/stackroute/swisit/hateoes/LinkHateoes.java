package com.stackroute.swisit.hateoes;

import java.util.List;

import com.stackroute.swisit.domain.QueryBean;
import com.stackroute.swisit.domain.SwisitBean;


public interface LinkHateoes {

	public List<SwisitBean> getalllinks(List<SwisitBean> all);
	public List<QueryBean> getallquery(List<QueryBean> all);
}
