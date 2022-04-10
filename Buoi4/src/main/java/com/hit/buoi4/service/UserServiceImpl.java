package com.hit.buoi4.service;

import com.hit.buoi4.entity.User;
import com.hit.buoi4.exception.InternalServerException;
import com.hit.buoi4.exception.NotFoundException;
import com.hit.buoi4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new NotFoundException("Not Found User");
        }
        return user.get();
    }

    @Override
    public User createUser(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new InternalServerException("Database error. Can't create user");
        }
    }

    @Override
    public List<User> searchUserByName(String name) {
        List<User> users = new ArrayList<>();
        List<User> userList = userRepository.findAll();

        String pattern = ".*" + name.toLowerCase() + ".*";
        for(User user : userList){
            if(user.getFullName().toLowerCase().matches(pattern)){
                users.add(user);
            }
        }
        if(users.isEmpty()) {
            throw new NotFoundException("Not Found User");
        }
        return users;
    }

    @Override
    public User updateUser(User user, Map<String, Object> fields) {
        try {
            fields.forEach((k, v) -> {
                Field field = ReflectionUtils.findField(User.class, k);
                if (field != null) {
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, user, v);
                }
            });
            return userRepository.save(user);
        } catch (Exception e) {
            throw new InternalServerException("Database error. Can't update user");
        }
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new NotFoundException("Not Found User");
        }
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new InternalServerException("Database error. Can't delete user");
        }
    }
}
