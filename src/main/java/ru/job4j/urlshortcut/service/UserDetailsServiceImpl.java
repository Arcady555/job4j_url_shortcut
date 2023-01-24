package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.repository.model.Site;
import ru.job4j.urlshortcut.repository.SiteRepository;

import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
@Data
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final SiteRepository sites;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Site> optionalUser = sites.findByLogin(login);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(login);
        }
        return new User(optionalUser.get().getLogin(), optionalUser.get().getPassword(), emptyList());
    }
}