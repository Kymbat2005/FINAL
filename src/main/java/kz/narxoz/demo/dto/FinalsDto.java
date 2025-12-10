package kz.narxoz.demo.dto;

import kz.narxoz.demo.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class FinalsDto {
    private Long id;
    private String nameDto;
    private LocalDate dateDto;
    private String subjectDto;

}
