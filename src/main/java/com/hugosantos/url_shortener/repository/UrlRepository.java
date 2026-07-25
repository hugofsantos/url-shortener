package com.hugosantos.url_shortener.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.hugosantos.url_shortener.model.Url;

@Repository
public interface UrlRepository extends CassandraRepository<Url, String> {
  
}
