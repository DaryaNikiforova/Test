package ru.tsystems.tsproject.sbb.filters;


import ru.tsystems.tsproject.sbb.listeners.EMFListener;
import ru.tsystems.tsproject.sbb.providers.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.servlet.*;
import java.io.IOException;

/**
 * Created by apple on 27.10.2014.
 */
public class EntityManagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            EntityManagerProvider.setCurrentEntityManager(EMFListener.createEntityManager());
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            EntityManagerProvider.setCurrentEntityManager(null);
        }
    }

    @Override
    public void destroy() {
    }
}
