package ro.sda.java64.rentalcarservice_finalproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ro.sda.java64.rentalcarservice_finalproject.dto.CarDto;
import ro.sda.java64.rentalcarservice_finalproject.dto.ReservationDto;
import ro.sda.java64.rentalcarservice_finalproject.dto.SearchAvailableCarDto;
import ro.sda.java64.rentalcarservice_finalproject.entities.Car;
import ro.sda.java64.rentalcarservice_finalproject.entities.Reservation;
import ro.sda.java64.rentalcarservice_finalproject.exceptions.CustomDatabaseException;
import ro.sda.java64.rentalcarservice_finalproject.services.ReservationService;

import java.util.List;
import java.util.Set;


@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationDto reservationDto) throws CustomDatabaseException {
        try {
            ReservationDto reservation = reservationService.saveReservation(reservationDto);
            if (reservation == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(reservation, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new CustomDatabaseException("Something was wrong");
        }
    }

    @GetMapping
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
         return new ResponseEntity<>(reservationService.getAllReservations(), HttpStatus.OK);
    }

    @GetMapping("/{reservationId}/{status}")
    public ResponseEntity<?> changeStatusReservation(@PathVariable Long reservationId, @PathVariable String status) {
        boolean succes = reservationService.changeReservationStatus(reservationId, status);
        if (succes) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/my-reservations/{userId}")
    public ResponseEntity<List<ReservationDto>> getAllReservationsByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(reservationService.getAllReservationsByUserId(userId), HttpStatus.OK);
    }




    @GetMapping("/{id}")
    public ResponseEntity<Reservation> get(@PathVariable Long id) {
        Reservation foundReservation = reservationService.getById(id);
        if (foundReservation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foundReservation, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable Long id, @RequestBody ReservationDto reservationDto) {
        ReservationDto updatedReservation = reservationService.updateReservation(id, reservationDto);
        if (updatedReservation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Reservation deletedReservation = reservationService.delete(id);
        if (deletedReservation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
