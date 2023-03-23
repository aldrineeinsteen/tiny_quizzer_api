package com.tinyquizzer.api.tinyquizzerapi.command_line_runners;

import com.tinyquizzer.api.tinyquizzerapi.model.Role;
import com.tinyquizzer.api.tinyquizzerapi.models.User;
import com.tinyquizzer.api.tinyquizzerapi.repositories.UserRepository;
import com.tinyquizzer.api.tinyquizzerapi.services.RoleService;
import com.tinyquizzer.api.tinyquizzerapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
@Profile("dev")
public class DevDataLoader implements CommandLineRunner {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {

        com.tinyquizzer.api.tinyquizzerapi.model.Role userRole = new Role(null, "USER");
        com.tinyquizzer.api.tinyquizzerapi.model.Role adminRole = new Role(null, "ADMIN");

        roleService.save(userRole);
        roleService.save(adminRole);

        // Create sample users
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);

        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(userRole);
        adminRoles.add(adminRole);

        User general_user = new User(
                UUID.randomUUID(),
                "user",
                bCryptPasswordEncoder.encode("userPass"),
                "user@dummy.com",
                true,
                userRoles
        );
        userService.save(general_user);

        User admin_user = new User(
                UUID.randomUUID(),
                "admin",
                bCryptPasswordEncoder.encode("adminPass"),
                "admin@dummy.com",
                true,
                adminRoles
        );
        userService.save(admin_user);
    }
}
