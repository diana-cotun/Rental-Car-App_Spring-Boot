//package ro.sda.java64.rentalcarservice_finalproject.controllers;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import ro.sda.java64.rentalcarservice_finalproject.entities.Employee;
//import ro.sda.java64.rentalcarservice_finalproject.services.EmployeeService;
//
//import java.util.List;
//
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/employee")
//@CrossOrigin(origins = "http://localhost:4200")
//
//public class EmployeeController {
//
//    private final EmployeeService employeeService;
//
//    @PostMapping
//    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
//        return new ResponseEntity<>(employeeService.create(employee), HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Employee> get(@PathVariable Long id) {
//        Employee foundEmployee = employeeService.getById(id);
//        if (foundEmployee == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(foundEmployee, HttpStatus.OK);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee employee) {
//        Employee updatedEmployee = employeeService.update(id, employee);
//        if (updatedEmployee == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable Long id) {
//        Employee deletedEmployee = employeeService.delete(id);
//        if (deletedEmployee == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Employee>> getAll() {
//        return new ResponseEntity<>(employeeService.getAll(), HttpStatus.OK);
//    }
//
//}
