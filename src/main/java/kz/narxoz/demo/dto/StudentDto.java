package kz.narxoz.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
    private Long id;
    private String nameDto;
    private String lastnameDto;
    private String emailDto;
    private List<SubjectDto> subjects;
}
