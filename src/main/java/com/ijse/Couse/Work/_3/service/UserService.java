package com.ijse.Couse.Work._3.service;
import org.springframework.stereotype.Service;

import com.ijse.Couse.Work._3.entity.User;



@Service
public interface UserService {
    User createUser(User user);
}
