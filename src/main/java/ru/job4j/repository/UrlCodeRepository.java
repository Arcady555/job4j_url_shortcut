package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.repository.model.UrlCode;

import java.util.List;

public interface UrlCodeRepository extends CrudRepository<UrlCode, Integer> {
    @Override
    List<UrlCode> findAll();

    UrlCode findByCode(String code);
}
