package kz.narxoz.demo.mapper;

import kz.narxoz.demo.dto.FinalsDto;
import kz.narxoz.demo.model.Finals;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FinalsMapper {

    @Mapping(target = "studentId", source = "student.id")
    @Mapping(target = "subjectId", source = "subject.id")
    FinalsDto toDto(Finals finals);

    @Mapping(target = "student", ignore = true) // будем ставить вручную в сервисе
    @Mapping(target = "subject", ignore = true)
    Finals toEntity(FinalsDto finalsDto);

    List<FinalsDto> toDtoList(List<Finals> finals);
}




