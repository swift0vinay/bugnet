package com.teadevs.bugnet.repository;

import com.teadevs.bugnet.model.bug.Bug;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BugnetRepository extends MongoRepository<Bug, Long> {

    public List<Bug> findByAssignedToEquals(String id);


}