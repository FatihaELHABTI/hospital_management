package ma.enset.hospital_system.security.services;

import ma.enset.hospital_system.security.entities.AppRole;
import ma.enset.hospital_system.security.entities.AppUser;

public interface AccountService {
    AppUser addNewUser(String username, String password, String confirmPassword, String email);
    AppRole addNewRole(String role);
    void addRoleToUser(String username, String role);
    void removeRoleFromUser(String username, String role);
    AppUser loadUserByUsername(String username);
}