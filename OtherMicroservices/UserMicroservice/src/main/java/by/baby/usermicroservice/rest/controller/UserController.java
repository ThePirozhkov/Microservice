package by.baby.usermicroservice.rest.controller;

import by.baby.usermicroservice.persistence.entity.UserEntity;
import by.baby.usermicroservice.repository.UserRepository;
import by.baby.usermicroservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }


    @PutMapping
    public UserEntity createUser(@RequestBody UserEntity user) {
        return userService.create(user);
    }

    @PostMapping("/{id}/update")
    public UserEntity updateUser(@PathVariable Long id, @RequestBody UserEntity user) {
        return userService.update(user, id);
    }
}
