package com.patrick.layeredarchitecture.application.user;

import com.patrick.layeredarchitecture.infra.entity.UserEntity;
import com.patrick.layeredarchitecture.infra.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(
            UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void businessLogic() {
        UserEntity user = userRepository.userById(1L);

    }
}
