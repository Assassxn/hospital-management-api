package co2124.part1.validator;

import co2124.part1.respos.AppointmentRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentIdExistsValidator implements ConstraintValidator<AppointmentIdExists, Long> {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public boolean isValid(Long appointmentId, ConstraintValidatorContext context) {
        if (appointmentId == null) {
            return true; // null values are handled by @NotNull
        }
        return appointmentRepository.existsById(appointmentId);
    }
}
