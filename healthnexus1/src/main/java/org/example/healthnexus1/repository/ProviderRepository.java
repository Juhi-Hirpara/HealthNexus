package org.example.healthnexus1.repository;

import org.example.healthnexus1.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
    List<Provider> findBySpecialtyIgnoreCase(String specialty);
//    Optional<Provider> findByUserId(Long userId);
    Optional<Provider> findByUserId(Long userId);

}
