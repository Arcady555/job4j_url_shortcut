package ru.job4j.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import ru.job4j.repository.SiteRepository;
import ru.job4j.repository.model.Site;

import java.util.Optional;

@Data
@Service
@AllArgsConstructor
public class SiteService {
    private final SiteRepository sites;

    public Optional<Site> findById(int id) {
        return sites.findById(id);
    }

    public Site save(Site site) {
        return sites.save(site);
    }
}