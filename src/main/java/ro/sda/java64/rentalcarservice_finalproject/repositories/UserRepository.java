package ro.sda.java64.rentalcarservice_finalproject.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sda.java64.rentalcarservice_finalproject.entities.User;
import ro.sda.java64.rentalcarservice_finalproject.enums.UserRole;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByEmail(String email);

    User findByUserRole(UserRole userRole);
}

