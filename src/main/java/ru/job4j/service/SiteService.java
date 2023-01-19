package ru.job4j.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import ru.job4j.repository.SiteRepository;
import ru.job4j.repository.model.Site;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Data
@Service
@AllArgsConstructor
public class SiteService {
    private final SiteRepository posts;

    public List<Site> findAll() {
        return posts.findAll();
    }

    public Optional<Site> findById(int id) {
        return posts.findById(id);
    }

    public boolean save(Site post) {
        var temp = findById(post.getId());
        temp.ifPresent(posts::save);
        return temp.isPresent();
    }

    public HashMap<String, Method> methods(Site post) {
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

    public String generate() {
        return "";
    }
}