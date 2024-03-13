package com.gameofthronesbooksandcharacters.databaseutils;

import com.gameofthronesbooksandcharacters.datamodel.CustomUser;
import com.gameofthronesbooksandcharacters.exceptions.EmailExistsException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;


public class CustomUserDao {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final SessionFactory sessionFactory = CustomUserSessionFactory.getCustomUserSessionFactory();

    public void saveUser(CustomUser customUser) {
        try (Session session = sessionFactory.openSession()) {
            Optional<CustomUser> existingUserOptional = findUserByEmail(customUser.getEmail());
            if (existingUserOptional.isPresent()) {
                throw new EmailExistsException("A user with this email already exists.");
            }

            Transaction transaction = session.beginTransaction();
            customUser.setPassword(passwordEncoder.encode(customUser.getPassword()));
            session.persist(customUser);
            transaction.commit();
        }
    }

    public Optional<CustomUser> findUserByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM CustomUser WHERE email = :email";
            Query<CustomUser> query = session.createQuery(hql, CustomUser.class);
            query.setParameter("email", email);
            return query.uniqueResultOptional();
        }
    }

    public List<CustomUser> findAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM CustomUser";
            Query<CustomUser> query = session.createQuery(hql, CustomUser.class);
            return query.list();
        }
    }

    public void deleteUser(Long userId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            CustomUser user = session.get(CustomUser.class, userId);
            if (user != null) {
                session.delete(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}