package com.shovans.demo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**  MyDao is a Spring component responsible for database operations using both NamedParameterJdbcTemplate and JdbcTemplate.*/
@Repository
public class MyDao {
	/*
	 * private JdbcTemplate jdbcTemplate; 
	 * public MyDao(DataSource dataSource) {
	 * jdbcTemplate = new JdbcTemplate(dataSource); 
	 * }
	 */
	
    @Autowired
    @Qualifier("mysqlJdbcTemplate")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    @Qualifier("mysqlJdbcOtherTemplate")
    private JdbcTemplate jdbcOtherTemplate;

    private static final String READ_QUERY = "SELECT name FROM test";

    /**
     * SQL query for updating a record in the 'test' table using NamedParameterJdbcTemplate.
     * Parameters:
     *   - :myName (String): The new name to be set.
     *   - :myId (int): The ID of the record to be updated.
     */
    private static final String UPDATE_QUERY_NAMED = "UPDATE test SET name = :myName WHERE id = :myId";

    /**
     * SQL query for inserting a new record into the 'test' table using NamedParameterJdbcTemplate.
     * Parameters:
     *   - :myName (String): The name to be inserted.
     */
    private static final String INSERT_QUERY_NAMED = "INSERT INTO test (name) VALUES (:myName)";

    /**
     * SQL query for updating a record in the 'test' table using JdbcTemplate.
     * Parameters:
     *   - ? (String): The new name to be set.
     *   - ? (int): The ID of the record to be updated.
     */
    private static final String UPDATE_SQL = "UPDATE test SET name = ? WHERE id = ?";

    /**
     * SQL query for inserting a new record into the 'test' table using JdbcTemplate.
     * Parameters:
     *   - ? (String): The name to be inserted.
     */
    private static final String INSERT_SQL = "INSERT INTO test (name) VALUES (?)";

    /**
     * SQL query for deleting records from the 'test' table using JdbcTemplate.
     * Parameters:
     *   - ? (String): The name to be used as a condition for deletion.
     */
    private static final String DELETE_SQL = "DELETE FROM test WHERE name = ?";

    /**
     * Retrieves a list of names from the 'test' table.
     *
     * @return List<String>: A list of names.
     */
    public List<String> listOfNames() {
        return namedParameterJdbcTemplate.query(READ_QUERY, (rs, rowNum) -> rs.getString("name"));
    }

    /**
     * Saves or updates a record in the 'test' table using NamedParameterJdbcTemplate.
     *
     * @param name (String): The name to be saved or updated.
     * @param id   (Integer): The ID of the record to be updated. Set to null for new records.
     * @return String: Success or error message.
     */
    public String saveOrUpdate(String name, Integer id) {
        int successMessage;
        if (name != null && !name.isEmpty()) {
            Map<String, Object> params = new HashMap<>();
            params.put("myName", name);
            try {
                if (id != null && id > 0) {
                    params.put("myId", id);
                    successMessage = namedParameterJdbcTemplate.update(UPDATE_QUERY_NAMED, params);
                } else {
                    successMessage = namedParameterJdbcTemplate.update(INSERT_QUERY_NAMED, params);
                }
            } catch (DataAccessException e) {
                e.printStackTrace();
                return "An error occurred while saving/updating data.";
            }
        } else {
            successMessage = 0;
        }
        return successMessage != 0 ? "Success!!" : "Oh! Some issue.";
    }

    /**
     * Saves or updates a record in the 'test' table using JdbcTemplate.
     *
     * @param name (String): The name to be saved or updated.
     * @param id   (Integer): The ID of the record to be updated. Set to null for new records.
     * @return String: Success or error message.
     */
    public String saveOrUpdateUnnamed(String name, Integer id) {
        int successMessage;
        if (name != null && !name.isEmpty()) {
            try {
                if (id != null && id > 0) {
                    Object[] params = new Object[]{name, id};
                    successMessage = jdbcOtherTemplate.update(UPDATE_SQL, params);
                } else {
                    Object[] params = new Object[]{name};
                    successMessage = jdbcOtherTemplate.update(INSERT_SQL, params);
                }
            } catch (DataAccessException e) {
                e.printStackTrace();
                return "An error occurred while saving/updating data.";
            }
        } else {
            successMessage = 0;
        }
        return successMessage != 0 ? "Success!!" : "Oh! Some issue.";
    }

    /**
     * Deletes records from the 'test' table using JdbcTemplate.
     *
     * @param name (String): The name used as a condition for deletion.
     * @return String: Success or error message.
     */
    public String delete(String name) {
        int successMessage = 0;
        successMessage = jdbcOtherTemplate.update(DELETE_SQL, name);
        return successMessage != 0 ? "Success!!" : "Oh! Some issue.";
    }
}
