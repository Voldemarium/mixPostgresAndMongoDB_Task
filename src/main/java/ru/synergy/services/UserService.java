package ru.synergy.services;

import ru.synergy.dao.UserDao;
import ru.synergy.models.User;

import java.util.List;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User findUser(int id) {return (User) userDao.findById(id);}

    public void saveUser(User user) {
        userDao.save(user);}

    public void updateUser(User user) {
        userDao.update(user);}

    public void deleteUser(User user) {
        userDao.delete(user);}

    public List<User> findAllUsers() {return userDao.findAll();}
}


