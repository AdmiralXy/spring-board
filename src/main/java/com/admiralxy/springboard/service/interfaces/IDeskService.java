package com.admiralxy.springboard.service.interfaces;

import com.admiralxy.springboard.entity.Desk;
import java.util.List;

public interface IDeskService {
    List<Desk> getAll();
    void save(Desk desk);
    Desk find(Long id);
    void delete(Long id);
    Desk findByName(String name);
}
