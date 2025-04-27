package org.example.healthnexus1.dto;

import org.example.healthnexus1.entity.Provider;
import java.math.BigDecimal;

public class DoctorDto {

    private Long id;
    private Long userId;  // UserId field
    private String name;
    private String specialty;
    private String education;
    private String experience;
    private String hospital;
    private BigDecimal consultationFee;
    private String about;

    // Constructor
    public DoctorDto(String name, String specialty, String education, String experience,
                     String hospital, BigDecimal consultationFee, String about, Long userId, Long id) {
        this.name = name;
        this.specialty = specialty;
        this.education = education;
        this.experience = experience;
        this.hospital = hospital;
        this.consultationFee = consultationFee;
        this.about = about;
        this.userId = userId;
        this.id = id;
    }

    // Default Constructor
    public DoctorDto() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public BigDecimal getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(BigDecimal consultationFee) {
        this.consultationFee = consultationFee;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    // Method to convert from Provider to DoctorDto
    public DoctorDto convertToDoctorDTO(Provider provider) {
        DoctorDto doctorDTO = new DoctorDto();

        doctorDTO.setId(provider.getId());
//        doctorDTO.setName(provider.getName());
        doctorDTO.setSpecialty(provider.getSpecialty());
        doctorDTO.setEducation(provider.getEducation());
        doctorDTO.setExperience(provider.getExperience());
        doctorDTO.setHospital(provider.getHospital());
        doctorDTO.setConsultationFee(provider.getConsultationFee());
        doctorDTO.setAbout(provider.getAbout());

        // Access userId from the associated User object
        if (provider.getUser() != null) {
            doctorDTO.setUserId(provider.getUser().getId());  // Get userId from the User entity
        }

        return doctorDTO;
    }
}
