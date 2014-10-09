package ru.tsystems.tsproject.sbb;

/**
 * Created by apple on 08.10.14.
 */

import ru.tsystems.tsproject.sbb.DAO.StationDAO;
import ru.tsystems.tsproject.sbb.DAO.TrainDAO;
import ru.tsystems.tsproject.sbb.DAO.UserDAO;
import ru.tsystems.tsproject.sbb.Entity.*;
import ru.tsystems.tsproject.sbb.Factory.MySQLFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.SimpleDateFormat;

public class Main {
        static EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
        static EntityManager em = emf.createEntityManager();

        public static void main(String[] a) throws Exception {
            em.getTransaction().begin();

            Role role= new Role();
            role.setRole("admin");
            em.persist(role);

            role = new Role();
            role.setRole("client");
            em.persist(role);

            MySQLFactory factory = new MySQLFactory();
            StationDAO stationDAO = factory.getStationDAO();
            stationDAO.addStation("Udelnaya");
            stationDAO.addStation("Repino");
            stationDAO.addStation("Losevo");
            stationDAO.addStation("Zelenogorsk");

            UserDAO userDao = factory.getUserDAO();
            role = userDao.getClientRole();
            User user = new User("Alex", "Smith", new SimpleDateFormat("dd-mm-yyyy").parse("24-08-1970"), "alex", "12345", role);
            userDao.addUser("Alex", "Smith", new SimpleDateFormat("dd-mm-yyyy").parse("24-08-1970"), "alex", "12345", role);
            userDao.addUser("Jemmy", "King", new SimpleDateFormat("dd-mm-yyyy").parse("27-09-1975"), "Jemmy", "12345", role);
            //String resRole = userDao.getUsersRole(user);


            TrainDAO trainDAO = factory.getTrainDAO();
            //trainDAO.addTrain()
            Station station = new Station();
            //station.setName("Udelnaya");
            //station.addTimetable(new Timetable());
            //em.persist(station);
           // User user = new User();
            //em.persist(user);
            Ticket ticket = new Ticket();
            //em.persist(ticket);
            TimetableRecord timetable = new TimetableRecord();
            //em.persist(timetable);
            Train train = new Train();
            //em.persist(train);
//            em.flush();

            //Query query = em.createQuery("SELECT e FROM Student e");
            //List<Student> list = (List<Student>) query.getResultList();
            //System.out.println(list);

            //query = em.createQuery("SELECT d FROM Department d");
            //List<Department> dList = (List<Department>) query.getResultList();
            //System.out.println(dList);
            em.getTransaction().commit();
           em.close();
           emf.close();

            //Helper.checkData();
        }
    }
