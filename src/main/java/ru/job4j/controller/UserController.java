package ru.job4j.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.repository.SiteRepository;
import ru.job4j.repository.model.Site;
import ru.job4j.repository.model.SiteDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

@Data
@RestController
@RequestMapping("/users")
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

    @ExceptionHandler(value = { IllegalArgumentException.class })
    public void exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response, ObjectMapper objectMapper)
            throws IOException {
        Logger logger = LoggerFactory.getLogger(UserController.class.getSimpleName());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() { {
            put("message", e.getMessage());
            put("type", e.getClass());
        }}));
        logger.error(e.getLocalizedMessage());
    }

    private String generateCode() {
        Random random = new Random();
        int intPassword = random.nextInt(9999);
        return Integer.toString(intPassword);
    }
}