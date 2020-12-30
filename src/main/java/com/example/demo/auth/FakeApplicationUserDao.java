package com.example.demo.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.demo.security.ApplicationUserRole.*;

@Repository
public class FakeApplicationUserDao implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    public FakeApplicationUserDao(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUserName(String userName) {
        return getApplicationUsers()
                .stream()
                .filter(u -> u.getUsername().equals(userName))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers(){
        List<ApplicationUser> applicationUsers = List.of(
            new ApplicationUser("telly",passwordEncoder.encode("kikala"),
                    EMPLOYEE.getGrantedAuthorities(),true),
            new ApplicationUser("resp",passwordEncoder.encode("password"),
                    RESPONSIBLE.getGrantedAuthorities(),true),
            new ApplicationUser("admin",passwordEncoder.encode("password123"),
                    ADMIN.getGrantedAuthorities(),true)
        );
        return applicationUsers;
    }
}
