package kz.narxoz.demo;


import jakarta.transaction.Transactional;
import kz.narxoz.demo.dto.SubjectDto;
import kz.narxoz.demo.service.SubjectService;
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


public class SubjectServiceTest {
    @Autowired
    private SubjectService subjectService;
    private SubjectDto createOne(){
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setNameDto("test1");
        subjectDto.setTeacherDto("desc1");
        return subjectService.addSubject(subjectDto);
    }

    @Test
    void getAllTest(){
        createOne();
        List<SubjectDto> subjectDto = subjectService.getAll();

        Assertions.assertNotNull(subjectDto);
        Assertions.assertNotEquals(0,subjectDto.size());

        for(int i = 0; i<subjectDto.size();i++){
            SubjectDto dto = subjectDto.get(i);
            Assertions.assertNotNull(dto);
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getNameDto());
            Assertions.assertNotNull(dto.getTeacherDto());
        }
    }




    @Test
    void getByIdTest(){
        createOne();
        Random random =new Random();
        int randomIndex=random.nextInt(subjectService.getAll().size());
        Long someIndex=subjectService.getAll().get(randomIndex).getId();
        SubjectDto subjectDto=subjectService.getById(someIndex);

        Assertions.assertNotNull(subjectDto);
        Assertions.assertNotNull(subjectDto.getId());
        Assertions.assertNotNull(subjectDto.getNameDto());
        Assertions.assertNotNull(subjectDto.getTeacherDto());

        SubjectDto check=subjectService.getById(-1L);
        Assertions.assertNull(check);

    }
    @Test
    void addTest(){
        SubjectDto subjectDto=new SubjectDto();
        subjectDto.setNameDto("kuygh");
        subjectDto.setTeacherDto("ghjk");

        SubjectDto add=subjectService.addSubject(subjectDto);
        Assertions.assertNotNull(add);
        Assertions.assertNotNull(add.getId());
        Assertions.assertNotNull(add.getNameDto());
        Assertions.assertNotNull(add.getTeacherDto());

        SubjectDto added=subjectService.getById(add.getId());



        Assertions.assertNotNull(added);
        Assertions.assertNotNull(added.getId());
        Assertions.assertNotNull(added.getNameDto());
        Assertions.assertNotNull(added.getTeacherDto());

        Assertions.assertEquals(add.getId(),added.getId());
        Assertions.assertEquals(add.getNameDto(),added.getNameDto());
        Assertions.assertEquals(add.getTeacherDto(),added.getTeacherDto());





    }
    @Test
    void updateTest(){
        createOne();
        Random random =new Random();
        int randomIndex=random.nextInt(subjectService.getAll().size());
        Long someIndex=subjectService.getAll().get(randomIndex).getId();
        SubjectDto newSubject=SubjectDto.builder().id(someIndex).nameDto("ghjk").teacherDto("ghjk").build();
        SubjectDto update=subjectService.updateSubject(newSubject.getId(),newSubject);

        Assertions.assertNotNull(update);
        Assertions.assertNotNull(update.getId());
        Assertions.assertNotNull(update.getNameDto());
        Assertions.assertNotNull(update.getTeacherDto());

        Assertions.assertEquals(newSubject.getId(),update.getId());
        Assertions.assertEquals(newSubject.getNameDto(),update.getNameDto());
        Assertions.assertEquals(newSubject.getTeacherDto(),update.getTeacherDto());
        SubjectDto updated=subjectService.getById(someIndex);

        Assertions.assertNotNull(updated);
        Assertions.assertNotNull(updated.getId());
        Assertions.assertNotNull(updated.getNameDto());
        Assertions.assertNotNull(updated.getTeacherDto());

        Assertions.assertEquals(update.getId(),updated.getId());
        Assertions.assertEquals(update.getNameDto(),updated.getNameDto());
        Assertions.assertEquals(update.getTeacherDto(),updated.getTeacherDto());



    }
    @Test
    void deleteTest(){
        createOne();
        Random random =new Random();
        int randomIndex=random.nextInt(subjectService.getAll().size());
        Long someIndex=subjectService.getAll().get(randomIndex).getId();
        Assertions.assertTrue(subjectService.deleteSubject(someIndex));
        SubjectDto deleted=subjectService.getById(someIndex);

    }

}
