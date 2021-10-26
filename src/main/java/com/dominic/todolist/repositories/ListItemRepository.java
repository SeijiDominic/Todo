package com.dominic.todolist.repositories;

import com.dominic.todolist.models.ListItem;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class ListItemRepository {
    private final NamedParameterJdbcTemplate jdbc;

    public ListItemRepository(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public MapSqlParameterSource mapParams (ListItem item) {
        var namedParam = new MapSqlParameterSource();
        namedParam.addValue("id", item.getId());
        namedParam.addValue("title", item.getTitle());
        namedParam.addValue("content", item.getContent());
        namedParam.addValue("date_due", item.getDateDue());
        namedParam.addValue("date_created", item.getDateCreated());
        namedParam.addValue("done", item.getDone());
        return namedParam;
    }

    public List<ListItem> retrieveAllByDateCreated() {
        var query = "SELECT * FROM todo ORDER BY date_created;";
        return jdbc.query(query, new HashMap<>(), new BeanPropertyRowMapper<>(ListItem.class));
    }

    public List<ListItem> retrieveAllByDateDue() {
        var query = "SELECT * FROM todo ORDER BY date_due;";
        return jdbc.query(query, new HashMap<>(), new BeanPropertyRowMapper<>(ListItem.class));
    }

    public ListItem retrieveById(Long id) {
        var query = "SELECT * FROM todo WHERE id=:id";
        var namedParam = new MapSqlParameterSource();
        namedParam.addValue("id", id);
        return jdbc.query(query, namedParam, new BeanPropertyRowMapper<>(ListItem.class)).get(0);
    }

    public void createListItem (ListItem item) {
        String query = "INSERT INTO todo(title, content, date_due, done) VALUES (:title, :content, :date_due, :done);";
        var namedParam = new MapSqlParameterSource();
        namedParam.addValue("title", item.getTitle());
        namedParam.addValue("id", item.getId());
        namedParam.addValue("content", item.getContent());
        namedParam.addValue("date_due", item.getDateDue());
        namedParam.addValue("date_created", item.getDateCreated());
        namedParam.addValue("done", item.getDone());
        int x = jdbc.update(query, namedParam);
        if (x > 0) {
            System.out.printf("%d row(s) created.\n", x);
        }
    }

    public void updateListItem (ListItem item) {
        String query = "UPDATE todo SET title=:title, content=:content, date_due=:date_due, done=:done WHERE id=:id;";
        MapSqlParameterSource namedParam = mapParams(item);
        int x = jdbc.update(query, namedParam);
        if (x > 0) {
            System.out.printf("%d row(s) updated.\n", x);
        }
    }

    public void deleteDueListItem() {
        String query = "DELETE FROM todo WHERE date_due < (CURRENT_DATE);";
        int x = jdbc.update(query, new HashMap<>());
        if (x > 0) {
            System.out.printf("%d row(s) deleted.\n", x);
        }
    }

    public void deleteListItem (Long id) {
        String query = "DELETE FROM todo WHERE id=:id";
        var namedParam = new MapSqlParameterSource();
        namedParam.addValue("id", id);
        int x = jdbc.update(query, namedParam);
        if (x > 0) {
            System.out.printf("%d row(s) deleted.\n", x);
        }
    }














































}
