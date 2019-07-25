package com.javaguru.todolist.repository;

import com.javaguru.todolist.domain.Task;
import com.javaguru.todolist.domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("hibernate")
@Transactional
class HibernateTaskRepository implements TaskRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateTaskRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Long save(Task task) {
        sessionFactory.getCurrentSession().save(task);
        return task.getId();
    }

    public void update(Task task) {
        sessionFactory.getCurrentSession().saveOrUpdate(task);
    }

    public Optional<Task> findTaskById(Long id) {
        Task task = (Task) sessionFactory.getCurrentSession().createCriteria(Task.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
        return Optional.ofNullable(task);
    }

    public boolean existsByName(String name) {
        String query = "select case when count(*)> 0 " +
                "then true else false end " +
                "from Task t where t.name='" + name + "'";
        return (boolean) sessionFactory.getCurrentSession().createQuery(query)
                .setMaxResults(1)
                .uniqueResult();
    }

    public Optional<Task> findTaskByName(String name) {
        Task task = (Task) sessionFactory.getCurrentSession().createCriteria(Task.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
        return Optional.ofNullable(task);
    }


    public List<Task> findAll() {
        return sessionFactory.getCurrentSession().createCriteria(Task.class)
                .list();
    }

    public void delete(Task task) {
        sessionFactory.getCurrentSession().delete(task);
    }
}
