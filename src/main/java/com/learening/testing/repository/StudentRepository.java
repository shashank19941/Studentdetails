package com.learening.testing.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.learening.testing.model.StudentDetails;

public interface StudentRepository extends MongoRepository<StudentDetails, String> {
    // Custom query method to find a student by email
    StudentDetails findByEmail(String email);
    
    // Custom query method to find a student by name
    StudentDetails findByName(String name);
     StudentDetails findByStudentId(String studentId);
    
    // Additional custom query methods can be defined here as needed

}
