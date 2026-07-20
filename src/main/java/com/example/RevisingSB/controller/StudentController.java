package com.example.RevisingSB.controller;

import com.example.RevisingSB.dto.StudentDTO;
import com.example.RevisingSB.dto.StudentRequestDTO;
import com.example.RevisingSB.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;
    @GetMapping("/list")
    public ResponseEntity<List<StudentDTO>> getAllStudents(){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
    }
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody @Valid StudentRequestDTO studentRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(studentRequestDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id,@RequestBody @Valid  StudentRequestDTO studentRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.updateStudent(id,studentRequestDTO));
    }

    @PatchMapping("partialUpdate/{id}")
    public ResponseEntity<StudentDTO> partialUpdate(@PathVariable Long id, @RequestBody @Valid Map<String, Object> updates){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.partialUpdate(id,updates));
    }

}
