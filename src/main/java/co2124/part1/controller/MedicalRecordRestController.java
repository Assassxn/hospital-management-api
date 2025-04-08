package co2124.part1.controller;

import co2124.part1.entities.MedicalRecord;
import co2124.part1.respos.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medical-records")
public class MedicalRecordRestController {
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @PostMapping
    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }
}
