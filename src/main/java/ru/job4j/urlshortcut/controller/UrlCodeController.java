package ru.job4j.urlshortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.urlshortcut.model.UrlCode;
import ru.job4j.urlshortcut.dto.UrlCodeDTO;
import ru.job4j.urlshortcut.service.UrlCodeService;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@RestController
public class UrlCodeController {
    private final UrlCodeService urlCodes;

    @PostMapping("/convert")
    public ResponseEntity<String> convert(@RequestBody UrlCode url) throws NoSuchAlgorithmException {
        url.setCode(generateCodeForUrl(url.getUrl()));
        url.setTotal(0);
        urlCodes.save(url);
        return new ResponseEntity<>(
                url.getCode(),
                HttpStatus.CREATED);
    }

    @GetMapping("/redirect/{code}")
    public ResponseEntity<String> redirect(@PathVariable String code) {
        Optional<UrlCode> optionalUrlCode = urlCodes.findByCode(code);
        if (optionalUrlCode.isPresent()) {
            UrlCode urlCode = optionalUrlCode.get();
            String url = urlCode.getUrl();
            int i = urlCode.getTotal();
            urlCode.setTotal(i + 1);
            urlCodes.save(urlCode);
            return new ResponseEntity<>(
                    "REDIRECT " + url,
                    HttpStatus.valueOf(302)
            );
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/statistic")
    public ResponseEntity<List<UrlCodeDTO>> statistic() {
        return new ResponseEntity<>(
                urlCodes.statistic(),
                HttpStatus.OK
        );
    }

    private String generateCodeForUrl(String url) throws NoSuchAlgorithmException {
        return UUID.randomUUID().toString().replace("-", "").substring(24);
    }
}