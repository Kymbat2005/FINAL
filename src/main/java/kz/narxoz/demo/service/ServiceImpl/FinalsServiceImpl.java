package kz.narxoz.demo.service.ServiceImpl;

import kz.narxoz.demo.dto.FinalsDto;
import kz.narxoz.demo.dto.StudentDto;
import kz.narxoz.demo.mapper.FinalsMapper;
import kz.narxoz.demo.mapper.StudentMapper;
import kz.narxoz.demo.model.Finals;
import kz.narxoz.demo.model.Student;
import kz.narxoz.demo.repository.FinalsRepository;
import kz.narxoz.demo.repository.StudentRepository;
import kz.narxoz.demo.service.FinalsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FinalsServiceImpl implements FinalsService {
    private final FinalsRepository finalsRepository;
    private final FinalsMapper finalsMapper;

    @Override
    public List<FinalsDto> getAll(){
        return finalsMapper.toDtoList(finalsRepository.findAll());
    }

    @Override
    public FinalsDto getById(Long id){
        return finalsMapper.toDto(finalsRepository.findById(id).orElse(null));
    }

    @Override
    public FinalsDto addFinals(FinalsDto finalsDto){
        Finals finals=finalsRepository.save(finalsMapper.toEntity(finalsDto));
        return finalsMapper.toDto(finals);
    }

    @Override
    public FinalsDto updateFinals(Long id,FinalsDto finalsDto){
        Finals finals=finalsRepository.findById(id).orElse(null);
        Finals finalsEntity=finalsMapper.toEntity(finalsDto);

        finals.setId(finalsEntity.getId());
        finals.setName(finalsEntity.getName());
        finals.setDate(finalsEntity.getDate());
        finals.setStudent(finalsEntity.getStudent());
        return finalsMapper.toDto(finalsRepository.save(finals));

    }
    @Override
    public Boolean deleteFinals(Long id){
        finalsRepository.deleteById(id);
        Finals finals = finalsRepository.findById(id).orElse(null);
        if(Objects.isNull(finals)){
            return true;
        }
        return false;

    }

}
