package com.learening.testing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learening.testing.model.SubjectNumber;
import com.learening.testing.repository.SubjectNumberRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/subjectNumber")
@Tag(name = "Subject Number APIs")
public class SubjectNumberController {
    @Autowired
    private SubjectNumberRepository subjectNumberRepository;

    @Operation(summary = "Add a new subject number")
    @PostMapping
    public SubjectNumber createSubjectNumber(@RequestBody SubjectNumber subjectNumber) {
        return subjectNumberRepository.save(subjectNumber);
    }
    @Operation(summary = "Get subject number by ID")
    @GetMapping("/{id}")
    public SubjectNumber getSubjectNumberById(@PathVariable String id) {
        return subjectNumberRepository.findById(id).orElse(null);
    }   
    @Operation(summary = "Get all subject numbers")
    @GetMapping
    public List<SubjectNumber> getAllSubjectNumbers() {
        return subjectNumberRepository.findAll();
    }
    @Operation(summary = "Delete subject number by ID")         
    @DeleteMapping("/{id}")
    public String deleteSubjectNumberById(@PathVariable String id) {
        subjectNumberRepository.deleteById(id);
        return "Subject number deleted successfully";
    }
    @Operation(summary = "Get subject numbers by student ID")
    @GetMapping("/search/{studentId}")
    public List<SubjectNumber> getSubjectNumbersByStudentId(@PathVariable String studentId) {
        return subjectNumberRepository.findByStudentId(studentId);
    }           
    @Operation(summary = "Get subject numbers by subject code")
    @GetMapping("/search/subjectCode/{subjectCode}")
    public List<SubjectNumber> getSubjectNumbersBySubjectCode(@PathVariable String subjectCode) {
        return subjectNumberRepository.findBySubjectCode(subjectCode);
    }
    @Operation(summary = "Update subject number by ID")         
    @PutMapping("/{id}")
    public SubjectNumber updateSubjectNumber(@PathVariable String id, @RequestBody SubjectNumber subjectNumber) {
        if (!subjectNumberRepository.existsById(id)) {
            return null; // or throw an exception
        }
        subjectNumber.setId(id);
        return subjectNumberRepository.save(subjectNumber);
    }
}                           
