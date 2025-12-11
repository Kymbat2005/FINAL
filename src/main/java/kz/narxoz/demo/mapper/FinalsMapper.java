package kz.narxoz.demo.mapper;

import kz.narxoz.demo.dto.FinalsDto;
import kz.narxoz.demo.model.Finals;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FinalsMapper {
    @Mapping(target = "nameDto",source = "name")
    @Mapping(target="dateDto",source="date")
    @Mapping(target = "subjectDto", source = "subject.name")
    FinalsDto toDto(Finals finals);

    @Mapping(target = "name",source = "nameDto")
    @Mapping(target = "email",source = "emailDto")
    Finals toEntity(FinalsDto finalsDto);

    List<FinalsDto> toDtoList(List<Finals> finals);
}



