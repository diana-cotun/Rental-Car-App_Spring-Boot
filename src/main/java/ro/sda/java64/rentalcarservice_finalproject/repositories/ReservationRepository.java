package ro.sda.java64.rentalcarservice_finalproject.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.sda.java64.rentalcarservice_finalproject.entities.Branch;
import ro.sda.java64.rentalcarservice_finalproject.entities.Car;
import ro.sda.java64.rentalcarservice_finalproject.entities.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByCarId(Long carId);

    List<Reservation> findAllByUserId(Long userId);

    @Modifying
    @Query("UPDATE Reservation r SET r.car = NULL WHERE r.car.id = :carId")
    void setCarToNull(@Param("carId") Long carId);


}
