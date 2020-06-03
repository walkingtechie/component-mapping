package com.walking.techie;

import com.walking.techie.entity.Address;
import com.walking.techie.entity.Person;
import com.walking.techie.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ComponentMappingClient {

  public static void main(String[] args) {
    // get session factory of an application
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    // Open a session
    Session session = sessionFactory.openSession();
    Transaction transaction = null;

    try {
      // Begin a unit of work and return the associated Transaction object.
      transaction = session.beginTransaction();

      Address address = new Address("6th cross, 6th main", "Bangalore", "560078");
      Person person = new Person("Santosh", address);

      session.persist(person);

      // commit the transaction
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
        e.printStackTrace();
      }
    } finally {
      if (session != null) {
        // End the session by releasing the JDBC connection and cleaning up.
        session.close();
      }
    }
  }
}
