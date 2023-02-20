package act.com.JobPortal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationReqDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String department;
    private Date yearOfGraduate;
    private double grade;
    private String experience;
}
