package com.patrick.layeredarchitecture.infra.repository.user;

import com.patrick.layeredarchitecture.infra.entity.UserEntity;

public interface UserRepository {

    UserEntity userById(Long id);

}
