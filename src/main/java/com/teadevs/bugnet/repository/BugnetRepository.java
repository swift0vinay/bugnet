package com.teadevs.bugnet.repository;

import com.teadevs.bugnet.model.Bug;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BugnetRepository extends MongoRepository<Bug, Long> {

}
