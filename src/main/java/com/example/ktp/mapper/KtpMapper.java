package com.example.ktp.mapper;

import com.example.ktp.dto.KtpDto;
import com.example.ktp.entity.Ktp;

public class KtpMapper {

    public static KtpDto toDto(Ktp entity) {
        if (entity == null) return null;
        return new KtpDto(
                entity.getId(),
                entity.getNomorKtp(),
                entity.getNamaLengkap(),
                entity.getAlamat(),
                entity.getTanggalLahir(),
                entity.getJenisKelamin()
        );
    }

    public static Ktp toEntity(KtpDto dto) {
        if (dto == null) return null;
        return new Ktp(
                dto.getId(),
                dto.getNomorKtp(),
                dto.getNamaLengkap(),
                dto.getAlamat(),
                dto.getTanggalLahir(),
                dto.getJenisKelamin()
        );
    }
}
