package com.stackroute.swisit.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.swisit.domain.QueryBean;
import com.stackroute.swisit.domain.ResponsiveBean;
import com.stackroute.swisit.domain.SwisitBean;


@Repository
public interface SearchRepository extends MongoRepository<SwisitBean, String>{
}
