package com.ali.userfront.service;

import com.ali.userfront.dao.UserDao;
import com.ali.userfront.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public boolean checkUserExists(String username, String email) {
        return checkUsernameExists(username) || checkEmailExists(email);
    }

    @Override
    public boolean checkUsernameExists(String username) {
        if (userDao.findByUsername(username) != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkEmailExists(String email) {
        if (userDao.findByEmail(email) != null) {
            return true;
        }
        return false;
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public User saveUser(User user) {
        return userDao.save(user);
    }

    @Override
    public List<User> findUserList() {
        return userDao.findAll();
    }

    @Override
    public void enableUser(String username) {
        User user = userDao.findByUsername(username);
        user.setEnabled(true);
        userDao.save(user);
    }

    @Override
    public void disableUser(String username) {
        User user = userDao.findByUsername(username);
        user.setEnabled(false);
        userDao.save(user);
    }
}
