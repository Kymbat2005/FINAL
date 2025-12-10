package kz.narxoz.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
    private Long id;
    private String nameDto;
    private String lastnameDto;
    private String emailDto;
}
