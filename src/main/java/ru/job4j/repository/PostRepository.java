package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.repository.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Integer> {
    @Override
    List<Post> findAll();

    Optional<Post> findByLogin(String login);
}