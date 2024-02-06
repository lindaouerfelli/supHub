package com.linda.suphub.controllers;


import com.linda.suphub.dto.PostDto;
import com.linda.suphub.dto.UserDto;
import com.linda.suphub.models.PostCategory;
import com.linda.suphub.models.PostStatus;
import com.linda.suphub.models.User;
import com.linda.suphub.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody PostDto postDto) {

        return ResponseEntity.ok(service.save(postDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<PostDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{post-id}")
    public ResponseEntity<PostDto> findById(@PathVariable("post-id") Integer postId) {
        return ResponseEntity.ok(service.findById(postId));
    }

    @DeleteMapping("/{post-id}")
    public ResponseEntity<Void> delete(@PathVariable("post-id") Integer postId) {
        service.delete(postId);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<PostDto>> getAllPostsByCategory(@PathVariable ("category") PostCategory category) {
        return ResponseEntity.ok(service.findAllByItemCategory(category));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<PostDto>> getAllPostsByStatus(@PathVariable ("status") PostStatus status) {
        return ResponseEntity.ok(service.findAllByStatus(status));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> getAllPostsByUser(@PathVariable ("userId") Integer userId) {
        User user = new User();
        user.setId(userId);
        return ResponseEntity.ok(service.findAllByUser(user));
    }



}
