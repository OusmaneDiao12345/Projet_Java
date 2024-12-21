package com.ism.repository;

import java.util.List;

import com.ism.enums.Role;

public interface  RepositoryUser<T>  extends Repository<T>{
    List<T> findByRole(Role role);
    T getByLogin(String login);
}
