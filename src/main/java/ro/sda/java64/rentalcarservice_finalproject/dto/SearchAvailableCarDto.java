package ro.sda.java64.rentalcarservice_finalproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class SearchAvailableCarDto {

    private Long branchId;
    private LocalDate startDate;
    private LocalDate endDate;
}
