package ru.tsystems.tsproject.sbb.DAO;

import ru.tsystems.tsproject.sbb.Entity.Role;
import ru.tsystems.tsproject.sbb.Entity.User;

import javax.persistence.EntityManager;
import java.util.Date;

/**
 * Created by apple on 04.10.14.
 */
public final class MySQLUserDAO implements UserDAO{
    private EntityManager entityManager;

    public MySQLUserDAO(EntityManager em) {
        entityManager = em;
    }

    @Override
    public void addUser(String name, String surname, Date birthDate, String login, String password, Role role) {
        entityManager.getTransaction().begin();
        User user=new User(name, surname, birthDate, login, password, role);
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public String getUsersRole(User user) {
        return (String) entityManager.createQuery("select u.role.name from User u where u.login = :login and u.password = :password")
        .setParameter("login", user.getLogin())
        .setParameter("password", user.getPassword())
        .getSingleResult();
    }

    @Override
    public Role getClientRole() {
        return (Role) entityManager.createQuery("select r from Role r where r.name = 'client'").getSingleResult();
    }

    @Override
    public boolean isAdmin(User user) {
        return entityManager.createQuery("select u from User u where u.role.name = 'admin' and u.login = :login")
                .setParameter("login", user.getLogin())
                .getSingleResult() != null;
    }
}
