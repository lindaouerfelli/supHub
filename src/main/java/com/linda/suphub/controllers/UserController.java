package com.linda.suphub.controllers;

import com.linda.suphub.dto.UserDto;
import com.linda.suphub.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "users")
public class UserController {


    private final UserService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody UserDto userDto) {

        return ResponseEntity.ok(service.save(userDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<UserDto> findById(@PathVariable("user-id") Integer userId) {
        return ResponseEntity.ok(service.findById(userId));
    }

    @PatchMapping("/validate/{user-id}")
    public ResponseEntity<Integer> validateAccount(@PathVariable("user-id") Integer userId) {
        return ResponseEntity.ok(service.validateAccount(userId));
    }

    @PatchMapping("/invalidate/{user-id}")
    public ResponseEntity<Integer> invalidateAccount(@PathVariable("user-id") Integer userId) {
        return ResponseEntity.ok(service.invalidateAccount(userId));
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<Void> delete(@PathVariable("user-id") Integer userId) {
        service.delete(userId);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{user-id}")
    public ResponseEntity<UserDto> updateUserProfile(@PathVariable("user-id") Integer userId, @RequestBody UserDto updatedUserDto) {
        UserDto updatedUser = service.updateUserProfile(userId, updatedUserDto);
        return ResponseEntity.ok(updatedUser);
    }


}
