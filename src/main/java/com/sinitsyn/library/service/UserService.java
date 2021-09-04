package com.sinitsyn.library.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinitsyn.library.dto.request.UserDto;
import com.sinitsyn.library.dto.response.UserDtoResponse;
import com.sinitsyn.library.exceptions.ApiError;
import com.sinitsyn.library.exceptions.ErrorCode;
import com.sinitsyn.library.exceptions.ServiceException;
import com.sinitsyn.library.model.User;
import com.sinitsyn.library.repository.UserRepository;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public UserService(UserRepository userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    public List<UserDtoResponse> findAll() {
        return (objectMapper.convertValue(userRepository.findAll(), new TypeReference<List<UserDtoResponse>>() {
        }));
    }

    public UserDtoResponse findUserById(Long id) throws ServiceException {
        User user = userRepository.findById(id).orElseThrow(() -> new ServiceException(new ApiError(ErrorCode.ERROR_FIND_OBJECT_TO_DATABASE.name(), "findUserById Method", "user with id = " + id + " not found")));
        return objectMapper.convertValue(user, UserDtoResponse.class);
    }

    public UserDtoResponse addUser(UserDto userDto) throws ServiceException {
        User user = objectMapper.convertValue(userDto, User.class);
        User saveUser;
        try {
            saveUser = userRepository.save(user);
        } catch (RuntimeException e) {
            throw new ServiceException(new ApiError(ErrorCode.ERROR_ADD_TO_DATABASE.name(), "addUserMethod", NestedExceptionUtils.getMostSpecificCause(e).getMessage()));
        }
        return objectMapper.convertValue(saveUser, UserDtoResponse.class);
    }

    public UserDtoResponse updateUser(UserDto updatedUser, Long id) throws ServiceException {
        updatedUser.setId(id);
        findUserById(id);
        return addUser(updatedUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
