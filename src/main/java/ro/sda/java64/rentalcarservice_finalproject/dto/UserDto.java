package ro.sda.java64.rentalcarservice_finalproject.dto;

import lombok.Data;
import ro.sda.java64.rentalcarservice_finalproject.enums.UserRole;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private UserRole userRole;
}
