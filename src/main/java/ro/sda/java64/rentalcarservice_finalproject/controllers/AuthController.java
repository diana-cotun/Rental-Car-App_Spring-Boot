package ro.sda.java64.rentalcarservice_finalproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sda.java64.rentalcarservice_finalproject.dto.UserDto;
import ro.sda.java64.rentalcarservice_finalproject.dto.LoginRequest;
import ro.sda.java64.rentalcarservice_finalproject.dto.LoginResponse;
import ro.sda.java64.rentalcarservice_finalproject.dto.SignupRequest;
import ro.sda.java64.rentalcarservice_finalproject.services.auth.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signupCustomer(@RequestBody SignupRequest signupRequest) {
        if (authService.hasCustomerWithEmail(signupRequest.getEmail())){
            return new ResponseEntity<>("Customer with this email already exist", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto createdUserDto = authService.createCustomer(signupRequest);
        if (createdUserDto == null) {
            return new ResponseEntity<>("Customer not created", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginCustomer(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.authenticate(loginRequest));
    }
}
