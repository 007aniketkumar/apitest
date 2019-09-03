/**
 * 
 */
package com.ani.vis.api.apitest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aniket
 *
 */

@RestController
@Service
public class EventController {

	
	private static Map<String, Event> repo = new HashMap<>();
	
	/*
	 * Erase all records 
	 * 
	 *    /erase
	 *  response code should be 200
	 * 
	 */
	
	@RequestMapping(value="/erase", method=RequestMethod.DELETE)
	public ResponseEntity<Object>  eraseAllRecords() {
	
		repo.clear();//Delete all the events
		return new ResponseEntity<>("Product is deleted successfully",HttpStatus.OK);
	}
	
	
	
	/*
	 * 
	 * Create an event 	
	 * 
	 * POST REQUEST
	 * 
	 *  /events
	 * 
	 * on success 201
	 * 
	 * on duplicate 400
	 * 
	 * 
	 */
	
	@RequestMapping(value="/events", method=RequestMethod.POST)
	public ResponseEntity<Object> createAnEvent(@RequestBody Event e) {
		
		if(repo.containsKey(e.getId())) {
			return new ResponseEntity<>("Duplicate record", HttpStatus.BAD_REQUEST);
		} else {
			repo.put(e.getId(), e);
			return new ResponseEntity<>("Successfully created", HttpStatus.OK);
			
		}
		
	}
	
	
	/*
	 * 
	 * 
	 * Return an event by ID
	 * 
	 * if the event id does not exists , then HTTP response code should be 400 ,
	 * 
	 * else 200
	 * 
	 */
	
	
	
	
	/*
	 * 
	 * Return the event records filtered by actorID
	 * 
	 *   /events/actors/{actorID}
	 *   
	 *   response code 200 
	 *   
	 *   failure 400
	 *   
	 *   The response should be sorted in ascending order by eventid
	 *   
	 */
	
}
