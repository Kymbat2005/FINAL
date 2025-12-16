package kz.narxoz.demo;

import kz.narxoz.demo.dto.FinalsDto;
import kz.narxoz.demo.model.Student;
import kz.narxoz.demo.model.Subject;
import kz.narxoz.demo.repository.StudentRepository;
import kz.narxoz.demo.repository.SubjectRepository;
import kz.narxoz.demo.service.FinalsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@SpringBootTest
@ActiveProfiles("test")
public class FinalsServiceTest {

    @Autowired
    private FinalsService finalsService;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    private FinalsDto createOne() {

        if (studentRepository.count() == 0) {
            Student student = new Student();
            student.setName("Test Student");
            studentRepository.save(student);
        }

        if (subjectRepository.count() == 0) {
            Subject subject = new Subject();
            subject.setName("Test Subject");
            subjectRepository.save(subject);
        }

        List<Student> students = studentRepository.findAll();
        List<Subject> subjects = subjectRepository.findAll();

        FinalsDto finalsDto = new FinalsDto();
        finalsDto.setNameDto("Test Final 1");
        finalsDto.setDateDto(LocalDate.now());
        finalsDto.setStudentIdDto(students.get(0).getId());
        finalsDto.setSubjectIdDto(subjects.get(0).getId());

        return finalsService.addFinals(finalsDto);
    }

    @Test
    void getAllTest() {
        createOne();
        List<FinalsDto> finalsDtos = finalsService.getAll();

        Assertions.assertNotNull(finalsDtos);
        Assertions.assertNotEquals(0, finalsDtos.size());

        for (FinalsDto finalsDto : finalsDtos) {
            Assertions.assertNotNull(finalsDto);
            Assertions.assertNotNull(finalsDto.getId());
            Assertions.assertNotNull(finalsDto.getNameDto());
            Assertions.assertNotNull(finalsDto.getDateDto());

        }
    }

    @Test
    void getByIdTest() {
        createOne();
        Random random = new Random();
        List<FinalsDto> allFinals = finalsService.getAll();
        int randomIndex = random.nextInt(allFinals.size());

        Long someId = allFinals.get(randomIndex).getId();
        FinalsDto finalsDto = finalsService.getById(someId);

        Assertions.assertNotNull(finalsDto);
        Assertions.assertNotNull(finalsDto.getId());
        Assertions.assertNotNull(finalsDto.getNameDto());
        Assertions.assertNotNull(finalsDto.getDateDto());


        FinalsDto check = finalsService.getById(-1L);
        Assertions.assertNull(check);
    }

    @Test
    void addFinalsTest() {

        if (studentRepository.count() == 0) {
            Student student = new Student();
            student.setName("Test Student");
            studentRepository.save(student);
        }

        if (subjectRepository.count() == 0) {
            Subject subject = new Subject();
            subject.setName("Test Subject");
            subjectRepository.save(subject);
        }

        List<Student> students = studentRepository.findAll();
        List<Subject> subjects = subjectRepository.findAll();

        FinalsDto finalsDto = new FinalsDto();
        finalsDto.setNameDto("Math Final Exam");
        finalsDto.setDateDto(LocalDate.of(2024, 5, 20));
        finalsDto.setStudentIdDto(students.get(0).getId());
        finalsDto.setSubjectIdDto(subjects.get(0).getId());

        FinalsDto created = finalsService.addFinals(finalsDto);

        Assertions.assertNotNull(created);
        Assertions.assertNotNull(created.getId());
        Assertions.assertNotNull(created.getNameDto());
        Assertions.assertNotNull(created.getDateDto());

        Assertions.assertEquals(finalsDto.getNameDto(), created.getNameDto());
        Assertions.assertEquals(finalsDto.getDateDto(), created.getDateDto());

        FinalsDto getFinals = finalsService.getById(created.getId());

        Assertions.assertNotNull(getFinals);
        Assertions.assertNotNull(getFinals.getId());
        Assertions.assertNotNull(getFinals.getNameDto());
        Assertions.assertNotNull(getFinals.getDateDto());

        Assertions.assertEquals(created.getId(), getFinals.getId());
        Assertions.assertEquals(created.getNameDto(), getFinals.getNameDto());
        Assertions.assertEquals(created.getDateDto(), getFinals.getDateDto());
    }

