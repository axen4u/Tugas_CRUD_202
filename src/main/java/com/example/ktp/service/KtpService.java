package com.example.ktp.service;

import com.example.ktp.dto.KtpDto;
import java.util.List;

public interface KtpService {
    List<KtpDto> getAllKtp();
    KtpDto getKtpById(Long id);
    KtpDto createKtp(KtpDto ktpDto);
    KtpDto updateKtp(Long id, KtpDto ktpDto);
    void deleteKtp(Long id);
}
