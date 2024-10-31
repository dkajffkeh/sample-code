package com.patrick.layeredarchitecture.infra.repository.user;

import com.patrick.layeredarchitecture.infra.entity.UserEntity;
import org.springframework.data.repository.Repository;

public interface UserJpaRepository extends Repository<UserEntity, Long> {

    UserEntity findById(Long Id);
}
