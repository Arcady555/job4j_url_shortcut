package ru.job4j.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import ru.job4j.repository.PostRepository;
import ru.job4j.repository.model.Post;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Data
@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository posts;

    public List<Post> findAll() {
        return posts.findAll();
    }

    public Optional<Post> findById(int id) {
        return posts.findById(id);
    }

    public boolean save(Post post) {
        var temp = findById(post.getId());
        temp.ifPresent(posts::save);
        return temp.isPresent();
    }

    public HashMap<String, Method> methods(Post post) {
        var methods = post.getClass().getDeclaredMethods();
        var namePerMethod = new HashMap<String, Method>();
        for (var method: methods) {
            var name = method.getName();
            if (name.startsWith("get") || name.startsWith("set")) {
                namePerMethod.put(name, method);
            }
        }
        return namePerMethod;
    }
}