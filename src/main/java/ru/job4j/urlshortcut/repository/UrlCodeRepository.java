package ru.job4j.urlshortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.model.UrlCode;

import java.util.List;
import java.util.Optional;

public interface UrlCodeRepository extends CrudRepository<UrlCode, Integer> {
    @Override
    List<UrlCode> findAll();

    Optional<UrlCode> findByCode(String code);
}
