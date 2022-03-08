package com.admiralxy.springboard.service.interfaces;

import com.admiralxy.springboard.entity.User;
import java.util.List;

public interface IUserService {
    List<User> getAll();
    User register(User user);
    User findByUsername(String username);
    User findById(Long id);
    void delete(Long id);
}
