package com.javaguru.todolist.repository;

import com.javaguru.todolist.domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public class HibernateUserRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    HibernateUserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Long save(User user) {
        sessionFactory.getCurrentSession().save(user);
        return user.getId();
    }

    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    public Optional<User> findUserById(Long id) {
        User user = (User) sessionFactory.getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
        return Optional.ofNullable(user);
    }
}
