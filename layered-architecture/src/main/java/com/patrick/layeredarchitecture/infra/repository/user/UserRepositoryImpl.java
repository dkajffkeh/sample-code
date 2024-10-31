package com.patrick.layeredarchitecture.infra.repository.user;

import com.patrick.layeredarchitecture.infra.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public UserRepositoryImpl(
            UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }


    @Override
    public UserEntity userById(Long id) {
        return userJpaRepository.findById(id);
    }
}
