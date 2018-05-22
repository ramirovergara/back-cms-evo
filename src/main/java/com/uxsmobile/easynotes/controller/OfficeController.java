package com.uxsmobile.easynotes.controller;

import com.uxsmobile.easynotes.exception.ResourceNotFoundException;
import com.uxsmobile.easynotes.model.Office;
import com.uxsmobile.easynotes.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OfficeController {

    @Autowired
    OfficeRepository officeRepository;

    // Get All Offices
    @CrossOrigin
    @GetMapping("/offices")
    public List<Office> getAllOffices() {
        return officeRepository.findAll();
    }
    // Create a new Office
    @CrossOrigin
    @PostMapping("/offices")
    public Office createOffice(@Valid @RequestBody Office office) {
        return officeRepository.save(office);
    }
    // Get a Single Office
    @CrossOrigin
    @GetMapping("/offices/{id}")
    public Office getOfficeById(@PathVariable(value = "id") Long officeId) {
        return officeRepository.findById(officeId)
                .orElseThrow(() -> new ResourceNotFoundException("Office", "id", officeId));
    }
    // Update a Office
    @CrossOrigin
    @PutMapping("/offices/{id}")
    public Office updateOffice(@PathVariable(value = "id") Long officeId,
                                            @Valid @RequestBody Office officeDetails) {

        Office office = officeRepository.findById(officeId)
                .orElseThrow(() -> new ResourceNotFoundException("Office", "id", officeId));
        
        office.setName(officeDetails.getName());
        office.setAddress(officeDetails.getAddress());
        office.setPostalCode(officeDetails.getPostalCode());
        office.setPhone(officeDetails.getPhone());
        office.setLongitude(officeDetails.getLongitude());
        office.setLatitude(officeDetails.getLatitude());
        office.setIngress(officeDetails.getIngress());
        office.setHalcash(officeDetails.getHalcash());
        office.setChangePin(officeDetails.getChangePin());

        Office updatedOffice = officeRepository.save(office);
        return updatedOffice;
    }
    // Delete a Office
 // Delete a Office
    @CrossOrigin
    @DeleteMapping("/offices/{id}")
    public ResponseEntity<?> deleteOffice(@PathVariable(value = "id") Long officeId) {
        Office office = officeRepository.findById(officeId)
                .orElseThrow(() -> new ResourceNotFoundException("Office", "id", officeId));

        officeRepository.delete(office);

        return ResponseEntity.ok().build();
    }
}