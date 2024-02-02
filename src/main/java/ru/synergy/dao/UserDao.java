package ru.synergy.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import ru.synergy.hiber.JpaUtil;
import ru.synergy.models.User;

import java.util.List;

public class UserDao implements Dao<User> {
    @Override
    public User findById(int id) {
        return JpaUtil.getEntityManager().find(User.class, id);
    }

    @Override
    public void save(User user) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(user);
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public void update(User user) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.detach(user);
        entityManager.merge(user);
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public void delete(User user) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(entityManager.find(User.class, user.getId()));
        entityManager.flush();
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public List<User> findAll() {
        return JpaUtil.getEntityManager().createQuery("From User", User.class).getResultList();
    }
}
