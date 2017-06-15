package com.stackroute.swisit.searchservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.swisit.domain.QueryBean;
import com.stackroute.swisit.domain.ResponsiveBean;
import com.stackroute.swisit.domain.SwisitBean;
import com.stackroute.swisit.exception.SearcherServiceException;
import com.stackroute.swisit.messageservice.MessageService;
import com.stackroute.swisit.repository.QueryRepository;
import com.stackroute.swisit.repository.SearchRepository;


@Service
public class SearchService implements SearchServiceInterface {
	
	@Value("${url}")
	String url;
	
	@Autowired
	private SearchRepository searchRepository;
	
	@Autowired
	private QueryRepository queryRepository;
	
	@Autowired
	MessageService kafkaconfig;

	ResponsiveBean responsiveBean = new ResponsiveBean();

	QueryBean q = new QueryBean();
	String domain="";
	String concept="";
	List<LinkedHashMap<String,String>> engineid = new ArrayList<LinkedHashMap<String,String>>();
	String url1;
	String key = "engineid";
	
	/* Insert Search result for the query into swisitBean collection */
	public Iterable<SwisitBean> save() throws SearcherServiceException, JsonProcessingException{
		
		try
		{
		/* Get the data from the queryBean collection */
			for(QueryBean qb: queryRepository.findAll())
			{
				domain = qb.getExactTerm();
				System.out.println(domain);
				concept = qb.getQuery();
				System.out.println(concept);
				String query = domain+" "+concept;	
				engineid = qb.getEngineId();
			
				for(Map<String, String> map : engineid){
					url1=map.get(key);
					break;
				}
			 url1 = query+url1;
			

			}
		
		String finalUrl = url+url1;
		
		RestTemplate restTemplate = new RestTemplate();
		responsiveBean = restTemplate.getForObject(finalUrl,ResponsiveBean.class);
		if(responsiveBean==null)
			{
			throw new SearcherServiceException("Incorrect Url");
			}
		
		List<SwisitBean> l=new ArrayList<SwisitBean>();
		for(SwisitBean b:responsiveBean.getS())
			{
			SwisitBean a=new SwisitBean();
			a.setQuery(responsiveBean.getQueries());
			a.setUrl(b.getUrl());
			a.setTitle(b.getTitle());
			a.setDescription(b.getDescription());
			searchRepository.save(a);
			l.add(a);
			}
		ObjectMapper om=new ObjectMapper();
		String s=om.writeValueAsString(l);
		System.out.println("publishing in service"+s);
		kafkaconfig.publishmessage("testcontrol", s);
		
		}
		catch(SearcherServiceException e)
		{
			System.out.println("Exception"+e.getMessage());
		}
		return searchRepository.findAll();
	
		
	}


/* get all datas from the swisitBean database */
	public Iterable<SwisitBean> getAll() throws SearcherServiceException{
	try
	{
		if(searchRepository.findAll()==null)
		{
		throw new SearcherServiceException("No Data is Found");
		}
	}
	catch(SearcherServiceException e)
	{
		System.out.println("Exception"+e.getMessage());
	}
	return searchRepository.findAll();
	}

/* Created Search Job in the database */
	public Iterable<QueryBean> saveQuery(QueryBean queryBean) throws SearcherServiceException {
		
	
		q.setExactTerm(queryBean.getExactTerm());
		q.setQuery(queryBean.getQuery());
		ObjectMapper mapper = new ObjectMapper();
		try {
			File file = new File("./src/main/resources/common/googleEngine.json");
			List<LinkedHashMap<String,String>> list= (List<LinkedHashMap<String,String>>) mapper.readValue(file, ArrayList.class);
			System.out.println(list);
			q.setEngineId(list);
		}
		
		catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		q.setResults(queryBean.getResults());
		q.setSitesearch(queryBean.getSitesearch());
		queryRepository.save(q);
		
		
		
		return queryRepository.findAll();
	}
	
	/* Get All data from QueryBean Collection */
	public Iterable<QueryBean> getQuery() {
		
		try
		{
			if(queryRepository.findAll()==null)
			{
			throw new SearcherServiceException("No Data is Found");
			}
		}
		catch(SearcherServiceException e)
		{
			System.out.println("Exception"+e.getMessage());
		}
		return queryRepository.findAll();
	}
	


}
