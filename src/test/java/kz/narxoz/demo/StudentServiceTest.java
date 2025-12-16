package kz.narxoz.demo;


import jakarta.transaction.Transactional;
import kz.narxoz.demo.dto.StudentDto;

import kz.narxoz.demo.service.StudentService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
@ActiveProfiles("test")
@Transactional


public class StudentServiceTest {
    @Autowired
    private StudentService studentService;
    private StudentDto createOne(){
        StudentDto studentDto = new StudentDto();
        studentDto.setNameDto("test1");
        studentDto.setLastnameDto("desc1");
        studentDto.setEmailDto("desc1");
        return studentService.addStudent(studentDto);
    }

    @Test
    void getAllTest(){
        createOne();
        List<StudentDto> studentDto = studentService.getAll();

        Assertions.assertNotNull(studentDto);
        Assertions.assertNotEquals(0,studentDto.size());

        for(int i = 0; i<studentDto.size();i++){
            StudentDto dto = studentDto.get(i);
            Assertions.assertNotNull(dto);
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getNameDto());
            Assertions.assertNotNull(dto.getLastnameDto());
            Assertions.assertNotNull(dto.getEmailDto());
        }
    }



    @Test
    void getByIdTest(){
        createOne();
        Random random =new Random();
        int randomIndex=random.nextInt(studentService.getAll().size());
        Long someIndex=studentService.getAll().get(randomIndex).getId();
        StudentDto studentDto=studentService.getById(someIndex);

        Assertions.assertNotNull(studentDto);
        Assertions.assertNotNull(studentDto.getId());
        Assertions.assertNotNull(studentDto.getNameDto());
        Assertions.assertNotNull(studentDto.getLastnameDto());
        Assertions.assertNotNull(studentDto.getEmailDto());

        StudentDto check=studentService.getById(-1L);
        Assertions.assertNull(check);

    }
    @Test
    void addTest(){
        StudentDto studentDto=new StudentDto();
        studentDto.setNameDto("kuygh");
        studentDto.setLastnameDto("ghjk");
        studentDto.setEmailDto("ghjk");

        StudentDto add=studentService.addStudent(studentDto);
        Assertions.assertNotNull(add);
        Assertions.assertNotNull(add.getId());
        Assertions.assertNotNull(add.getNameDto());
        Assertions.assertNotNull(add.getLastnameDto());
        Assertions.assertNotNull(add.getEmailDto());

        StudentDto added=studentService.getById(add.getId());



        Assertions.assertNotNull(added);
        Assertions.assertNotNull(added.getId());
        Assertions.assertNotNull(added.getNameDto());
        Assertions.assertNotNull(added.getLastnameDto());
        Assertions.assertNotNull(added.getEmailDto());

        Assertions.assertEquals(add.getId(),added.getId());
        Assertions.assertEquals(add.getNameDto(),added.getNameDto());
        Assertions.assertEquals(add.getLastnameDto(),added.getLastnameDto());
        Assertions.assertEquals(add.getEmailDto(),added.getEmailDto());





    }
    @Test
    void updateTest(){
        createOne();
        Random random =new Random();
        int randomIndex=random.nextInt(studentService.getAll().size());
        Long someIndex=studentService.getAll().get(randomIndex).getId();
        StudentDto newStudent=StudentDto.builder().id(someIndex).nameDto("ghjk").lastnameDto("ghjk").emailDto("@gmail.com").build();
        StudentDto update=studentService.updateStudent(newStudent.getId(),newStudent);

        Assertions.assertNotNull(update);
        Assertions.assertNotNull(update.getId());
        Assertions.assertNotNull(update.getNameDto());
        Assertions.assertNotNull(update.getLastnameDto());
        Assertions.assertNotNull(update.getEmailDto());

        Assertions.assertEquals(newStudent.getId(),update.getId());
        Assertions.assertEquals(newStudent.getNameDto(),update.getNameDto());
        Assertions.assertEquals(newStudent.getLastnameDto(),update.getLastnameDto());
        Assertions.assertEquals(newStudent.getEmailDto(),update.getEmailDto());

        StudentDto updated=studentService.getById(someIndex);

        Assertions.assertNotNull(updated);
        Assertions.assertNotNull(updated.getId());
        Assertions.assertNotNull(updated.getNameDto());
        Assertions.assertNotNull(updated.getLastnameDto());
        Assertions.assertNotNull(updated.getEmailDto());

        Assertions.assertEquals(update.getId(),updated.getId());
        Assertions.assertEquals(update.getNameDto(),updated.getNameDto());
        Assertions.assertEquals(update.getLastnameDto(),updated.getLastnameDto());
        Assertions.assertEquals(update.getEmailDto(),updated.getEmailDto());



    }
    @Test
    void deleteTest(){
        createOne();
        Random random =new Random();
        int randomIndex=random.nextInt(studentService.getAll().size());
        Long someIndex=studentService.getAll().get(randomIndex).getId();
        Assertions.assertTrue(studentService.deleteStudent(someIndex));
        StudentDto deleted=studentService.getById(someIndex);

    }

}
