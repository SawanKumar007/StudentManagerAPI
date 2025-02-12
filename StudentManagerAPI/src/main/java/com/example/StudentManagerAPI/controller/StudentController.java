package com.example.StudentManagerAPI.controller;

import com.example.StudentManagerAPI.dto.StudentRequest;
import lombok.RequiredArgsConstructor;
import com.example.StudentManagerAPI.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.StudentManagerAPI.service.StudentService;

import javax.validation.Valid;
import java.util.List;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@Tag(name = "Student API", description = "Operations related to student management")
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    @Operation(summary = "Add a new student", description = "Creates and stores a new student record")
    public ResponseEntity<Student> addStudent(@RequestBody @Valid StudentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.addStudent(request));
    }

    @GetMapping
    @Operation(summary = "Get all students", description = "Retrieves a list of all students")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{name}")
    @Operation(summary = "Get student by name", description = "Searches for a student by name")
    public ResponseEntity<Student> getStudentByName(@PathVariable String name) {

        return studentService.getStudentByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{name}")
    @Operation(summary = "Update student details", description = "Updates the details of a student based on the provided name")    public ResponseEntity<Student> updateStudent(@PathVariable String name, @RequestBody @Valid StudentRequest request) {
        return ResponseEntity.ok(studentService.updateStudent(name, request));
    }

    @DeleteMapping("/{name}")
    @Operation(summary = "Delete a student", description = "Removes a student record based on the provided name")
    public ResponseEntity<Void> deleteStudent(@PathVariable String name) {
        studentService.deleteStudent(name);
        return ResponseEntity.noContent().build();
    }
}
