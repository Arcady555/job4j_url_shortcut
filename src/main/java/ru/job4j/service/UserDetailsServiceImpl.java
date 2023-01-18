package ru.job4j.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.repository.model.Post;
import ru.job4j.repository.PostRepository;

import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
@Data
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PostRepository sites;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Post> optionalUser = sites.findByLogin(login);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(login);
        }
        return new User(optionalUser.get().getLogin(), optionalUser.get().getPassword(), emptyList());
    }
}