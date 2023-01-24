package ru.job4j.urlshortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.urlshortcut.repository.SiteRepository;
import ru.job4j.urlshortcut.repository.model.Site;
import ru.job4j.urlshortcut.repository.model.SiteDTO;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Random;

@AllArgsConstructor
@RestController
public class UserController {
    private final SiteRepository sites;
    private final BCryptPasswordEncoder encoder;

    @PostMapping("/registration")
    public ResponseEntity<SiteDTO> signUp(@Valid @RequestBody Site site) {
        if (Objects.equals(site.getSite(), "хер")) {
            throw new IllegalArgumentException("foul language");
        }
        String login = generateCode();
        String password = generateCode();
        site.setLogin(login);
        site.setPassword(password);
        site.setPassword(encoder.encode(site.getPassword()));
        if (sites.save(site).getId() != 0) {
            return new ResponseEntity<>(
                    new SiteDTO(true, login, password),
                    HttpStatus.CREATED
            );
        } else {
            return new ResponseEntity<>(new SiteDTO(), HttpStatus.BAD_REQUEST);
        }
    }

    private String generateCode() {
        Random random = new Random();
        int intPassword = random.nextInt(9999);
        return Integer.toString(intPassword);
    }
}
