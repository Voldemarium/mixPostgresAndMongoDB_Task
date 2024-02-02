package ru.synergy.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import ru.synergy.hiber.JpaUtil;
import ru.synergy.models.Contact;

import java.util.List;

public class ContactDao implements Dao<Contact> {
    @Override
    public Contact findById(int id) {
        return JpaUtil.getEntityManager().find(Contact.class, id);
    }

    @Override
    public void save(Contact contact) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(contact);
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public void update(Contact contact) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.detach(contact);
        entityManager.merge(contact);
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public void delete(Contact contact) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(entityManager.find(Contact.class, contact.getId()));
        entityManager.flush();
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public List<Contact> findAll() {
        return JpaUtil.getEntityManager().createQuery("From Contact", Contact.class).getResultList();
    }
}
