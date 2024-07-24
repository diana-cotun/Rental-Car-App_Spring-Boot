package ro.sda.java64.rentalcarservice_finalproject.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import ro.sda.java64.rentalcarservice_finalproject.dto.CarDto;
import ro.sda.java64.rentalcarservice_finalproject.enums.EntityStatus;
import ro.sda.java64.rentalcarservice_finalproject.enums.SizeOfCar;

import java.util.List;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private String fuel;
    private Integer yearOfManufacture;
    private String transmission;

    @Enumerated(EnumType.STRING)
    private SizeOfCar sizeOfCar;

    private Double pricePerDay;
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    @JsonIgnore
    private Branch branch;

    @OneToMany(mappedBy = "car")
//    cascade = CascadeType.ALL, orphanRemoval = true
    private List<Reservation> reservation;

    @Enumerated(EnumType.STRING)
    private EntityStatus entityStatus;

    public CarDto getCarDto() {
        CarDto carDto = CarDto.builder()
                .id(id)
                .brand(brand)
                .model(model)
                .fuel(fuel)
                .yearOfManufacture(yearOfManufacture)
                .transmission(transmission)
                .sizeOfCar(sizeOfCar)
                .pricePerDay(pricePerDay)
                .imageUrl(imageUrl)
                .branchId(branch.getId())
                .entityStatus(entityStatus)
                .build();
        return carDto;
    }
}
