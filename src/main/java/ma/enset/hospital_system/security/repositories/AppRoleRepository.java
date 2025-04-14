package ma.enset.hospital_system.security.repositories;


import ma.enset.hospital_system.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, String> {

}