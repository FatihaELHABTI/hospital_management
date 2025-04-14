package ma.enset.hospital_system.security.services;

import lombok.AllArgsConstructor;
import ma.enset.hospital_system.security.entities.AppUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = accountService.loadUserByUsername(username);
        if(appUser == null) throw new UsernameNotFoundException(String.format("User %s not found",username));
        UserDetails userDetails = User
                .withUsername(username)
                .password(appUser.getPassword())
                .roles(appUser.getRoles().stream().map(e -> e.getRole()).toArray(String[]::new))
                .build();
        return userDetails;
    }
}
