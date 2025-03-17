package ma.enset.hospital_system.repository;

import ma.enset.hospital_system.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository  extends JpaRepository<Patient, Integer> {
}
