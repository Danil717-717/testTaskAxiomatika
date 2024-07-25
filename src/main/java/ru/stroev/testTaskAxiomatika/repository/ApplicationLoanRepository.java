package ru.stroev.testTaskAxiomatika.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.stroev.testTaskAxiomatika.models.entities.ApplicationLoan;

import java.util.List;

/**
 * Application loan repository
 *
 * @author Строев Д.В.
 * @version 1.0
 */
@Repository
public class ApplicationLoanRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public ApplicationLoan saveApplication(ApplicationLoan applicationLoan) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(applicationLoan);
        session.getTransaction().commit();
        return applicationLoan;
    }

    public List<ApplicationLoan> getAllApplications() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select l from ApplicationLoan l join fetch l.client join fetch l.contractLoan", ApplicationLoan.class).list();
    }
}
