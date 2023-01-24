package ru.job4j.url_shortcut.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SiteDTO {
    private boolean registration;
    private String login;
    private String password;
}