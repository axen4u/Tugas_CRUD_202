package com.example.ktp.service.impl;

import com.example.ktp.dto.KtpDto;
import com.example.ktp.entity.Ktp;
import com.example.ktp.exception.DuplicateResourceException;
import com.example.ktp.exception.ResourceNotFoundException;
import com.example.ktp.mapper.KtpMapper;
import com.example.ktp.repository.KtpRepository;
import com.example.ktp.service.KtpService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KtpServiceImpl implements KtpService {

    private final KtpRepository ktpRepository;

    public KtpServiceImpl(KtpRepository ktpRepository) {
        this.ktpRepository = ktpRepository;
    }

    @Override
    public List<KtpDto> getAllKtp() {
        return ktpRepository.findAll().stream()
                .map(KtpMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public KtpDto getKtpById(Long id) {
        Ktp ktp = ktpRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("KTP not found with ID: " + id));
        return KtpMapper.toDto(ktp);
    }

    @Override
    public KtpDto createKtp(KtpDto ktpDto) {
        if (ktpRepository.existsByNomorKtp(ktpDto.getNomorKtp())) {
            throw new DuplicateResourceException("Nomor KTP already exists: " + ktpDto.getNomorKtp());
        }
        Ktp ktp = KtpMapper.toEntity(ktpDto);
        return KtpMapper.toDto(ktpRepository.save(ktp));
    }

    @Override
    public KtpDto updateKtp(Long id, KtpDto ktpDto) {
        Ktp ktp = ktpRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("KTP not found with ID: " + id));

        if (!ktp.getNomorKtp().equals(ktpDto.getNomorKtp()) && 
            ktpRepository.existsByNomorKtp(ktpDto.getNomorKtp())) {
            throw new DuplicateResourceException("Nomor KTP already exists: " + ktpDto.getNomorKtp());
        }

        ktp.setNomorKtp(ktpDto.getNomorKtp());
        ktp.setNamaLengkap(ktpDto.getNamaLengkap());
        ktp.setAlamat(ktpDto.getAlamat());
        ktp.setTanggalLahir(ktpDto.getTanggalLahir());
        ktp.setJenisKelamin(ktpDto.getJenisKelamin());

        return KtpMapper.toDto(ktpRepository.save(ktp));
    }

    @Override
    public void deleteKtp(Long id) {
        Ktp ktp = ktpRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("KTP not found with ID: " + id));
        ktpRepository.delete(ktp);
    }
}
