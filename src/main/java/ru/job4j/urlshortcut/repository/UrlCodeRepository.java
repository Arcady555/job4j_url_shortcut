package ru.job4j.urlshortcut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.model.UrlCode;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UrlCodeRepository extends CrudRepository<UrlCode, Integer> {
    @Override
    List<UrlCode> findAll();

    Optional<UrlCode> findByCode(String code);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(
            value = "update UrlCode u set u.total = :#{#urlCode.total} + 1 where u.id = :#{#urlCode.id}"
    )
    int incrementTotal(UrlCode urlCode);
}
