package ru.job4j.url_shortcut.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlCodeDTO {
    private String url;
    private int total;
}
