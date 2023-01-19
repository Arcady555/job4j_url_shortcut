package ru.job4j.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.repository.model.Site;
import ru.job4j.service.SiteService;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@RestController
//@RequestMapping("/sites")
public class SiteController {
    private final SiteService sites;

    @GetMapping("/")
    public List<Site> findAll() {
        return this.sites.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Site> findById(@Valid @PathVariable int id) {
        var site = this.sites.findById(id);
        if (site.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Site is not found. Please, check ID");
        }
        return new ResponseEntity<>(
                site.orElse(new Site()),
                HttpStatus.OK
        );
    }
/*
    @PatchMapping("/{id}")
    public Site patch(@Valid @RequestBody Site site) throws InvocationTargetException, IllegalAccessException {
        var current = sites.findById(site.getId());
        if (current.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        HashMap<String, Method> namePerMethod = sites.methods(site);
        for (var name : namePerMethod.keySet()) {
            if (name.startsWith("get")) {
                var getMethod = namePerMethod.get(name);
                var setMethod = namePerMethod.get(name.replace("get", "set"));
                if (setMethod == null) {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "Impossible invoke set method from object : " + current.get() + ", Check set and get pairs."
                    );
                }
                var newValue = getMethod.invoke(site);
                if (newValue != null) {
                    setMethod.invoke(current, newValue);
                }
            }
        }
        sites.save(site);
        return current.get();
    }

    @PostMapping("/")
    public ResponseEntity<Boolean> create(@Valid @RequestBody Site site) {
        return this.sites.save(site) ? new ResponseEntity<>(
                this.sites.save(site),
                HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@Valid @RequestBody Site site) {
        return this.sites.save(site) ? ResponseEntity.ok().build()
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }   */
    @PostMapping("/convert")
        public ResponseEntity<Boolean> convert(@Valid @RequestBody Site site) {
            return this.sites.save(site) ? new ResponseEntity<>(
                    this.sites.save(site),
                    HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}