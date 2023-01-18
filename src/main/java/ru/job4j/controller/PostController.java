package ru.job4j.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.repository.model.Post;
import ru.job4j.service.PostService;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService posts;

    @GetMapping("/")
    public List<Post> findAll() {
        return this.posts.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@Valid @PathVariable int id) {
        var post = this.posts.findById(id);
        if (post.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Person is not found. Please, check ID");
        }
        return new ResponseEntity<>(
                post.orElse(new Post()),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{id}")
    public Post patch(@Valid @RequestBody Post post) throws InvocationTargetException, IllegalAccessException {
        var current = posts.findById(post.getId());
        if (current.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        HashMap<String, Method> namePerMethod = posts.methods(post);
        for (var name : namePerMethod.keySet()) {
            if (name.startsWith("get")) {
                var getMethod = namePerMethod.get(name);
                var setMethod = namePerMethod.get(name.replace("get", "set"));
                if (setMethod == null) {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "Impossible invoke set method from object : " + current.get() + ", Check set and get pairs."
                    );
                }
                var newValue = getMethod.invoke(post);
                if (newValue != null) {
                    setMethod.invoke(current, newValue);
                }
            }
        }
        posts.save(post);
        return current.get();
    }

    @PostMapping("/")
    public ResponseEntity<Boolean> create(@Valid @RequestBody Post post) {
        return this.posts.save(post) ? new ResponseEntity<>(
                this.posts.save(post),
                HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@Valid @RequestBody Post post) {
        return this.posts.save(post) ? ResponseEntity.ok().build()
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
