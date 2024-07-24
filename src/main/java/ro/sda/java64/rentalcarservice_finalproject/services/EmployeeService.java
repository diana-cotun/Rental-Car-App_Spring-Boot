//package ro.sda.java64.rentalcarservice_finalproject.services;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import ro.sda.java64.rentalcarservice_finalproject.entities.Employee;
//import ro.sda.java64.rentalcarservice_finalproject.repositories.EmployeeRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class EmployeeService {
//
//    private final EmployeeRepository employeeRepository;
//
//    public Employee create(Employee customer) {
//        return employeeRepository.save(customer);
//    }
//
//    public Employee getById(Long id) {
//        Optional<Employee> foundEmployee = employeeRepository.findById(id);
//        if (foundEmployee.isEmpty()) {
//            return null;
//        }
//        return foundEmployee.get();
//    }
//    public Employee update(Long id, Employee employee) {
//        Employee updatedEmployee = getById(id);
//
//        updatedEmployee.setFirstName(employee.getFirstName());
//        updatedEmployee.setLastName(employee.getLastName());
//
//        return employeeRepository.save(updatedEmployee);
//    }
//
//    public Employee delete(Long id) {
//        Employee deletedEmployee = getById(id);
//        employeeRepository.deleteById(id);
//        return deletedEmployee;
//    }
//
//    public List<Employee> getAll(){
//        return employeeRepository.findAll();
//    }
//
//
//}
