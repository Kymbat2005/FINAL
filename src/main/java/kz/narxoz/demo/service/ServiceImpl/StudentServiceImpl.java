package kz.narxoz.demo.service.ServiceImpl;

import kz.narxoz.demo.dto.StudentDto;

import kz.narxoz.demo.mapper.StudentMapper;

import kz.narxoz.demo.model.Student;

import kz.narxoz.demo.repository.StudentRepository;

import kz.narxoz.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl  implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public List<StudentDto> getAll(){
        return studentMapper.toDtoList(studentRepository.findAll());
    }

    @Override
    public StudentDto getById(Long id){
        return studentMapper.toDto(studentRepository.findById(id).orElse(null));
    }

    @Override
    public StudentDto addStudent(StudentDto studentDto){
        Student student=studentRepository.save(studentMapper.toEntity(studentDto));
        return studentMapper.toDto(student);
    }

    @Override
    public StudentDto updateStudent(Long id,StudentDto studentDto){
        Student student=studentRepository.findById(id).orElse(null);
        Student studentEntity=studentMapper.toEntity(studentDto);

        student.setId(studentEntity.getId());
        student.setName(studentEntity.getName());
        student.setLastname(studentEntity.getLastname());
        student.setEmail(studentEntity.getEmail());
        return studentMapper.toDto(studentRepository.save(student));

    }
    @Override
    public Boolean deleteStudent(Long id){
        studentRepository.deleteById(id);
        Student student = studentRepository.findById(id).orElse(null);
        if(Objects.isNull(student)){
            return true;
        }
        return false;

    }
}
