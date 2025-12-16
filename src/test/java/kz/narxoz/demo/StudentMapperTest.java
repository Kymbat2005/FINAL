package kz.narxoz.demo;


import kz.narxoz.demo.dto.StudentDto;
import kz.narxoz.demo.mapper.StudentMapper;
import kz.narxoz.demo.model.Student;
import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")

public class StudentMapperTest {
    @Autowired
    private StudentMapper studentMapper;

    @Test
    void convertEntityToDtoTest(){
        Student studentEntity=new Student(1L,"kymbat","Zhenisbekova","kymbat@gmail.com",new ArrayList<>());
        StudentDto studentDto =studentMapper.toDto(studentEntity);

        Assertions.assertNotNull(studentDto);
        Assertions.assertNotNull(studentDto.getId());
        Assertions.assertNotNull(studentDto.getNameDto());
        Assertions.assertNotNull(studentDto.getLastnameDto());
        Assertions.assertNotNull(studentDto.getEmailDto());

        Assertions.assertEquals(studentEntity.getId(),studentDto.getId());
        Assertions.assertEquals(studentEntity.getName(),studentDto.getNameDto());
        Assertions.assertEquals(studentEntity.getLastname(),studentDto.getLastnameDto());
        Assertions.assertEquals(studentEntity.getEmail(),studentDto.getEmailDto());
    }

    @Test
    void convertDtoToEntityTest(){
        StudentDto studentDto =new StudentDto(1L,"kymbat","Zhenisbekova","kymbat@gmail.com",new ArrayList<>());
        Student studentEntity=studentMapper.toEntity(studentDto);

        Assertions.assertNotNull(studentEntity);
        Assertions.assertNotNull(studentEntity.getId());
        Assertions.assertNotNull(studentEntity.getName());
        Assertions.assertNotNull(studentEntity.getLastname());
        Assertions.assertNotNull(studentEntity.getEmail());

        Assertions.assertEquals(studentDto.getId(),studentEntity.getId());
        Assertions.assertEquals(studentDto.getNameDto(),studentEntity.getName());
        Assertions.assertEquals(studentDto.getLastnameDto(),studentEntity.getLastname());
        Assertions.assertEquals(studentDto.getEmailDto(),studentEntity.getEmail());

    }
    @Test
    void convertEntityListToDtoList(){
        List<Student> studentEntityList=new ArrayList<>();
        studentEntityList.add(new   Student(1L,"kymbat","Zhenisbekova","@gmail.com",new ArrayList<>()));
        studentEntityList.add(new Student(2L,"symbat","symbat","@gmail.com",new ArrayList<>()));
        studentEntityList.add(new Student(3L,"zhanna","zhanna","@gmail.com",new ArrayList<>()));

        List<StudentDto> studentDtoList=studentMapper.toDtoList(studentEntityList);
        Assertions.assertNotNull(studentDtoList);
        Assertions.assertNotEquals(0,studentDtoList.size());

        for(int i=0;i<studentDtoList.size();i++){
            StudentDto studentDto=studentDtoList.get(i);
            Student studentEntity=studentEntityList.get(i);
            Assertions.assertNotNull(studentDto);
            Assertions.assertNotNull(studentDto.getId());
            Assertions.assertNotNull(studentDto.getNameDto());
            Assertions.assertNotNull(studentDto.getLastnameDto());
            Assertions.assertNotNull(studentDto.getEmailDto());

            Assertions.assertEquals(studentEntity.getId(),studentDto.getId());
            Assertions.assertEquals(studentEntity.getName(),studentDto.getNameDto());
            Assertions.assertEquals(studentEntity.getLastname(),studentDto.getLastnameDto());
            Assertions.assertEquals(studentEntity.getEmail(),studentDto.getEmailDto());


        }


    }
}
