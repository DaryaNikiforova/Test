package ru.tsystems.tsproject.sbb.database.dao.impl;

import ru.tsystems.tsproject.sbb.database.entity.Role;
import ru.tsystems.tsproject.sbb.database.entity.User;
import ru.tsystems.tsproject.sbb.database.dao.UserDAO;

import javax.persistence.EntityManager;
import java.util.Date;

/**
 * Created by apple on 04.10.14.
 */
public final class UserDAOImpl implements UserDAO {
    private EntityManager entityManager;

    public UserDAOImpl(EntityManager em) {
        entityManager = em;
    }

    @Override
    public void addUser(String name, String surname, Date birthDate, String login, String password, Role role) {
        User user=new User(name, surname, birthDate, login, password, role);
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } finally {
            if (entityManager.getTransaction().isActive())
                entityManager.getTransaction().rollback();
        }
    }

    @Override
    public Role getClientRole() {
        return (Role) entityManager.createQuery("select r from Role r where r.name = 'client'").getSingleResult();
    }

    @Override
    public User getUser(String login) {
        return (User) entityManager.createQuery("select u from User u where u.login = :login")
                .setParameter("login", login)
                .getSingleResult();
    }

    @Override
    public boolean isUserExist(String login) {
        return entityManager.createQuery("select u from User u where u.login = :login")
                .setParameter("login", login)
                .getFirstResult() != 0;
    }
}
