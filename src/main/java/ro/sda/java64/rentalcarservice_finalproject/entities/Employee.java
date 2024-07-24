//package ro.sda.java64.rentalcarservice_finalproject.entities;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIdentityInfo;
//import com.fasterxml.jackson.annotation.ObjectIdGenerators;
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Table(name = "employees")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
////@JsonIdentityInfo(
////        generator = ObjectIdGenerators.PropertyGenerator.class,
////        property = "id",
////        scope = Employee.class)
//
//public class Employee {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String firstName;
//    private String lastName;
//
//    @ManyToOne
//    @JoinColumn(name = "branchId")
//    private Branch branch;
//
//
//}
