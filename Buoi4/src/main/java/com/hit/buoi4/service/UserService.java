package com.hit.buoi4.service;

import com.hit.buoi4.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {
    List<User> findAllUser();
    User findUserById(Long id);
    User createUser(User user);
    List<User> searchUserByName(String name);
    User updateUser(User user, Map<String, Object> fields);
    void deleteUser(Long id);
}
