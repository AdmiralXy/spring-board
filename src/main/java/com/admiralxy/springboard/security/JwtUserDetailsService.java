package com.admiralxy.springboard.security;

import com.admiralxy.springboard.entity.User;
import com.admiralxy.springboard.security.jwt.JwtUser;
import com.admiralxy.springboard.security.jwt.JwtUserFactory;
import com.admiralxy.springboard.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final IUserService userService;

    @Autowired
    public JwtUserDetailsService(@Lazy IUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        return JwtUserFactory.create(user);
    }
}
