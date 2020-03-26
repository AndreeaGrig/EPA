package apbdoo.onlineLib.services;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import apbdoo.onlineLib.aspects.TrackExecutionTime;
import apbdoo.onlineLib.domain.Role;
import apbdoo.onlineLib.domain.User;
import apbdoo.onlineLib.repositories.RoleRepository;
import apbdoo.onlineLib.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @TrackExecutionTime
    @Override
    public User findByEmail(String email){
        //TODO:
//        log.info("Retrieve user with email: "+email);
        return userRepository.findByEmail(email);
    }

    @TrackExecutionTime
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null){
            //TODO:
//            log.error("User not found for email: "+email);
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @TrackExecutionTime
    @Override
    public void save(User user) {
        //TODO:
//        log.info("Saving user with email "+user.getEmail()+" with role ROLE_USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByName("ROLE_USER");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @TrackExecutionTime
    @Override
    public User findByUsername(String username) {
        //TODO:
//        log.info("Retrieving user with username "+username);
        return userRepository.findByUsername(username);
    }
}
