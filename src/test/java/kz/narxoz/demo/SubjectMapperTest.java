package kz.narxoz.demo;


import kz.narxoz.demo.dto.StudentDto;
import kz.narxoz.demo.dto.SubjectDto;
import kz.narxoz.demo.mapper.StudentMapper;
import kz.narxoz.demo.mapper.SubjectMapper;
import kz.narxoz.demo.model.Student;
import kz.narxoz.demo.model.Subject;
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

public class SubjectMapperTest {
    @Autowired
    private SubjectMapper subjectMapper;

    @Test
    void convertEntityToDtoTest(){
        Subject subjectEntity=new Subject(1L,"kymbat","kymbat@gmail.com",new ArrayList<>());
        SubjectDto subjectDto =subjectMapper.toDto(subjectEntity);

        Assertions.assertNotNull(subjectDto);
        Assertions.assertNotNull(subjectDto.getId());
        Assertions.assertNotNull(subjectDto.getNameDto());
        Assertions.assertNotNull(subjectDto.getTeacherDto());


        Assertions.assertEquals(subjectEntity.getId(),subjectDto.getId());
        Assertions.assertEquals(subjectEntity.getName(),subjectDto.getNameDto());
        Assertions.assertEquals(subjectEntity.getTeacher(),subjectDto.getTeacherDto());

    }

    @Test
    void convertDtoToEntityTest(){
        SubjectDto subjectDto =new SubjectDto(1L,"kymbat","kymbat@gmail.com");
        Subject subjectEntity=subjectMapper.toEntity(subjectDto);

        Assertions.assertNotNull(subjectEntity);
        Assertions.assertNotNull(subjectEntity.getId());
        Assertions.assertNotNull(subjectEntity.getName());
        Assertions.assertNotNull(subjectEntity.getTeacher());


        Assertions.assertEquals(subjectDto.getId(),subjectEntity.getId());
        Assertions.assertEquals(subjectDto.getNameDto(),subjectEntity.getName());
        Assertions.assertEquals(subjectDto.getTeacherDto(),subjectEntity.getTeacher());


    }
    @Test
    void convertEntityListToDtoList(){
        List<Subject> subjectEntityList=new ArrayList<>();
        subjectEntityList.add(new   Subject(1L,"kymbat","@gmail.com",new ArrayList<>()));
        subjectEntityList.add(new Subject(2L,"symbat","@gmail.com",new ArrayList<>()));
        subjectEntityList.add(new Subject(3L,"zhanna","@gmail.com",new ArrayList<>()));

        List<SubjectDto> subjectDtoList=subjectMapper.toDtoList(subjectEntityList);
        Assertions.assertNotNull(subjectDtoList);
        Assertions.assertNotEquals(0,subjectDtoList.size());

        for(int i=0;i<subjectDtoList.size();i++){
            SubjectDto subjectDto=subjectDtoList.get(i);
            Subject subjectEntity=subjectEntityList.get(i);
            Assertions.assertNotNull(subjectDto);
            Assertions.assertNotNull(subjectDto.getId());
            Assertions.assertNotNull(subjectDto.getNameDto());
            Assertions.assertNotNull(subjectDto.getTeacherDto());


            Assertions.assertEquals(subjectEntity.getId(),subjectDto.getId());
            Assertions.assertEquals(subjectEntity.getName(),subjectDto.getNameDto());
            Assertions.assertEquals(subjectEntity.getTeacher(),subjectDto.getTeacherDto());



        }


    }
}
