package ru.stroev.testTaskAxiomatika.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.stroev.testTaskAxiomatika.models.entities.ContractLoan;

import java.util.List;

/**
 * Contract loan repository
 *
 * @author Строев Д.В.
 * @version 1.0
 */
@Repository
public class ContractLoanRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public ContractLoan saveContract(ContractLoan contractLoan) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(contractLoan);
        session.getTransaction().commit();
        return contractLoan;
    }

    public ContractLoan getByApplicationId(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select l from ContractLoan l where l.applicationLoan.id = :id", ContractLoan.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public ContractLoan getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select l from ContractLoan l where l.id = :id", ContractLoan.class)
                .setParameter("id", id).getSingleResult();
    }

    public List<ContractLoan> getAllSignedContracts() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select l from ContractLoan l join fetch l.applicationLoan where l.signatureStatus = true", ContractLoan.class).list();
    }
}
