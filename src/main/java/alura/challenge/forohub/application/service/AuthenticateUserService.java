package alura.challenge.forohub.application.service;

import alura.challenge.forohub.application.port.out.persistence.UserRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateUserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(@NotBlank String username) throws UsernameNotFoundException {
       var user = userRepository.findUserByEmail(username);

       if(user.isEmpty()) {
           throw new UsernameNotFoundException("User was not found.");
       }

        return user.get();
    }
}
