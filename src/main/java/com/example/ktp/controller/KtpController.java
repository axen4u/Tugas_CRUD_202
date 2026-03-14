package com.example.ktp.controller;

import com.example.ktp.dto.KtpDto;
import com.example.ktp.service.KtpService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ktp")
@CrossOrigin("*")
public class KtpController {

    private final KtpService ktpService;

    public KtpController(KtpService ktpService) {
        this.ktpService = ktpService;
    }

    @PostMapping
    public ResponseEntity<KtpDto> createKtp(@Valid @RequestBody KtpDto ktpDto) {
        return new ResponseEntity<>(ktpService.createKtp(ktpDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<KtpDto>> getAllKtp() {
        return ResponseEntity.ok(ktpService.getAllKtp());
    }

    @GetMapping("/{id}")
    public ResponseEntity<KtpDto> getKtpById(@PathVariable Long id) {
        return ResponseEntity.ok(ktpService.getKtpById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<KtpDto> updateKtp(@PathVariable Long id, @Valid @RequestBody KtpDto ktpDto) {
        return ResponseEntity.ok(ktpService.updateKtp(id, ktpDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKtp(@PathVariable Long id) {
        ktpService.deleteKtp(id);
        return ResponseEntity.noContent().build();
    }
}
