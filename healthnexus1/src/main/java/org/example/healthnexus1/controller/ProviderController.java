package org.example.healthnexus1.controller;

import org.example.healthnexus1.dto.ProviderDTO;
import org.example.healthnexus1.entity.Provider;
import org.example.healthnexus1.entity.User;
import org.example.healthnexus1.repository.ProviderRepository;
import org.example.healthnexus1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/providers")
@CrossOrigin(origins = "http://localhost:3000")
public class ProviderController {

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private UserRepository userRepository;

    // Create new provider profile
    @PostMapping
    public ResponseEntity<?> createProvider(@RequestBody ProviderRequest req) {
        Optional<User> userOpt = userRepository.findById(req.getUserId());
        if (!userOpt.isPresent()) {
            return ResponseEntity.badRequest().body("User not found.");
        }

        Provider provider = new Provider();
        provider.setUser(userOpt.get());
        mapProviderFields(provider, req);
        providerRepository.save(provider);

        return ResponseEntity.ok(provider);
    }

    // Update existing provider profile
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProvider(@PathVariable Long id, @RequestBody ProviderRequest req) {
        Optional<Provider> providerOpt = providerRepository.findById(id);
        if (!providerOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Provider provider = providerOpt.get();
        mapProviderFields(provider, req);
        providerRepository.save(provider);

        return ResponseEntity.ok(provider);
    }
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<?> getProviderByUserId(@PathVariable Long userId) {
//        Optional<Provider> providerOpt = providerRepository.findByUserId(userId);
//        if (!providerOpt.isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(providerOpt.get());
//    }
@GetMapping("/user/{userId}")
public ResponseEntity<?> getProviderByUserId(@PathVariable Long userId) {
    Optional<Provider> providerOpt = providerRepository.findByUserId(userId);
    if (!providerOpt.isPresent()) {
        return ResponseEntity.notFound().build();
    }

    Provider provider = providerOpt.get();
    ProviderDTO dto = new ProviderDTO(
            provider.getId(),
            provider.getUser().getId(),
            provider.getSpecialty(),
            provider.getEducation(),
            provider.getExperience(),
            provider.getHospital(),
            provider.getConsultationFee().toPlainString(),
            provider.getAbout()

    );

    return ResponseEntity.ok(dto);
}
    private void mapProviderFields(Provider provider, ProviderRequest req) {
        User user = new User(); // Create a new User object
        user.setId(req.getUserId()); // Set only the ID (JPA will recognize it as a reference)

        provider.setUser(user); // Set user reference in provider
        provider.setSpecialty(req.getSpecialty());
        provider.setEducation(req.getEducation());
        provider.setExperience(req.getExperience());
        provider.setHospital(req.getHospital());
        provider.setConsultationFee(new BigDecimal(req.getConsultationFee()));
        provider.setAbout(req.getAbout());
        // Availability: You can store it as JSON string or in another table
        // Not implemented here since your entity doesn't include it
    }


    // DTO class
    public static class ProviderRequest {
        private Long userId;
        private String specialty;
        private String education;
        private String experience;
        private String hospital;
        private String consultationFee;
        private String about;

        // Getters & Setters
        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }
        public String getSpecialty() { return specialty; }
        public void setSpecialty(String specialty) { this.specialty = specialty; }
        public String getEducation() { return education; }
        public void setEducation(String education) { this.education = education; }
        public String getExperience() { return experience; }
        public void setExperience(String experience) { this.experience = experience; }
        public String getHospital() { return hospital; }
        public void setHospital(String hospital) { this.hospital = hospital; }
        public String getConsultationFee() { return consultationFee; }
        public void setConsultationFee(String consultationFee) { this.consultationFee = consultationFee; }
        public String getAbout() { return about; }
        public void setAbout(String about) { this.about = about; }
    }
}
//org.example.healthnexus1.controller;
//
//import org.example.healthnexus1.entity.Provider;
//import org.example.healthnexus1.entity.User;
//import org.example.healthnexus1.repository.ProviderRepository;
//import org.example.healthnexus1.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//        import java.math.BigDecimal;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/providers")
//@CrossOrigin(origins = "http://localhost:3000")
//public class ProviderController {
//
//    @Autowired
//    private ProviderRepository providerRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    // Create new provider profile
//    @PostMapping
//    public ResponseEntity<?> createProvider(@RequestBody ProviderRequest req) {
//        Optional<User> userOpt = userRepository.findById(req.getUserId());
//        if (!userOpt.isPresent()) {
//            return ResponseEntity.badRequest().body("User not found.");
//        }
//
//        Provider provider = new Provider();
//        provider.setUser(userOpt.get());
//        mapProviderFields(provider, req);
//        providerRepository.save(provider);
//
//        return ResponseEntity.ok(provider);
//    }
//
//    // Update existing provider profile
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateProvider(@PathVariable Long id, @RequestBody ProviderRequest req) {
//        Optional<Provider> providerOpt = providerRepository.findById(id);
//        if (!providerOpt.isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        Provider provider = providerOpt.get();
//        mapProviderFields(provider, req);
//        providerRepository.save(provider);
//
//        return ResponseEntity.ok(provider);
//    }
//
//    private void mapProviderFields(Provider provider, ProviderRequest req) {
//        provider.setSpecialty(req.getSpecialty());
//        provider.setEducation(req.getEducation());
//        provider.setExperience(req.getExperience());
//        provider.setHospital(req.getHospital());
//        provider.setConsultationFee(new BigDecimal(req.getConsultationFee()));
//        provider.setAbout(req.getAbout());
//        // Availability: You can store it as JSON string or in another table
//        // Not implemented here since your entity doesn't include it
//    }
//
//
//    // DTO class
//    public static class ProviderRequest {
//        private Long userId;
//        private String specialty;
//        private String education;
//        private String experience;
//        private String hospital;
//        private String consultationFee;
//        private String about;
//
//        // Getters & Setters
//        public Long getUserId() { return userId; }
//        public void setUserId(Long userId) { this.userId = userId; }
//        public String getSpecialty() { return specialty; }
//        public void setSpecialty(String specialty) { this.specialty = specialty; }
//        public String getEducation() { return education; }
//        public void setEducation(String education) { this.education = education; }
//        public String getExperience() { return experience; }
//        public void setExperience(String experience) { this.experience = experience; }
//        public String getHospital() { return hospital; }
//        public void setHospital(String hospital) { this.hospital = hospital; }
//        public String getConsultationFee() { return consultationFee; }
//        public void setConsultationFee(String consultationFee) { this.consultationFee = consultationFee; }
//        public String getAbout() { return about; }
//        public void setAbout(String about) { this.about = about; }
//    }
//}
