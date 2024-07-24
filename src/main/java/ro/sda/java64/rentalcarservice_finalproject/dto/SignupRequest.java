package ro.sda.java64.rentalcarservice_finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.sda.java64.rentalcarservice_finalproject.enums.UserRole;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {

    private String name;
    private String email;
    private String password;

}
