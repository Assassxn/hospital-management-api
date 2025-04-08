package co2124.part1.respos;

import co2124.part1.entities.Doctor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {
    List<Doctor> findAll();
    Doctor findDoctorById(Long id);
}
