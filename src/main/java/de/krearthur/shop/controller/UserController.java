package de.krearthur.shop.controller;

import de.krearthur.shop.dto.UserRequest;
import de.krearthur.shop.dto.UserResponse;
import de.krearthur.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.fetchAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        return userService.fetchUser(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserRequest user) {
        Long newUserId = userService.addUser(user);
        return ResponseEntity.ok("User added successfully with id " + newUserId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserRequest user) {
        if (userService.updateUser(id, user)) {
            return ResponseEntity.ok("User updated successfully!");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
