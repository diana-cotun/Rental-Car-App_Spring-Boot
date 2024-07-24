package ro.sda.java64.rentalcarservice_finalproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.sda.java64.rentalcarservice_finalproject.dto.ReservationDto;
import ro.sda.java64.rentalcarservice_finalproject.entities.Branch;
import ro.sda.java64.rentalcarservice_finalproject.entities.Car;
import ro.sda.java64.rentalcarservice_finalproject.entities.Reservation;
import ro.sda.java64.rentalcarservice_finalproject.entities.User;
import ro.sda.java64.rentalcarservice_finalproject.enums.ReservationStatus;
import ro.sda.java64.rentalcarservice_finalproject.exceptions.CustomDatabaseException;
import ro.sda.java64.rentalcarservice_finalproject.repositories.BranchRepository;
import ro.sda.java64.rentalcarservice_finalproject.repositories.CarRepository;
import ro.sda.java64.rentalcarservice_finalproject.repositories.ReservationRepository;
import ro.sda.java64.rentalcarservice_finalproject.repositories.UserRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final BranchRepository branchRepository;

    public ReservationDto saveReservation(ReservationDto reservationDto) throws CustomDatabaseException {
        try {
            Reservation createdReservation = buildAReservationEntity(reservationDto);
            if (createdReservation != null) {
                reservationRepository.save(createdReservation);
                return createdReservation.getReservationDto();
            }
        } catch (Exception e) {
            throw new CustomDatabaseException(e.getMessage());
        }
        return null;
    }


    private Reservation buildAReservationEntity(ReservationDto reservationDto) {

        Car car = getCarDtoForReservation(reservationDto);

        Optional<User> optionalUser = userRepository.findById(reservationDto.getUserId());
        Branch loanBranch = getLoanBranchForReservation(reservationDto.getLoanBranchId());
        Optional<Branch> optionalReturnBranch = branchRepository.findById(reservationDto.getReturnBranchId());
        Long days = getDaysOfReservation(reservationDto);

        if (car != null && optionalUser.isPresent() && (car.getBranch().equals(loanBranch)) && optionalReturnBranch.isPresent()) {
            Reservation reservation;
            reservation = Reservation.builder()
                    .dateOfBooking(LocalDate.now())
                    .user(optionalUser.get())
                    .car(car)
                    .fromDate(reservationDto.getFromDate())
                    .toDate(reservationDto.getToDate())
                    .days(days)
                    .totalPrice(car.getPricePerDay() * days)
                    .loanBranch(loanBranch)
                    .returnBranch(optionalReturnBranch.get())
                    .reservationStatus(ReservationStatus.PENDING)
                    .build();

            car.setBranch(optionalReturnBranch.get());
            carRepository.save(car);

            return reservation;
        }
        return null;
    }

    private Branch getLoanBranchForReservation(Long branchId) {
        Optional<Branch> optionalLoanBranch = branchRepository.findById(branchId);
        return optionalLoanBranch.orElse(null);
    }

    private Car getCarDtoForReservation(ReservationDto reservationDto) {
        Optional<Car> optionalCar = carRepository.findById(reservationDto.getCarId());
        Car car = new Car();
        if (optionalCar.isPresent()) {
            car = optionalCar.get();
        }

        LocalDate startDate = reservationDto.getFromDate();
        LocalDate endDate = reservationDto.getToDate();

        List<ReservationDto> reservationList = getAllReservations();
        for (ReservationDto reservation : reservationList) {
            if (startDate.isEqual(reservation.getToDate()) ||
                    startDate.isBefore(reservation.getToDate()) && endDate.isAfter(reservation.getFromDate()) ||
                    endDate.isEqual(reservation.getFromDate())) {
                Optional<Car> reservedCarDto = carRepository.findById(reservation.getCarId());
                if (reservedCarDto.isPresent() && Objects.equals(reservedCarDto.get().getId(), car.getId())) {
                    return null;
                }
            }
        }
        return car;
    }

    private Long getDaysOfReservation(ReservationDto reservationDto) {
        return ChronoUnit.DAYS.between(reservationDto.getFromDate(), reservationDto.getToDate()) + 1;
    }


    public Reservation getById(Long id) {
        Optional<Reservation> foundReservation = reservationRepository.findById(id);
        if (foundReservation.isEmpty()) {
            return null;
        }
        return foundReservation.get();
    }

    public ReservationDto updateReservation(Long id, ReservationDto reservationDto) {
        Optional<Reservation> foundReservation = reservationRepository.findById(id);
        if (foundReservation.isEmpty()) {
            return null;
        }
        Reservation existingReservation = foundReservation.get();
        Optional<Car> foundCar = carRepository.findById(reservationDto.getCarId());
        Optional<Branch> foundReturnBranch = branchRepository.findById(reservationDto.getReturnBranchId());
        if (foundCar.isPresent() && foundReturnBranch.isPresent()) {
            existingReservation.setCar(foundCar.get());
            existingReservation.setReturnBranch(foundReturnBranch.get());
        }
        existingReservation.setFromDate(reservationDto.getFromDate());
        existingReservation.setToDate(reservationDto.getToDate());

        return reservationRepository.save(existingReservation).getReservationDto();
    }

    public boolean changeReservationStatus(Long reservationId, String status) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        if (optionalReservation.isEmpty()) {
            return false;
        }
        Reservation existingReservation = optionalReservation.get();
        if (Objects.equals(status, "Approve")) {
            existingReservation.setReservationStatus(ReservationStatus.APPROVED);
        } else {
            existingReservation.setReservationStatus(ReservationStatus.REJECTED);
        }
        reservationRepository.save(existingReservation);
        return true;
    }

    public Reservation delete(Long id) {
        Reservation deletedReservation = getById(id);
        reservationRepository.deleteById(id);
        return deletedReservation;
    }

    public List<ReservationDto> getAllReservations() {
        return reservationRepository.findAll().stream().map(Reservation::getReservationDto).collect(Collectors.toList());
    }

    public List<ReservationDto> getAllReservationsByUserId(Long userId) {
        return reservationRepository.findAllByUserId(userId).stream().map(Reservation::getReservationDto).collect(Collectors.toList());
    }


}
