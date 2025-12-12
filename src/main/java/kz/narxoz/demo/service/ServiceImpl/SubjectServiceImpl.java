package kz.narxoz.demo.service.ServiceImpl;

import kz.narxoz.demo.dto.SubjectDto;
import kz.narxoz.demo.mapper.SubjectMapper;
import kz.narxoz.demo.model.Subject;
import kz.narxoz.demo.repository.SubjectRepository;
import kz.narxoz.demo.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    @Override
    public List<SubjectDto> getAll(){
        return subjectMapper.toDtoList(subjectRepository.findAll());
    }

    @Override
    public SubjectDto getById(Long id){
        return subjectMapper.toDto(subjectRepository.findById(id).orElse(null));
    }

    @Override
    public SubjectDto addSubject(SubjectDto subjectDto){
        Subject subject=subjectRepository.save(subjectMapper.toEntity(subjectDto));
        return subjectMapper.toDto(subject);
    }

    @Override
    public SubjectDto updateSubject(Long id,SubjectDto subjectDto){
        Subject subject=subjectRepository.findById(id).orElse(null);
        Subject subjectEntity=subjectMapper.toEntity(subjectDto);

        subject.setId(subjectEntity.getId());
        subject.setName(subjectEntity.getName());
        subject.setTeacher(subjectEntity.getTeacher());
        return subjectMapper.toDto(subjectRepository.save(subject));

    }
    @Override
    public Boolean deleteSubject(Long id){
        subjectRepository.deleteById(id);
        Subject subject = subjectRepository.findById(id).orElse(null);
        if(Objects.isNull(subject)){
            return true;
        }
        return false;

    }



}
