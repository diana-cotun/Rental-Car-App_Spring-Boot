package ro.sda.java64.rentalcarservice_finalproject.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.sda.java64.rentalcarservice_finalproject.entities.Car;
import ro.sda.java64.rentalcarservice_finalproject.enums.EntityStatus;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("select c from Car AS c where c.branch.id = :branchId")
    List<Car> findAllByBranchId(@Param("branchId") Long id);

    List<Car> findAllByEntityStatus(EntityStatus entityStatus);




}
