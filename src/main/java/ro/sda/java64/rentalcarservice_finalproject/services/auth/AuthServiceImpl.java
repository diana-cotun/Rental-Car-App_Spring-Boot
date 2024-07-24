package ro.sda.java64.rentalcarservice_finalproject.services.auth;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.sda.java64.rentalcarservice_finalproject.dto.UserDto;
import ro.sda.java64.rentalcarservice_finalproject.dto.LoginRequest;
import ro.sda.java64.rentalcarservice_finalproject.dto.LoginResponse;
import ro.sda.java64.rentalcarservice_finalproject.dto.SignupRequest;
import ro.sda.java64.rentalcarservice_finalproject.entities.User;
import ro.sda.java64.rentalcarservice_finalproject.enums.UserRole;
import ro.sda.java64.rentalcarservice_finalproject.repositories.UserRepository;
import ro.sda.java64.rentalcarservice_finalproject.services.JwtService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @PostConstruct
    public void createAdminAccount(){
        User adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
        if (adminAccount == null) {
            User newAdminAccount = User.builder()
                    .name("Admin")
                    .email("admin@gmail.com")
                    .password(passwordEncoder.encode("admin"))
                    .userRole(UserRole.ADMIN)
                    .build();
            userRepository.save(newAdminAccount);
            System.out.println("Admin account created successfully");
        }
    }

    @Override
    public UserDto createCustomer(SignupRequest signupRequest) {
        User customer = User.builder()
                .name(signupRequest.getName())
                .email(signupRequest.getEmail())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .userRole(UserRole.CUSTOMER)
                .build();
        User createdCustomer = userRepository.save(customer);
        UserDto userDto = new UserDto();
        userDto.setId(createdCustomer.getId());
        return userDto;
    }

    @Override
    public boolean hasCustomerWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }

    @Override
    public LoginResponse authenticate(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        User customer = userRepository.findFirstByEmail(loginRequest.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(customer);
        return LoginResponse.builder().token(jwtToken).userId(customer.getId()).userRole(customer.getUserRole()).build();
    }



}
