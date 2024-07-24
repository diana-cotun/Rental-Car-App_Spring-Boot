package ro.sda.java64.rentalcarservice_finalproject.services.auth;

import ro.sda.java64.rentalcarservice_finalproject.dto.LoginRequest;
import ro.sda.java64.rentalcarservice_finalproject.dto.LoginResponse;
import ro.sda.java64.rentalcarservice_finalproject.dto.UserDto;
import ro.sda.java64.rentalcarservice_finalproject.dto.SignupRequest;

public interface AuthService {

    UserDto createCustomer(SignupRequest signupRequest);

    boolean hasCustomerWithEmail(String email);

    LoginResponse authenticate(LoginRequest loginRequest);

}
