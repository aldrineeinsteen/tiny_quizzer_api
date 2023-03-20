package com.tinyquizzer.api.tinyquizzerapi.command_line_runners;

import com.tinyquizzer.api.tinyquizzerapi.models.User;
import com.tinyquizzer.api.tinyquizzerapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Profile("dev")
public class DevDataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public void run(String... args) throws Exception {

        User general_user = new User(
                UUID.randomUUID(),
                "user",
                bCryptPasswordEncoder.encode("userPass"),
                "user@dummy.com",
                true
        );
        userRepository.save(general_user);

        User admin_user = new User(
                UUID.randomUUID(),
                "admin",
                bCryptPasswordEncoder.encode("adminPass"),
                "admin@dummy.com",
                true
        );
        userRepository.save(admin_user);
        userRepository.flush();
    }
}
