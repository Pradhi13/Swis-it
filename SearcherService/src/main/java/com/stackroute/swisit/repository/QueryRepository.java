package com.stackroute.swisit.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stackroute.swisit.domain.QueryBean;
import com.stackroute.swisit.domain.SwisitBean;

public interface QueryRepository extends MongoRepository<QueryBean, String>{

}
