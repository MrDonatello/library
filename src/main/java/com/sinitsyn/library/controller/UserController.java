package com.sinitsyn.library.controller;

import com.sinitsyn.library.exceptions.ServiceException;
import com.sinitsyn.library.model.User;
import com.sinitsyn.library.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("library/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> users() {
        return userService.findAll();
    }

    @GetMapping("{id}")
    public User getOneUser(@PathVariable("id") User user) throws ServiceException {
        return userService.findUserById(user);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("{id}")
    public User updateUser(@PathVariable("id") User userFromDataBase, @RequestBody User updatedUser) {
        return userService.updateUser(userFromDataBase, updatedUser);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") User user) {
        userService.deleteUser(user);
    }
}
