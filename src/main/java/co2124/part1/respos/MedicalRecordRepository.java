package co2124.part1.respos;

import co2124.part1.entities.MedicalRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MedicalRecordRepository extends CrudRepository<MedicalRecord, Long> {
    List<MedicalRecord> findAll();
}
