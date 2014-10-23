package ru.tsystems.tsproject.sbb.database.dao.impl;

import ru.tsystems.tsproject.sbb.database.dao.RateDAO;
import ru.tsystems.tsproject.sbb.database.entity.Rate;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by apple on 22.10.14.
 */
public class RateDAOImpl implements RateDAO {
    private EntityManager entityManager;

    public RateDAOImpl(EntityManager em) {
        entityManager = em;
    }

    @Override
    public List<Rate> getRates() {
        return entityManager.createQuery("select r from Rate r where r.forTrain = false").getResultList();
    }

    @Override
    public List<Rate> getTrainRates() {
        return entityManager.createQuery("select r from Rate r where r.forTrain = true").getResultList();
    }

    @Override
    public double getValueById(int id) {
        return (Double) entityManager.createQuery("select r.value from Rate r where r.id=:id")
                            .setParameter("id", id).getSingleResult();
    }
}
