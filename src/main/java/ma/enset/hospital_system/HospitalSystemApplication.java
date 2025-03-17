package ma.enset.hospital_system;

import ma.enset.hospital_system.entities.Patient;
import ma.enset.hospital_system.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class HospitalSystemApplication  implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(HospitalSystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //constr sans params
        Patient patient1 = new Patient();
        patient1.setNom("Mohamed");
        patient1.setDateNaissance(new Date());
        patient1.setMalade(false);
        patient1.setScore(23);

        //constructeur avec params
        Patient patient2 = new Patient(null,"Yassine",new Date(),false,123);

        //builder
        Patient patient3 = Patient.builder()
                .nom("Imane")
                .dateNaissance(new Date())
                .malade(true)
                .score(26)
                .build();

        patientRepository.save(new Patient(null,"Yassine",new Date(),false,123));
        patientRepository.save(new Patient(null,"Najat",new Date(),false,89));
        patientRepository.save(new Patient(null,"Siham",new Date(),false,34));

    }
}
