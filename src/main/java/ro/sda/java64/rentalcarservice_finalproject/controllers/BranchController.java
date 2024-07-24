package ro.sda.java64.rentalcarservice_finalproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sda.java64.rentalcarservice_finalproject.dto.BranchDto;
import ro.sda.java64.rentalcarservice_finalproject.entities.Branch;
import ro.sda.java64.rentalcarservice_finalproject.services.BranchService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/branch")
//@CrossOrigin(origins = "http://localhost:4200")

public class BranchController {

    private final BranchService branchService;

    @PostMapping
    public ResponseEntity<BranchDto> create(@RequestBody BranchDto branchDto) {
        BranchDto createdBranch = branchService.createBranch(branchDto);
        if (createdBranch == null) {
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdBranch, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchDto> get(@PathVariable Long id) {
        BranchDto foundBranch = branchService.getBranchById(id);
        if (foundBranch == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foundBranch, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Branch> update(@PathVariable Long id, @RequestBody Branch branch) {
        Branch updatedBranch = branchService.update(id, branch);
        if (updatedBranch == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedBranch, HttpStatus.OK);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable Long id) {
//        Branch deletedBranch = branchService.delete(id);
//        if (deletedBranch == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<List<BranchDto>> getAllBranches() {
        return new ResponseEntity<>(branchService.getAll(), HttpStatus.OK);
    }

}
