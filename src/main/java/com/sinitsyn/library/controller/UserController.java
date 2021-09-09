package com.sinitsyn.library.controller;

import com.sinitsyn.library.dto.request.UserDto;
import com.sinitsyn.library.dto.response.UserDtoResponse;
import com.sinitsyn.library.exceptions.ServiceException;
import com.sinitsyn.library.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("library/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDtoResponse> users() {
        return userService.findAll();
    }

    @GetMapping("{id}")
    public UserDtoResponse getOneUser(@PathVariable Long id) throws ServiceException {
        return userService.findUserById(id);
    }

    @PostMapping
    public UserDtoResponse addUser(@RequestBody @Valid UserDto userDto) throws ServiceException {
        return userService.addUser(userDto);
    }

    @PutMapping("{id}")
    public UserDtoResponse updateUser(@PathVariable Long id, @RequestBody @Valid UserDto updatedUser) throws ServiceException {
        return userService.updateUser(updatedUser, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
