package com.learening.testing.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learening.testing.dto.StudentWithSubjectDTO;
import com.learening.testing.model.StudentDetails;
import com.learening.testing.model.Subject;
import com.learening.testing.model.SubjectNumber;
import com.learening.testing.repository.StudentRepository;
import com.learening.testing.repository.SubjectNumberRepository;
import com.learening.testing.repository.SubjectRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SubjectNumberRepository subjectNumberRepository;

    public StudentWithSubjectDTO getStudentWithSubjects(String studentId) {
        StudentDetails student = studentRepository.findByStudentId(studentId);
        List<SubjectNumber> subjectNumbers = subjectNumberRepository.findByStudentId(studentId);

        StudentWithSubjectDTO dto = new StudentWithSubjectDTO();
        dto.setStudentId(student.getStudentId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setSubjects(subjectNumbers.stream().map(sn -> {
            StudentWithSubjectDTO.SubjectMarkDTO sm = new StudentWithSubjectDTO.SubjectMarkDTO();
            sm.setSubjectCode(sn.getSubjectCode());
            sm.setExamMarks(sn.getExamMarks());
             Subject subject = subjectRepository.findByCode(sn.getSubjectCode());
            sm.setSubjectName(subject != null ? subject.getName() : null);
            
            return sm;
        }).collect(Collectors.toList()));
int totalMarks = subjectNumbers.stream().mapToInt(SubjectNumber::getExamMarks).sum();
    int subjectCount = subjectNumbers.size();
    double percentage = subjectCount > 0 ? (double) totalMarks / subjectCount : 0.0;
    dto.setPercentage(percentage);

    // Determine division
    String division;
    if (percentage >= 90) {
        division = "First with Distinction";
    } else if (percentage >= 60) {
        division = "First";
    } else if (percentage >= 50) {
        division = "Second";
    } else if (percentage >= 40) {
        division = "Third";
    } else {
        division = "Failed";
    }
    dto.setDivision(division);

    return dto;
}
}
