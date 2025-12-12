package kz.narxoz.demo.service;

import kz.narxoz.demo.dto.FinalsDto;
import kz.narxoz.demo.dto.StudentDto;

import java.util.List;

public interface FinalsService {
    List<FinalsDto> getAll();
    FinalsDto getById(Long id);
    FinalsDto addFinals(FinalsDto finalsDto);
    FinalsDto updateFinals(Long id,FinalsDto finalsDto);
    Boolean deleteFinals(Long id);

}
