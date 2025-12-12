package kz.narxoz.demo.service;

import kz.narxoz.demo.dto.StudentDto;


import java.util.List;

public interface StudentService {
    List<StudentDto> getAll();
    StudentDto getById(Long id);
    StudentDto addStudent(StudentDto studentDto);
    StudentDto updateStudent(Long id,StudentDto studentDto);
    Boolean deleteStudent(Long id);
}
