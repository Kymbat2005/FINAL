package kz.narxoz.demo.mapper;


import kz.narxoz.demo.dto.StudentDto;

import kz.narxoz.demo.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(target = "nameDto",source = "name")
    @Mapping(target="lastnameDto",source="lastname")
    @Mapping(target = "emailDto",source = "email")
    @Mapping(target = "subjects", source = "subjects")
    StudentDto toDto(   Student student);

    @Mapping(target = "name",source = "nameDto")
    @Mapping(target = "lastname",source = "lastnameDto")
    @Mapping(target = "email",source = "emailDto")
    @Mapping(target = "subjects", source = "subjects")
    Student toEntity(StudentDto studentDto);

    List<StudentDto> toDtoList(List<Student> student);
}

