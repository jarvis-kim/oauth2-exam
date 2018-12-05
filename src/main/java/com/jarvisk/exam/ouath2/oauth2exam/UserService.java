package com.jarvisk.exam.ouath2.oauth2exam;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service("userDetailsService")
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    public AppUser save(AppUser user) {
        return userRepository.save(user);
    }

    public void delete(String username) {
        userRepository.deleteById(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return new User(user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @PostConstruct
    public void init() {
        boolean exists = userRepository.findById("jarvis")
                .isPresent();
        if (!exists) {
            AppUser user = new AppUser();
            user.setUsername("jarvis");
            user.setPassword(passwordEncoder.encode("pwd"));

            userRepository.save(user);
            log.info("add user: {}", user);
        }
    }
}
