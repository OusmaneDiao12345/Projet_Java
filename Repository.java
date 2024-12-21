package com.ism.repository;
import java.util.List;

public interface Repository<T> {
    void insert(T data);
    List<T> selectAll();
    
}
