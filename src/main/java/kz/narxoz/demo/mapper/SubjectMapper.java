package kz.narxoz.demo.mapper;

import kz.narxoz.demo.dto.StudentDto;
import kz.narxoz.demo.dto.SubjectDto;
import kz.narxoz.demo.model.Student;
import kz.narxoz.demo.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    @Mapping(target = "nameDto",source = "name")
    @Mapping(target="teacherDto",source="teacher")
    SubjectDto toDto(Subject subject);

    @Mapping(target = "name",source = "nameDto")
    @Mapping(target = "teacher",source = "teacherDto")

    Subject toEntity(SubjectDto subjectDto);

    List<SubjectDto> toDtoList(List<Subject> subject);
}


