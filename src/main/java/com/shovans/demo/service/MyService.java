package com.shovans.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.shovans.demo.common.models.MyModel;
import com.shovans.demo.dao.MyDao;

import java.util.List;

/**  MyService is a Spring service component that acts as an intermediary between controllers and MyDao for handling business logic related to MyModel entities. */
@Service
public class MyService {

    @Autowired
    MyDao myDao;

    /**
     * Retrieves a list of All names from the 'test' table using MyDao. 
     * @return ResponseEntity<List<String>>: A response containing the list of names  or a no-content status.
     */
    public ResponseEntity<List<String>> listOfNames() {
        List<String> result = myDao.listOfNames();
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
    }

    /**
     * Saves or updates a record in the 'test' table using MyDao. 
     * @param myModel (MyModel): The model containing the name and ID to be saved or  updated.
     * @return ResponseEntity<String>: A response containing a success message or a  no-content status.
     */
    public ResponseEntity<String> saveOrUpdate(MyModel myModel) {
        String result = myDao.saveOrUpdateUnnamed(myModel.getName(), myModel.getId());
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
    }

    /**
     * Deletes records from the 'test' table using MyDao. 
     * @param name (String): The name used as a condition for deletion.
     * @return ResponseEntity<String>: A response containing a success message or a  no-content status.
     */
    public ResponseEntity<String> delete(String name) {
        String result = myDao.delete(name);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
    }

}
