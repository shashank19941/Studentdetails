package com.learening.testing.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "students") 
public class StudentDetails {
    private String id;
    private String studentId;
    private String name;
    private String email;
    private String course;
    private String address;
    private String phoneNumber;
    private String dateOfBirth;
    private String gender;
    private String enrollmentDate;
    private String graduationDate;
    private String status; // e.g., "active", "graduated", "dropped out"
    private String notes; // Additional notes or comments about the student
    private String profilePictureUrl; // URL to the student's profile picture
    private String emergencyContactName; // Name of the emergency contact
    private String emergencyContactPhone; // Phone number of the emergency contact
    private String emergencyContactEmail; // Email of the emergency contact
    private String createdAt; // Timestamp of when the student was created
    private String updatedAt; // Timestamp of the last update to the student's details      

}
