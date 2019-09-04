/**
 * 
 */
package com.ani.vis.api.apitest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aniket
 *
 */

@RestController
@Service
public class EventController {

	
	private static Map<String, Event> repo = new HashMap<>();
	List<Event> eventList = new ArrayList<>();
	private static Map<String, List<Event>> actorRepo = new HashMap<>();//actor id to event
	
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
		//clean the dependent actor repo as well
		actorRepo.clear();
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
			//the actor repo will need to be updated as well
			eventList.add(e);
			actorRepo.put(e.getActor().getactorID(),eventList);
			return new ResponseEntity<>("Successfully created", HttpStatus.OK);
			
		}
		
	}
	
	
	/*
	 * 
	 * 
	 * Return an event by ID
	 * 
	 *   /event/{id}
	 *   
	 *   GET request
	 *   
	 *   if event id does not exist , response code is 404
	 * 
	 * if the event id does not exists , then HTTP response code should be 400 ,
	 * 
	 * else 200
	 * 
	 */
	
	@RequestMapping(value="/event",method=RequestMethod.GET)
	public ResponseEntity<Object> getEventByID(@RequestParam String id) {
		
		if(!repo.containsKey(id)) {
	
			return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
		} else {
			//return a valid response
			Event event = repo.get(id);
			return new ResponseEntity<>(event,HttpStatus.OK);
		}
	
	
	}
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
	
    
	@RequestMapping(value="events/actors" , method=RequestMethod.GET)
	public ResponseEntity<Object> getEventsByActorID(@RequestParam String actorID) {
		

		//List of events
		actorRepo.get(actorID).stream().forEach();; // fetch events
		
		return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
		
	}
		
}