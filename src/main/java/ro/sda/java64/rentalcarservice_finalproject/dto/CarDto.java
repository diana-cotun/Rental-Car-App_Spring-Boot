package ro.sda.java64.rentalcarservice_finalproject.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.sda.java64.rentalcarservice_finalproject.entities.Branch;
import ro.sda.java64.rentalcarservice_finalproject.entities.Car;
import ro.sda.java64.rentalcarservice_finalproject.enums.EntityStatus;
import ro.sda.java64.rentalcarservice_finalproject.enums.SizeOfCar;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDto {

    private Long id;
    private String brand;
    private String model;
    private String fuel;
    private Integer yearOfManufacture;
    private String transmission;
    private SizeOfCar sizeOfCar;
    private Double pricePerDay;
    private String imageUrl;
    private Long branchId;
    private EntityStatus entityStatus;


//    public Car getCarEntity() {
//        Car car = Car.builder()
//                .id(id)
//                .brand(brand)
//                .model(model)
//                .fuel(fuel)
//                .yearOfManufacture(yearOfManufacture)
//                .transmission(transmission)
//                .sizeOfCar(sizeOfCar)
//                .pricePerDay(pricePerDay)
//                .imageUrl(imageUrl)
//                .branch(Branch.builder().build())
//                .build();
//        return car;
//    }

}
