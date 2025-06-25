package com.learening.testing.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.learening.testing.model.SubjectNumber;

public interface SubjectNumberRepository extends MongoRepository<SubjectNumber, String> {       

    List<SubjectNumber> findByStudentId(String studentId);
    List<SubjectNumber> findBySubjectCode(String subjectCode);
}
