package ro.sda.java64.rentalcarservice_finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BranchDto {
    private Long id;
    private String branchName;
    private String city;
    private String address;
    private String imageUrl;
}
