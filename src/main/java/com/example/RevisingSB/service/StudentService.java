package com.example.RevisingSB.service;

import com.example.RevisingSB.dto.StudentDTO;
import com.example.RevisingSB.dto.StudentRequestDTO;
import com.example.RevisingSB.entity.Student;
import com.example.RevisingSB.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public List<StudentDTO> getAllStudents(){
        List<Student> students = studentRepository.findAll();
        List<StudentDTO> list = students.stream().map(s -> modelMapper.map(s,StudentDTO.class)).toList();
        return list;
    }

    public StudentDTO getStudentById(Long id){
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student with id "+ id + " is not present"));
        StudentDTO st = modelMapper.map(student,StudentDTO.class);
        return st;
    }

    public StudentDTO createStudent(StudentRequestDTO studentRequestDTO) {
        Student student = modelMapper.map(studentRequestDTO,Student.class);
        Student saveStudent = studentRepository.save(student);
        return modelMapper.map(saveStudent, StudentDTO.class);
    }

    public void deleteStudent(Long id) {
        if(!studentRepository.existsById(id)){
            throw new IllegalArgumentException("Student does not exist with id: "+id);
        }
        studentRepository.deleteById(id);
    }

    public StudentDTO updateStudent(Long id, StudentRequestDTO studentRequestDTO) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student with id "+ id + " is not present"));
        modelMapper.map(studentRequestDTO,student);
        studentRepository.save(student);
        return modelMapper.map(student,StudentDTO.class);
    }

    public StudentDTO partialUpdate(Long id, Map<String, Object> updates) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student with id "+ id + " is not present"));
        updates.forEach((field,value)->{
            switch (field){
                case "name":student.setName((String) value);
                break;
                case "email":student.setEmail((String) value);
                break;
                default: throw new IllegalArgumentException("No Such field is present in DB");
            }
        });

        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent,StudentDTO.class);
    }
}
