package ro.sda.java64.rentalcarservice_finalproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sda.java64.rentalcarservice_finalproject.dto.CarDto;
import ro.sda.java64.rentalcarservice_finalproject.dto.SearchAvailableCarDto;
import ro.sda.java64.rentalcarservice_finalproject.exceptions.CustomDatabaseException;
import ro.sda.java64.rentalcarservice_finalproject.services.CarService;

import java.util.List;


@RestController
@RequiredArgsConstructor
//@CrossOrigin
@RequestMapping("/car")
public class CarController {

    private final CarService carService;

    @PostMapping()
    public ResponseEntity<CarDto> createCar(@RequestBody CarDto carDto) {
        CarDto createdCar = carService.createCar(carDto);
        if (createdCar == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdCar, HttpStatus.CREATED);
    }

    @PostMapping("/available-car")
    public ResponseEntity<List<CarDto>> getAvailableCars(@RequestBody SearchAvailableCarDto searchAvailableCarDto) {
        List<CarDto> availableCars = carService.findAvailableCarsByBranchAndDates(searchAvailableCarDto);
        if (availableCars == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(availableCars, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long id) {
        CarDto foundCar = carService.getCarById(id);
        if (foundCar == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foundCar, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDto> updateCar(@PathVariable Long id, @RequestBody CarDto carDto) {
        CarDto updatedCar = carService.update(id, carDto);
        if (updatedCar == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedCar, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws CustomDatabaseException {
        try {
            carService.deleteCar(id);
            return new ResponseEntity<>("Car was deleted", HttpStatus.OK);
        } catch (Exception e) {
            throw new CustomDatabaseException("Error while deleting the car");
        }
    }

    @GetMapping
    public ResponseEntity<List<CarDto>> getAll() {
        return new ResponseEntity<>(carService.getAllCars(), HttpStatus.OK);
    }

    @GetMapping("/status-cars")
    public ResponseEntity<List<CarDto>> getAllCarsAvailableByEntityStatus() {
        return new ResponseEntity<>(carService.getAllCarsAvailableByEntityStatus(), HttpStatus.OK);
    }

    @GetMapping("/byBranch/{branchId}")
    public ResponseEntity<List<CarDto>> getAllCarsByBranchId(@PathVariable Long branchId) {
        return new ResponseEntity<>(carService.getAllCarByBranchId(branchId), HttpStatus.OK);
    }

}
