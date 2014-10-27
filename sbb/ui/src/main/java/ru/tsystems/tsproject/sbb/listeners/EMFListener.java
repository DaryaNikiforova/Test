package ru.tsystems.tsproject.sbb.listeners;
/**
 * Created by apple on 27.10.2014.
 */

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener()
public class EMFListener implements ServletContextListener {

    private static EntityManagerFactory emf;

    public EMFListener() { }

    public void contextInitialized(ServletContextEvent sce) {
        emf = Persistence.createEntityManagerFactory("manager1");
    }

    public void contextDestroyed(ServletContextEvent sce) {
      emf.close();
    }

    public static EntityManager createEntityManager() {
        if (emf == null) {
            throw new IllegalStateException("Context is not initialized yet.");
        }
        return emf.createEntityManager();
    }
}
