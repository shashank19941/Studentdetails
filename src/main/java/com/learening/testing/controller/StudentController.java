package com.learening.testing.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learening.testing.dto.StudentWithSubjectDTO;
import com.learening.testing.model.StudentDetails;
import com.learening.testing.repository.StudentRepository;
import com.learening.testing.service.StudentService;
import com.learening.testing.util.SessionUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/student")
@Tag(name = "Student APIs")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

     @Autowired
    private StudentService studentService;


    private boolean denyIfNotLoggedIn(HttpSession session) {
        return !SessionUtil.isAuthenticated(session);
    }

    @Operation(summary = "Add a new student")
    @PostMapping
    public Object addUser(@RequestBody StudentDetails users, HttpSession session) {
        if (denyIfNotLoggedIn(session)) return "Unauthorized access - please login";
        return studentRepository.save(users);
    }

    @Operation(summary = "Get all students")
    @GetMapping
    public Object getAllUsers(HttpSession session) {
        if (denyIfNotLoggedIn(session)) return "Unauthorized access - please login";
        return studentRepository.findAll();
    }

    @Operation(summary = "Get student by ID")
    @GetMapping("/{id}")
    public Object getUserById(@PathVariable String id, HttpSession session) {
        if (denyIfNotLoggedIn(session)) return "Unauthorized access - please login";
        return studentRepository.findById(id).orElse(null);
    }

    @Operation(summary = "Delete student by ID")
    @DeleteMapping("/{id}")
    public Object deleteUserById(@PathVariable String id, HttpSession session) {
        if (denyIfNotLoggedIn(session)) return "Unauthorized access - please login";
        studentRepository.deleteById(id);
        return "Student deleted successfully";
    }
    @Operation(summary = "Update student details")
    @PostMapping("/{id}")
    public Object updateUserById(@PathVariable String id, @RequestBody StudentDetails users, HttpSession session) {
        if (denyIfNotLoggedIn(session)) return "Unauthorized access - please login";
        if (!studentRepository.existsById(id)) {
            return "Student not found";
        }
        users.setId(id);
        return studentRepository.save(users);
    }
    
    @Operation(summary = "Get student by name")
    @GetMapping("/name/{name}")
    public Object getUserByName(@PathVariable String name, HttpSession session) {
        if (denyIfNotLoggedIn(session)) return "Unauthorized access - please login";
        return studentRepository.findByName(name);
    }

    @Operation(summary = "Get student by student ID")
    @GetMapping("/studentId/{studentId}")
    public Object getUserByStudentId(@PathVariable String studentId, HttpSession session) {
        if (denyIfNotLoggedIn(session)) return "Unauthorized access - please login";
        return studentRepository.findByStudentId(studentId);
    }

    @Operation(summary = "Get student with subjects by student ID") 
    @GetMapping("/{studentId}/with-subjects")
    public StudentWithSubjectDTO getStudentWithSubjects(@PathVariable String studentId) {
        return studentService.getStudentWithSubjects(studentId);
    }
    }
