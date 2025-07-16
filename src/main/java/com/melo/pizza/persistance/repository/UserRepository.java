package com.melo.pizza.persistance.repository;

import com.melo.pizza.persistance.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <UserEntity, String>{
}
