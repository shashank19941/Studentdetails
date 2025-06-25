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

import com.learening.testing.model.Subject;
import com.learening.testing.repository.SubjectRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/subject")
@Tag(name = "Subject APIs")
public class SubjectController {
    @Autowired
    private SubjectRepository subjectRepository;


    @Operation(summary = "Add a new subject")
    @PostMapping
    public Subject createSubject(@RequestBody Subject subject) {
        return subjectRepository.save(subject);
        }

    @Operation(summary = "Get subject by ID")
    @GetMapping("/{id}")
    public Subject getSubjectById(@PathVariable String id) {
        return subjectRepository.findById(id).orElse(null);
    }

    @Operation(summary = "Get all subjects")
    @GetMapping
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Operation(summary = "Delete subject by ID")
    @DeleteMapping("/{id}")
    public String deleteSubjectById(@PathVariable String id) {
        subjectRepository.deleteById(id);
        return "Subject deleted successfully";
    }

    @Operation(summary = "Get subjects by name")
    @GetMapping("/search/{name}")
    public List<Subject> getSubjectsByName(@PathVariable String name) {
        return subjectRepository.findByName(name);
    }

    @Operation(summary = "Update subject by ID")
    @PutMapping("/{id}")
    public Subject updateSubject(@PathVariable String id, @RequestBody Subject subject) {
        Subject existingSubject = subjectRepository.findById(id).orElse(null);
        if (existingSubject != null) {
            existingSubject.setName(subject.getName());
            existingSubject.setCode(subject.getCode());
            existingSubject.setDescription(subject.getDescription());
            return subjectRepository.save(existingSubject);
        }
            return null; // or throw an exception if preferred
        }       
    }
