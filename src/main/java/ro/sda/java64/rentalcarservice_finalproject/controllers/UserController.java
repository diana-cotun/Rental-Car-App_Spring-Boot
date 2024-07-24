//package ro.sda.java64.rentalcarservice_finalproject.controllers;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import ro.sda.java64.rentalcarservice_finalproject.entities.User;
//import ro.sda.java64.rentalcarservice_finalproject.services.UserService;
//
//import java.util.List;
//
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/customer")
//public class UserController {
//
//    private final UserService userService;
//
//    @PostMapping
//    public ResponseEntity<User> create(@RequestBody User user) {
//        return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<User> get(@PathVariable Long id) {
//        User foundUser = userService.getById(id);
//        if (foundUser == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(foundUser, HttpStatus.OK);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
//        User updatedUser = userService.update(id, user);
//        if (updatedUser == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable Long id) {
//        User deletedUser = userService.delete(id);
//        if (deletedUser == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<User>> getAll() {
//        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
//    }
//
//}
