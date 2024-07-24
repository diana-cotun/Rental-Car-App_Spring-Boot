package ro.sda.java64.rentalcarservice_finalproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ro.sda.java64.rentalcarservice_finalproject.dto.ReservationDto;
import ro.sda.java64.rentalcarservice_finalproject.enums.ReservationStatus;

import java.time.LocalDate;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateOfBooking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE) //de testat cum functioneaza
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "car_id", nullable = false)
    @JsonIgnore
    private Car car;

    private LocalDate fromDate;
    private LocalDate toDate;
    private Long days;
    private Double totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loanBranch_id", nullable = false)
    @JsonIgnore
    private Branch loanBranch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "returnBranch_id", nullable = false)
    @JsonIgnore
    private Branch returnBranch;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    public ReservationDto getReservationDto() {
        ReservationDto reservationDto = ReservationDto.builder()
                .id(id)
                .dateOfBooking(dateOfBooking)
                .userId(user.getId())
                .carId(car.getId())
                .fromDate(fromDate)
                .toDate(toDate)
                .days(days)
                .totalPrice(totalPrice)
                .loanBranchId(loanBranch.getId())
                .returnBranchId(returnBranch.getId())
                .reservationStatus(reservationStatus)
                .userName(user.getName())
                .userEmail(user.getEmail())
                .build();
        return reservationDto;
    }

}
