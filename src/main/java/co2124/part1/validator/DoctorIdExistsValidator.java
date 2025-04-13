package co2124.part1.validator;

import co2124.part1.respos.DoctorRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorIdExistsValidator implements ConstraintValidator<DoctorIdExists, Long> {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public boolean isValid(Long patientId, ConstraintValidatorContext context) {
        if (patientId == null) {
            return true; // null values are handled by @NotNull
        }
        return doctorRepository.existsById(patientId);
    }
}
