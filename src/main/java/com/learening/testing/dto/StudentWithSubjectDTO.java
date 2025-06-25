package com.learening.testing.dto;



import java.util.List;

import lombok.Data;

@Data
public class StudentWithSubjectDTO {
    private String studentId;
    private String name;
    private String email;
    private List<SubjectMarkDTO> subjects;
    private double percentage;
    private String division;

    @Data
    public static class SubjectMarkDTO {
        private String subjectCode;
        private String subjectName; 
        private int examMarks;
       
    }
}
