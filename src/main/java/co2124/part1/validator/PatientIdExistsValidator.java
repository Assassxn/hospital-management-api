package co2124.part1.validator;

import co2124.part1.respos.PatientRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientIdExistsValidator implements ConstraintValidator<PatientIdExists, Long> {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public boolean isValid(Long patientId, ConstraintValidatorContext context) {
        return patientRepository.existsById(patientId);
    }
}