package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.repository.UrlCodeRepository;
import ru.job4j.urlshortcut.model.UrlCode;
import ru.job4j.urlshortcut.model.UrlCodeDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Service
@AllArgsConstructor
public class UrlCodeService {
    private final UrlCodeRepository urlCodes;

    public List<UrlCode> findAll() {
        return urlCodes.findAll();
    }

    public Optional<UrlCode> findById(int id) {
        return urlCodes.findById(id);
    }

    public UrlCode save(UrlCode urlCode) {
        return urlCodes.save(urlCode);
    }

    public List<UrlCodeDTO> statistic() {
        List<UrlCode> urlCodeList = findAll();
        List<UrlCodeDTO> list = new ArrayList<>();
        for (UrlCode urlCode : urlCodeList) {
            list.add(new UrlCodeDTO(urlCode.getUrl(), urlCode.getTotal()));
        }
        return list;
    }

    public UrlCode findByCode(String code) {
        return urlCodes.findByCode(code);
    }
}