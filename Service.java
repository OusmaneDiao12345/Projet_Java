package com.ism.services;

import java.util.List;


public interface Service<T> {
    void create(T objet);
    List<T> show();
}
