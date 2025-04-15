package com.example.part1.respos;

import com.example.part1.entities.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PatientRepository extends CrudRepository<Patient, Long> {
    List<Patient> findAll();
    Patient findPatientById(Long id);
    boolean existsById(Long id);
}
