package ru.tsystems.tsproject.sbb.database.dao.impl;

import ru.tsystems.tsproject.sbb.database.dao.ServiceDAO;
import ru.tsystems.tsproject.sbb.database.entity.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by apple on 22.10.14.
 */
public final class ServiceDAOImpl implements ServiceDAO{
    private EntityManager entityManager;

    public ServiceDAOImpl(EntityManager em) {
        entityManager = em;
    }

    @Override
    public List<Service> getServices() {
        return entityManager.createQuery("select s from Service s").getResultList();
    }

    @Override
    public Service getServiceById(int id) {
        return (Service) entityManager.find(Service.class, id);
    }

    @Override
    public double getValueById(int id) {
         return (Double) entityManager.createQuery("select s.value from Service s where s.id=:id")
                                      .setParameter("id", id).getSingleResult();
    }
}
