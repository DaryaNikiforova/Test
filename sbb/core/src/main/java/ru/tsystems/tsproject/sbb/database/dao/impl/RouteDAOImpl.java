package ru.tsystems.tsproject.sbb.database.dao.impl;

import ru.tsystems.tsproject.sbb.database.dao.RouteDAO;
import ru.tsystems.tsproject.sbb.database.entity.Route;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by apple on 19.10.14.
 */
public class RouteDAOImpl implements RouteDAO {

    private EntityManager entityManager;

    public RouteDAOImpl(EntityManager em) {
        entityManager = em;
    }

    @Override
    public List<Route> getRoutes() {
        return entityManager.createQuery("select r from Route r").getResultList();
    }

    @Override
    public void addRoute(Route route) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(route);
            entityManager.getTransaction().commit();
        }
        finally {
            entityManager.getTransaction().rollback();
        }
    }
}
