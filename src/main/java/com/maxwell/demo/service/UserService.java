package com.maxwell.demo.service;

import com.maxwell.demo.dto.UserRegisterRequest;
import com.maxwell.demo.model.User;

public interface UserService {
    
    Integer register(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);
    
}
