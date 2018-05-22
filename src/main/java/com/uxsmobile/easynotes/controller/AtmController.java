package com.uxsmobile.easynotes.controller;

import com.uxsmobile.easynotes.exception.ResourceNotFoundException;
import com.uxsmobile.easynotes.model.Atm;
import com.uxsmobile.easynotes.repository.AtmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AtmController {

    @Autowired
    AtmRepository atmRepository;

    // Get All Atm
    @CrossOrigin
    @GetMapping("/atms")
    public List<Atm> getAllAtms() {
        return atmRepository.findAll();
    }
    // Create a new Atm
    @PostMapping("/atms")
    public Atm createAtm(@Valid @RequestBody Atm atm) {
        return atmRepository.save(atm);
    }
    // Get a Single Atm
    @GetMapping("/atms/{id}")
    public Atm getAtmById(@PathVariable(value = "id") Long atmId) {
        return atmRepository.findById(atmId)
                .orElseThrow(() -> new ResourceNotFoundException("Atm", "id", atmId));
    }
    // Update a Atm
    @PutMapping("/atms/{id}")
    public Atm updateAtm(@PathVariable(value = "id") Long atmId,
                                            @Valid @RequestBody Atm atmDetails) {

        Atm atm = atmRepository.findById(atmId)
                .orElseThrow(() -> new ResourceNotFoundException("Atm", "id", atmId));
        
        atm.setName(atmDetails.getName());
        atm.setAddress(atmDetails.getAddress());
        atm.setPostalCode(atmDetails.getPostalCode());
        atm.setPhone(atmDetails.getPhone());
        atm.setLongitude(atmDetails.getLongitude());
        atm.setLatitude(atmDetails.getLatitude());
        atm.setIngress(atmDetails.getIngress());
        atm.setHalcash(atmDetails.getHalcash());
        atm.setChangePin(atmDetails.getChangePin());

        Atm updatedAtm = atmRepository.save(atm);
        return updatedAtm;
    }
    // Delete a Atm
 // Delete a Atm
    @DeleteMapping("/atms/{id}")
    public ResponseEntity<?> deleteAtm(@PathVariable(value = "id") Long atmId) {
        Atm atm = atmRepository.findById(atmId)
                .orElseThrow(() -> new ResourceNotFoundException("Atm", "id", atmId));

        atmRepository.delete(atm);

        return ResponseEntity.ok().build();
    }
}