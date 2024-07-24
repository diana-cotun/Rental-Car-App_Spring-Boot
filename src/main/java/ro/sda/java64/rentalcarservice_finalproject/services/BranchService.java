package ro.sda.java64.rentalcarservice_finalproject.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.sda.java64.rentalcarservice_finalproject.dto.BranchDto;
import ro.sda.java64.rentalcarservice_finalproject.entities.Branch;
import ro.sda.java64.rentalcarservice_finalproject.entities.Car;
import ro.sda.java64.rentalcarservice_finalproject.repositories.BranchRepository;
import ro.sda.java64.rentalcarservice_finalproject.repositories.CarRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;

    public BranchDto createBranch(BranchDto branchDto) {
        Branch branch = Branch.builder()
                .branchName(branchDto.getBranchName())
                .city(branchDto.getCity())
                .address(branchDto.getAddress())
                .imageUrl(branchDto.getImageUrl())
                .build();
        Branch createdBranch = branchRepository.save(branch);
        BranchDto newBranchDto = new BranchDto();
        newBranchDto.setId(createdBranch.getId());
        return newBranchDto;
    }

    public BranchDto getBranchById(Long id) {
        Optional<Branch> foundBranch = branchRepository.findById(id);
        if (foundBranch.isEmpty()) {
            return null;
        }
        return foundBranch.get().getBranchDto();
    }

    public Branch update(Long id, Branch branch) {
        Optional<Branch> foundBranch = branchRepository.findById(id);
        if (foundBranch.isEmpty()) {
            return null;
        }
        Branch updatedBranch = foundBranch.get();
        updatedBranch.setBranchName(branch.getBranchName());
        updatedBranch.setCity(branch.getCity());
        updatedBranch.setAddress(branch.getAddress());
        updatedBranch.setImageUrl(branch.getImageUrl());

        return branchRepository.save(updatedBranch);
    }

//    public Branch delete(Long id) {
////        Branch deletedBranch = getById(id);
//        branchRepository.deleteById(id);
////        return deletedBranch;
//    }

    public List<BranchDto> getAll(){
        List<Branch> branchList = branchRepository.findAll();
        return branchList.stream().map(Branch::getBranchDto).collect(Collectors.toList());
    }


}
