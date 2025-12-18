package kz.narxoz.demo;

import kz.narxoz.demo.dto.FinalsDto;
import kz.narxoz.demo.mapper.FinalsMapper;
import kz.narxoz.demo.model.Finals;
import kz.narxoz.demo.model.Student;
import kz.narxoz.demo.model.Subject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")

public class FinalsMapperTest {

    @Autowired
    private FinalsMapper finalsMapper;

    @Test
    void convertEntityToDtoTest() {

        Student student = new Student();
        student.setId(1L);

        Subject subject = new Subject();
        subject.setId(2L);


        Finals finalsEntity = new Finals(1L, "Math Final", LocalDate.now(), student, subject);


        FinalsDto finalsDto = finalsMapper.toDto(finalsEntity);


        Assertions.assertNotNull(finalsDto);
        Assertions.assertNotNull(finalsDto.getId());
        Assertions.assertNotNull(finalsDto.getNameDto());
        Assertions.assertNotNull(finalsDto.getDateDto());
        Assertions.assertNotNull(finalsDto.getStudentIdDto());
        Assertions.assertNotNull(finalsDto.getSubjectIdDto());

        Assertions.assertEquals(finalsEntity.getId(), finalsDto.getId());
        Assertions.assertEquals(finalsEntity.getName(), finalsDto.getNameDto());
        Assertions.assertEquals(finalsEntity.getDate(), finalsDto.getDateDto());
        Assertions.assertEquals(student.getId(), finalsDto.getStudentIdDto());
        Assertions.assertEquals(subject.getId(), finalsDto.getSubjectIdDto());
    }

    @Test
    void convertDtoToEntityTest() {

        FinalsDto finalsDto = new FinalsDto(1L, "Physics Final", LocalDate.now(), 1L, 2L);


        Student student = new Student();
        student.setId(finalsDto.getStudentIdDto());

        Subject subject = new Subject();
        subject.setId(finalsDto.getSubjectIdDto());

        Finals finalsEntity = new Finals(
                finalsDto.getId(),
                finalsDto.getNameDto(),
                finalsDto.getDateDto(),
                student,
                subject
        );


        Assertions.assertNotNull(finalsEntity);
        Assertions.assertNotNull(finalsEntity.getStudent());
        Assertions.assertNotNull(finalsEntity.getSubject());

        Assertions.assertEquals(finalsDto.getId(), finalsEntity.getId());
        Assertions.assertEquals(finalsDto.getNameDto(), finalsEntity.getName());
        Assertions.assertEquals(finalsDto.getDateDto(), finalsEntity.getDate());
        Assertions.assertEquals(finalsDto.getStudentIdDto(), finalsEntity.getStudent().getId());
        Assertions.assertEquals(finalsDto.getSubjectIdDto(), finalsEntity.getSubject().getId());
    }

    @Test
    void convertEntityListToDtoList() {
        List<Finals> finalsEntityList = new ArrayList<>();

        for (long i = 1; i <= 3; i++) {
            Student student = new Student();
            student.setId(i);

            Subject subject = new Subject();
            subject.setId(i + 10);

            finalsEntityList.add(new Finals(
                    i,
                    "Final " + i,
                    LocalDate.now(),
                    student,
                    subject
            ));
        }

        List<FinalsDto> finalsDtoList = finalsMapper.toDtoList(finalsEntityList);

        Assertions.assertNotNull(finalsDtoList);
        Assertions.assertNotEquals(0, finalsDtoList.size());

        for (int i = 0; i < finalsDtoList.size(); i++) {
            FinalsDto dto = finalsDtoList.get(i);
            Finals entity = finalsEntityList.get(i);

            Assertions.assertNotNull(dto);
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getNameDto());
            Assertions.assertNotNull(dto.getDateDto());
            Assertions.assertNotNull(dto.getStudentIdDto());
            Assertions.assertNotNull(dto.getSubjectIdDto());

            Assertions.assertEquals(entity.getId(), dto.getId());
            Assertions.assertEquals(entity.getName(), dto.getNameDto());
            Assertions.assertEquals(entity.getDate(), dto.getDateDto());
            Assertions.assertEquals(entity.getStudent().getId(), dto.getStudentIdDto());
            Assertions.assertEquals(entity.getSubject().getId(), dto.getSubjectIdDto());
        }
    }
}
