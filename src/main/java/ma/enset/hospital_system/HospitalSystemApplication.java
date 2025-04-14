package ma.enset.hospital_system;

import ma.enset.hospital_system.entities.Patient;
import ma.enset.hospital_system.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

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

//        patientRepository.save(new Patient(null,"Yassine",new Date(),false,123));
//        patientRepository.save(new Patient(null,"Najat",new Date(),false,89));
//        patientRepository.save(new Patient(null,"Siham",new Date(),false,34));

    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager) {
        PasswordEncoder passwordEncoder = passwordEncoder();
        return args -> {
            UserDetails ud1 = jdbcUserDetailsManager.loadUserByUsername("user11");
            UserDetails ud2 = jdbcUserDetailsManager.loadUserByUsername("user22");
            UserDetails ud3 = jdbcUserDetailsManager.loadUserByUsername("admin2");
            if(ud1 == null)
                jdbcUserDetailsManager.createUser(
                        User.withUsername("user11").password(passwordEncoder.encode("1234")).roles("USER").build()
                );
            if(ud2 == null)
                jdbcUserDetailsManager.createUser(
                        User.withUsername("user22").password(passwordEncoder.encode("1234")).roles("USER").build()
                );
            if(ud3 == null)
                jdbcUserDetailsManager.createUser(
                        User.withUsername("admin2").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build()
                );

//            jdbcUserDetailsManager.createUser(
//                    User.withUsername("user11").password(passwordEncoder.encode("1234")).roles("USER").build()
//            );
//            jdbcUserDetailsManager.createUser(
//                    User.withUsername("user22").password(passwordEncoder.encode("1234")).roles("USER").build()
//            );
//            jdbcUserDetailsManager.createUser(
//                    User.withUsername("admin2").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build()
//            );
        };
    }
}
