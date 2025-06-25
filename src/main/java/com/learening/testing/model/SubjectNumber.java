package com.learening.testing.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class SubjectNumber {
    private String id;
    private String subjectCode; // New field to store the code of the subject
    private int examMarks; // New field to store the exam marks for the subject
    private String studentId;// New field to store the ID of the student
    // New field to store the email of the student

}
