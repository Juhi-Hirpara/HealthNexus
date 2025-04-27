package org.example.healthnexus1.dto;

public class ProviderDTO {
    private Long providerId;
    private Long userId;
    private String specialty;
    private String education;
    private String experience;
    private String hospital;
    private String consultationFee;
    private String about;

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(String consultationFee) {
        this.consultationFee = consultationFee;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    // constructor
    public ProviderDTO(Long providerId, Long userId, String specialty, String education, String experience,
                       String hospital, String consultationFee, String about) {
        this.providerId = providerId;
        this.userId = userId;
        this.specialty = specialty;
        this.education = education;
        this.experience = experience;
        this.hospital = hospital;
        this.consultationFee = consultationFee;
        this.about = about;
    }

    // getters and setters
}

