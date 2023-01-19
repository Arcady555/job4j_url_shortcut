package ru.job4j.repository.model;

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