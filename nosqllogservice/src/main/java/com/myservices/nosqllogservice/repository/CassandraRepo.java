package com.myservices.nosqllogservice.repository;

import com.myservices.nosqllogservice.model.Logs;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CassandraRepo extends CassandraRepository<Logs,Integer> {

}
