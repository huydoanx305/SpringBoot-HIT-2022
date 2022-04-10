package com.hit.buoi3;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Store {
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1L,"HuyDoanx","123","Doan Van Huy"));
        users.add(new User(2L,"Huannd0101","1234","Nguyen Dinh Huan"));
    }

    public List<User> getAllUser() {
        return users;
    }

    public void addUser(User user) {
        user.setId(max() + 1);
        users.add(user);
    }

    public long max() {
        long max = users.get(0).getId();
        for(User user : users)
            if (user.getId() > max)
                max = user.getId();
        return max;
    }
}
