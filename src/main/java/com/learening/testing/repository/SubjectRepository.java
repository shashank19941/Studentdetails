package com.learening.testing.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.learening.testing.model.Subject;

public interface SubjectRepository extends MongoRepository<Subject, String> {
    
   List<Subject> findByName(String name);

   Subject findByCode(String subjectCode);
 

}
