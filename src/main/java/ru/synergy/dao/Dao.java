package ru.synergy.dao;


import java.util.List;

public interface Dao<T> {
    public T findById(int id);

    public void save(T o);

    public void update(T o);

    public void delete(T o);

    public List<T> findAll();
}
