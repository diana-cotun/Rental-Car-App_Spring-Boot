package ro.sda.java64.rentalcarservice_finalproject.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import ro.sda.java64.rentalcarservice_finalproject.dto.BranchDto;

import java.util.List;

@Entity
@Table(name = "branches")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String branchName;
    private String city;
    private String address;
    private String imageUrl;
    @OneToMany(mappedBy = "branch")
//    @JsonIgnore
    private List<Car> cars;

//    @OneToMany(mappedBy = "branch")
//    @JsonIgnore
//    private List<Employee> employees;


    public BranchDto getBranchDto(){
        BranchDto branchDto = new BranchDto();
        branchDto.setId(id);
        branchDto.setBranchName(branchName);
        branchDto.setCity(city);
        branchDto.setAddress(address);
        branchDto.setImageUrl(imageUrl);
        return branchDto;
    }


}
