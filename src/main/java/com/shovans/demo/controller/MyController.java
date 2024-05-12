package com.shovans.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shovans.demo.common.models.MyModel;
import com.shovans.demo.service.MyService;

/**
 * MyController is a Spring REST controller that handles HTTP requests related  to MyModel entities.
 */
@RestController
@RequestMapping("/api")
public class MyController {

	@Autowired
	MyService myService;

	/**
	 * Retrieves a list of names from the 'test' table.
	 * 
	 * @return ResponseEntity<?>: A response containing the list of names or a  no-content status.
	 */
	@GetMapping(value = "/list-names")
	public ResponseEntity<?> listOfNames() {
		return myService.listOfNames();
	}

	/**
	 * Saves or updates a record in the 'test' table.
	 * 
	 * @param myModel (MyModel): The model containing the name and ID to be saved or  updated.
	 * @return ResponseEntity<?>: A response containing a success message or a  no-content status.
	 */
	@PostMapping(value = "/new-names")
	public ResponseEntity<?> saveOrUpdate(@RequestBody MyModel myModel) {
		return myService.saveOrUpdate(myModel);
	}

	/**
	 * Deletes records from the 'test' table based on the provided name.
	 * 
	 * @param name (String): The name used as a condition for deletion.
	 * @return ResponseEntity<?>: A response containing a success message or a  no-content status.
	 */
	@DeleteMapping(value = "/delete-names")
	public ResponseEntity<?> delete(@RequestParam String name) {
		return myService.delete(name);
	}

}
