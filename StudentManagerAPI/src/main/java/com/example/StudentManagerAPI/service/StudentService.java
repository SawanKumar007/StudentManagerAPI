package com.example.StudentManagerAPI.service;

import com.example.StudentManagerAPI.dto.StudentRequest;
import lombok.RequiredArgsConstructor;
import com.example.StudentManagerAPI.model.Student;
import org.springframework.stereotype.Service;
import com.example.StudentManagerAPI.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Student addStudent(StudentRequest request) {
        Student student = new Student(null, request.getName(), request.getAge(), request.getStudentClass(), request.getPhoneNumber());
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentByName(String name) {
        return studentRepository.findByName(name);
    }

    public Student updateStudent(String name, StudentRequest request) {
        return studentRepository.findByName(name)
                .map(student -> {
                    student.setAge(request.getAge());
                    student.setStudentClass(request.getStudentClass());
                    student.setPhoneNumber(request.getPhoneNumber());
                    return studentRepository.save(student);
                }).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public void deleteStudent(String name) {
        studentRepository.findByName(name)
                .ifPresent(studentRepository::delete);
    }
}
