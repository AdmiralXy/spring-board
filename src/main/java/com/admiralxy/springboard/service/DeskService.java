package com.admiralxy.springboard.service;

import com.admiralxy.springboard.entity.Desk;
import com.admiralxy.springboard.entity.User;
import com.admiralxy.springboard.repository.DeskRepository;
import com.admiralxy.springboard.repository.UserRepository;
import com.admiralxy.springboard.security.jwt.JwtUser;
import com.admiralxy.springboard.service.interfaces.IDeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeskService implements IDeskService {

    private final DeskRepository deskRepository;
    private final UserRepository userRepository;
    private JwtUser jwtUser;

    @Autowired
    public DeskService(DeskRepository deskRepository, UserRepository userRepository) {
        this.deskRepository = deskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Desk> getAll() {
        jwtUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(jwtUser.getUsername());
        return user.getDesks();
    }

    @Override
    public void save(Desk desk) {
        jwtUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(jwtUser.getUsername());
        List<User> deskUsers = desk.getUsers();
        deskUsers.add(user);
        deskRepository.save(desk);
    }

    @Override
    public Desk find(Long id) {
        Optional<Desk> desk = deskRepository.findById(id);
        return desk.orElse(null);
    }

    @Override
    public void delete(Long id) {
        deskRepository.deleteById(id);
    }

    @Override
    public Desk findByName(String name) {
        return deskRepository.findByName(name);
    }
}
