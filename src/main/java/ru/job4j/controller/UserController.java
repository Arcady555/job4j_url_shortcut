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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Data
@RestController
@RequestMapping("/users")
public class UserController {
    private final SiteRepository posts;
    private final BCryptPasswordEncoder encoder;
    private final ObjectMapper objectMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class.getSimpleName());

    @PostMapping("/sign-up")
    public ResponseEntity<Site> signUp(@Valid @RequestBody Site post) {
        if (Objects.equals(post.getLogin(), "хер") || Objects.equals(post.getPassword(), "хер")) {
            throw new IllegalArgumentException("foul language login or password");
        }
        post.setPassword(encoder.encode(post.getPassword()));
        return new ResponseEntity<>(this.posts.save(post), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Site> findAll() {
        return posts.findAll();
    }

    @ExceptionHandler(value = { IllegalArgumentException.class })
    public void exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() { {
            put("message", e.getMessage());
            put("type", e.getClass());
        }}));
        LOGGER.error(e.getLocalizedMessage());
    }
}