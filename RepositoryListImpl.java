package com.ism.repository.list;
import java.util.ArrayList;
import java.util.List;

import com.ism.repository.Repository;
public class RepositoryListImpl<T> implements Repository<T>{
    protected final  List<T> list = new ArrayList<>();
    
    public void insert(T data){
        list.add(data);
    }
    public List<T> selectAll(){
        return list;
    }

}



