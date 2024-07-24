//package ro.sda.java64.rentalcarservice_finalproject.services;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import ro.sda.java64.rentalcarservice_finalproject.entities.User;
//import ro.sda.java64.rentalcarservice_finalproject.repositories.UserRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class UserService {
//
//    private final UserRepository userRepository;
//
//    public User create(User customer) {
//        return userRepository.save(customer);
//    }
//
//    public User getById(Long id) {
//        Optional<User> foundCustomer = userRepository.findById(id);
//        if (foundCustomer.isEmpty()) {
//            return null;
//        }
//        return foundCustomer.get();
//    }
//    public User update(Long id, User user) {
//        User updatedUser = getById(id);
//
//        updatedUser.setName(user.getName());
//        updatedUser.setEmail(user.getEmail());
//        updatedUser.setPassword(user.getPassword());
//
//        return userRepository.save(updatedUser);
//    }
//
//    public User delete(Long id) {
//        User deletedUser = getById(id);
//        userRepository.deleteById(id);
//        return deletedUser;
//    }
//
//    public List<User> getAll(){
//        return userRepository.findAll();
//    }
//
//
//}
