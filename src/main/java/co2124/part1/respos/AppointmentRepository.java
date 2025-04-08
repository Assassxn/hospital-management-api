package co2124.part1.respos;

import co2124.part1.entities.Appointment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
    List<Appointment> findAll();
    Appointment findAppointmentById(Long id);
}