    @Test
    void updateFinalsTest() {

        if (studentRepository.count() == 0) {
            Student student1 = new Student();
            student1.setName("Student 1");
            studentRepository.save(student1);

            Student student2 = new Student();
            student2.setName("Student 2");
            studentRepository.save(student2);
        }

        if (subjectRepository.count() == 0) {
            Subject subject1 = new Subject();
            subject1.setName("Math");
            subjectRepository.save(subject1);

            Subject subject2 = new Subject();
            subject2.setName("Physics");
            subjectRepository.save(subject2);
        }


        List<Student> students = studentRepository.findAll();
        List<Subject> subjects = subjectRepository.findAll();
        if (students.size() < 2) {

            Student student = new Student();
            student.setName("Student " + (students.size() + 1));
            studentRepository.save(student);
            students = studentRepository.findAll();
        }

        if (subjects.size() < 2) {

            Subject subject = new Subject();
            subject.setName("Subject " + (subjects.size() + 1));
            subjectRepository.save(subject);
            subjects = subjectRepository.findAll();
        }


        FinalsDto finalsDto = new FinalsDto();
        finalsDto.setNameDto("Original Final Exam");
        finalsDto.setDateDto(LocalDate.now());
        finalsDto.setStudentIdDto(students.get(0).getId());
        finalsDto.setSubjectIdDto(subjects.get(0).getId());

        FinalsDto existingFinals = finalsService.addFinals(finalsDto);
        Long existingId = existingFinals.getId();

        Assertions.assertNotNull(existingId, "ID существующей записи не должен быть null");


        FinalsDto updateDto = new FinalsDto();
        updateDto.setNameDto("Updated Final Exam");
        updateDto.setDateDto(LocalDate.of(2024, 6, 15));

        updateDto.setStudentIdDto(students.get(1).getId());
        updateDto.setSubjectIdDto(subjects.get(1).getId());


        FinalsDto updated = finalsService.updateFinals(existingId, updateDto);


        Assertions.assertNotNull(updated);
        Assertions.assertNotNull(updated.getId());
        Assertions.assertNotNull(updated.getNameDto());
        Assertions.assertNotNull(updated.getDateDto());
        Assertions.assertNotNull(updated.getStudentIdDto());
        Assertions.assertNotNull(updated.getSubjectIdDto());

        Assertions.assertEquals(existingId, updated.getId());
        Assertions.assertEquals(updateDto.getNameDto(), updated.getNameDto());
        Assertions.assertEquals(updateDto.getDateDto(), updated.getDateDto());
        Assertions.assertEquals(updateDto.getStudentIdDto(), updated.getStudentIdDto());
        Assertions.assertEquals(updateDto.getSubjectIdDto(), updated.getSubjectIdDto());


        FinalsDto after = finalsService.getById(existingId);
        Assertions.assertNotNull(after);
        Assertions.assertEquals(updated.getId(), after.getId());
        Assertions.assertEquals(updated.getNameDto(), after.getNameDto());
        Assertions.assertEquals(updated.getDateDto(), after.getDateDto());
        Assertions.assertEquals(updated.getStudentIdDto(), after.getStudentIdDto());
        Assertions.assertEquals(updated.getSubjectIdDto(), after.getSubjectIdDto());
    }

    @Test
    void deleteFinalsTest() {
        createOne();
        Random random = new Random();
        List<FinalsDto> allFinals = finalsService.getAll();
        int randomIndex = random.nextInt(allFinals.size());

        Long someId = allFinals.get(randomIndex).getId();

        Assertions.assertTrue(finalsService.deleteFinals(someId));

        FinalsDto finalsDto = finalsService.getById(someId);
        Assertions.assertNull(finalsDto);
    }
}