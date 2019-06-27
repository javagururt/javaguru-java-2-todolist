package com.javaguru.todolist.repository;

import com.javaguru.todolist.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("jdbc")
class JdbcTaskRepository implements TaskRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    JdbcTaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Task save(Task task) {
        String query = "insert into tasks (name, description) values (" +
                "?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, task.getName());
            ps.setString(2, task.getDescription());
            return ps;
        }, keyHolder);

        task.setId(keyHolder.getKey().longValue());
        return task;
    }

    @Override
    public Optional<Task> findTaskById(Long id) {
        String query = "select * from tasks where id=" + id;
        List<Task> tasks = jdbcTemplate.query(query,
                new BeanPropertyRowMapper(Task.class));
        if (!tasks.isEmpty()) {
            return Optional.ofNullable(tasks.get(0));
        }
        return Optional.empty();
    }

    @Override
    public boolean existsByName(String name) {
        String query = "SELECT CASE WHEN count(*)> 0 " +
                "THEN true ELSE false END " +
                "FROM tasks where name=" + name;
        return jdbcTemplate.queryForObject(query, Boolean.class);
    }

    @Override
    public Optional<Task> findTaskByName(String name) {
        String query = "select * from tasks where name=" + name;
        return jdbcTemplate.query(query, new BeanPropertyRowMapper(Task.class)).stream()
                .findFirst();
    }
}
