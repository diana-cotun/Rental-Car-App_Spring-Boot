package ro.sda.java64.rentalcarservice_finalproject.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.sda.java64.rentalcarservice_finalproject.dto.CarDto;
import ro.sda.java64.rentalcarservice_finalproject.dto.ReservationDto;
import ro.sda.java64.rentalcarservice_finalproject.dto.SearchAvailableCarDto;
import ro.sda.java64.rentalcarservice_finalproject.entities.Branch;
import ro.sda.java64.rentalcarservice_finalproject.entities.Car;
import ro.sda.java64.rentalcarservice_finalproject.entities.Reservation;
import ro.sda.java64.rentalcarservice_finalproject.enums.EntityStatus;
import ro.sda.java64.rentalcarservice_finalproject.exceptions.CustomDatabaseException;
import ro.sda.java64.rentalcarservice_finalproject.repositories.BranchRepository;
import ro.sda.java64.rentalcarservice_finalproject.repositories.CarRepository;
import ro.sda.java64.rentalcarservice_finalproject.repositories.ReservationRepository;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final BranchRepository branchRepository;
    private final ReservationRepository reservationRepository;

    public CarDto createCar(CarDto carDto) {
        Optional<Branch> optionalBranch = branchRepository.findById(carDto.getBranchId());
        if (optionalBranch.isEmpty()) {
            return null;
        }
        Car car = Car.builder()
                .brand(carDto.getBrand())
                .model(carDto.getModel())
                .fuel(carDto.getFuel())
                .yearOfManufacture(carDto.getYearOfManufacture())
                .transmission(carDto.getTransmission())
                .sizeOfCar(carDto.getSizeOfCar())
                .pricePerDay(carDto.getPricePerDay())
                .imageUrl(carDto.getImageUrl())
                .branch(optionalBranch.get())
                .entityStatus(EntityStatus.AVAILABLE)
                .build();
        Car createdCar = carRepository.save(car);
        carDto.setId(createdCar.getId());
        return carDto;
    }

    public List<CarDto> findAvailableCarsByBranchAndDates(SearchAvailableCarDto searchAvailableCarDto) {
        List<CarDto> carsByBranchId = getAllCarByBranchId(searchAvailableCarDto.getBranchId());
        List<CarDto> reservedCars = findReservedCarsByDates(searchAvailableCarDto.getStartDate(), searchAvailableCarDto.getEndDate());

        LinkedList<CarDto> availableCars = new LinkedList<>();
        for (CarDto carByBranch : carsByBranchId) {
            availableCars.add(carByBranch);
            for (CarDto reservedCar : reservedCars) {
                if (carByBranch.getId().equals(reservedCar.getId())) {
                    availableCars.remove(reservedCar);
                }
            }
        }
        return availableCars;
    }

//    private List<CarDto> findCarsByBranchId(Long branchId) {
//        return getAllCarByBranchId(branchId);
//    }

    public List<CarDto> findReservedCarsByDates(LocalDate startDate, LocalDate endDate) {

        List<CarDto> reservedCars = new ArrayList<>();

        List<ReservationDto> reservationList = reservationRepository.findAll().stream().map(Reservation::getReservationDto).toList();
        for (ReservationDto reservation : reservationList) {
            if (startDate.isEqual(reservation.getToDate()) ||
                    startDate.isBefore(reservation.getToDate()) && endDate.isAfter(reservation.getFromDate()) ||
                    endDate.isEqual(reservation.getFromDate())) {
                CarDto carDto = getCarById(reservation.getCarId());
                reservedCars.add(carDto);
            }
        }
        return reservedCars;
    }

    public CarDto getCarById(Long id) {
        Optional<Car> foundCar = carRepository.findById(id);
        return foundCar.map(Car::getCarDto).orElse(null);
    }

    public CarDto update(Long id, CarDto carDto) {
        Optional<Car> foundCar = carRepository.findById(id);
        if (foundCar.isEmpty()) {
            return null;
        }
        Car existingCar = foundCar.get();
        existingCar.setBrand(carDto.getBrand());
        existingCar.setModel(carDto.getModel());
        existingCar.setFuel(carDto.getFuel());
        existingCar.setYearOfManufacture(carDto.getYearOfManufacture());
        existingCar.setTransmission(carDto.getTransmission());
        existingCar.setSizeOfCar(carDto.getSizeOfCar());
        existingCar.setPricePerDay(carDto.getPricePerDay());
        existingCar.setImageUrl(carDto.getImageUrl());
        existingCar.setBranch(getBranchById(carDto.getBranchId()));
        return carRepository.save(existingCar).getCarDto();
    }

    private Branch getBranchById(Long branchId) {
        Optional<Branch> foundBranch = branchRepository.findById(branchId);
        return foundBranch.orElse(null);
    }


    public void deleteCar(Long carId) throws CustomDatabaseException {
        Car existingCar = carRepository.findById(carId).orElseThrow(() -> new CustomDatabaseException("Car with id: " + carId + " does not exist"));
        boolean isReserved = isCarReservedAtTheMoment(carId);
        if (isReserved) {
            throw new CustomDatabaseException("Car is reserved");
        }
        existingCar.setEntityStatus(EntityStatus.UNAVAILABLE);
        carRepository.save(existingCar);
    }

    private boolean isCarReservedAtTheMoment(Long carId) {
        LocalDate actualDate = LocalDate.now();

        List<Reservation> reservationListForDeletingCar = reservationRepository.findAllByCarId(carId);
        for (Reservation reservation : reservationListForDeletingCar) {
            if (actualDate.isEqual(reservation.getToDate()) ||
                    actualDate.isBefore(reservation.getToDate()) && actualDate.isAfter(reservation.getFromDate()) ||
                    actualDate.isEqual(reservation.getFromDate())) {
                return true;
            }
        }
        return false;
    }

    public List<CarDto> getAllCars() {
        List<Car> carsList = carRepository.findAll();
        return carsList.stream().map(Car::getCarDto).collect(Collectors.toList());
    }

    public List<CarDto> getAllCarsAvailableByEntityStatus() {
        List<Car> carsList = carRepository.findAllByEntityStatus(EntityStatus.AVAILABLE);
        return carsList.stream().map(Car::getCarDto).collect(Collectors.toList());
    }

    public List<CarDto> getAllCarByBranchId(Long branchId) {
        List<Car> resultCarList = new ArrayList<>();

        List<Car> availableCars = carRepository.findAllByBranchId(branchId);
        for (Car availableCar : availableCars) {
            if (Objects.equals(availableCar.getEntityStatus(), EntityStatus.AVAILABLE))  {
                resultCarList.add(availableCar);
            }
        }
        return resultCarList.stream().map(Car::getCarDto).collect(Collectors.toList());
    }


}
