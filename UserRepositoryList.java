package com.ism.repository.list;

import java.util.ArrayList;
import java.util.List;

import com.ism.enums.Role;
import com.ism.entities.User;
import com.ism.repository.RepositoryUser;

public class UserRepositoryList extends RepositoryListImpl<User> implements RepositoryUser<User>{
    @Override
    public User getByLogin(String login) {
        for (User user : list) {
            if (user != null && user.getLogin() != null && user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> findByRole(Role role) {
        List<User> actifs=new ArrayList<>();
        for (User user : list) {
            if(user.getStatut().compareTo("Activer")==0 || user.getRole().compareTo(role)==0){
                actifs.add(user);
            }
        }
        return actifs;
    }
    
}
