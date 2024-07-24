package ro.sda.java64.rentalcarservice_finalproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.sda.java64.rentalcarservice_finalproject.entities.Branch;
import ro.sda.java64.rentalcarservice_finalproject.entities.Car;
import ro.sda.java64.rentalcarservice_finalproject.entities.User;
import ro.sda.java64.rentalcarservice_finalproject.enums.ReservationStatus;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {

    private Long id;
    private LocalDate dateOfBooking;
    private Long userId;
    private Long carId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Long days;
    private Double totalPrice;
    private Long loanBranchId;
    private Long returnBranchId;
    private ReservationStatus reservationStatus;
    private String userEmail;
    private String userName;




}
