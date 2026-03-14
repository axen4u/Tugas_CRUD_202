package com.example.ktp.repository;

import com.example.ktp.entity.Ktp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KtpRepository extends JpaRepository<Ktp, Long> {
    
    boolean existsByNomorKtp(String nomorKtp);
    
    Optional<Ktp> findByNomorKtp(String nomorKtp);
}
