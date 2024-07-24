package ro.sda.java64.rentalcarservice_finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.sda.java64.rentalcarservice_finalproject.enums.UserRole;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    private String token;
    private UserRole userRole;
    private Long userId;

}
