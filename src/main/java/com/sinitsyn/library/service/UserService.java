package com.sinitsyn.library.service;

import com.sinitsyn.library.exceptions.ApiError;
import com.sinitsyn.library.exceptions.ErrorCode;
import com.sinitsyn.library.exceptions.ServiceException;
import com.sinitsyn.library.model.User;
import com.sinitsyn.library.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        if (validate(user)) {
            return userRepository.save(user);
        } else return null;
    }

    public User updateUser(User userFromDataBase, User updatedUser) {
        BeanUtils.copyProperties(updatedUser, userFromDataBase, "id");
        return userRepository.save(userFromDataBase);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public User findUserById(User user) throws ServiceException {
        try {
            return userRepository.findById(user.getId()).orElseThrow(RuntimeException::new);
        }catch (RuntimeException e){
            ArrayList<ApiError> errors = new ArrayList<>();
            ApiError apiError = new ApiError(ErrorCode.ERROR_FIND_OBJECT_TO_DATABASE.name(), "findUserByIdMethod", e.getCause().getMessage());
            errors.add(apiError);
            throw new ServiceException(errors);
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


    private boolean validate(User user) {

        return user.getFirstName() != null && user.getLastName() != null && user.getYearOfBirth() != 0;
    }

}
