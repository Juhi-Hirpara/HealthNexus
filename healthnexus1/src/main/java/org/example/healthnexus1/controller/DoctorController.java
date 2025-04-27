package org.example.healthnexus1.controller;

import org.example.healthnexus1.dto.DoctorDto;
import org.example.healthnexus1.entity.Provider;
import org.example.healthnexus1.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "http://localhost:3000") // optional, allows frontend to call API
public class DoctorController {

    @Autowired
    private ProviderRepository providerRepository;

    @GetMapping("/category/{specialty}")
    public List<DoctorDto> getDoctorsBySpecialty(@PathVariable String specialty) {
        List<Provider> providers = providerRepository.findBySpecialtyIgnoreCase(specialty);
        return providers.stream().map(provider -> {
            String name = provider.getUser().getFullName(); // assuming User has getFullName() method
            Long userId = provider.getUser().getId();       // fetch userId from related User

            // Return DoctorDto with all required fields
            return new DoctorDto(
                    name,
                    provider.getSpecialty(),
                    provider.getEducation(),
                    provider.getExperience(),
                    provider.getHospital(),
                    provider.getConsultationFee(),
                    provider.getAbout(),
                    userId,  // userId is passed as an argument
                    provider.getId()  // provider id is passed as an argument
            );
        }).collect(Collectors.toList());
    }

}
