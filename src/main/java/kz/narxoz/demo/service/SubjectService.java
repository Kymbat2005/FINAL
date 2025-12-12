package kz.narxoz.demo.service;

import kz.narxoz.demo.dto.SubjectDto;

import java.util.List;

public interface SubjectService {
    List<SubjectDto> getAll();
    SubjectDto getById(Long id);
    SubjectDto addSubject(SubjectDto subjectDto);
    SubjectDto updateSubject(Long id,SubjectDto subjectDto);
    Boolean deleteSubject(Long id);
}


