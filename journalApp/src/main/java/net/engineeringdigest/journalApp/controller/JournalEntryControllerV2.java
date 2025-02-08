package net.engineeringdigest.journalApp.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
	
	@Autowired
	private JournalEntryService journalEntryService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("{userName}")							// end point for all entries
	public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName) {
		User user = userService.findByUserName(userName);
		List<JournalEntry> all = user.getJournalEntries();
		if(all !=null && all.isEmpty()) {
			return new ResponseEntity<>(all, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	} 
	
	
	
	@PostMapping("{userName}")					// to create new entry
	public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName) {			// post at the same URL (localhost:8080/journal)
		try {
			
			journalEntryService.saveEntry(myEntry, user);
			return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
			
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} 
	}
	
	
	
/*	@GetMapping("id/{myId}")
	public JournalEntry getJournalEntryById(@PathVariable ObjectId myId) {
		return journalEntryService.findById(myId).orElse(null);
	}*/
	
	
	
	@GetMapping("id/{myId}")		// find by id 
	public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
		Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
		if (journalEntry.isPresent()) {
			return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	} 
	
	
	
	
	@DeleteMapping("id/{userName}/{myId}")
	public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId, @PathVariable String userName) {
		journalEntryService.deleteById(myId, userName);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	
	
	@PutMapping("/id/{userName}/{myid}")
	public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId myid, 
			@RequestBody JournalEntry newEntry, 
			@PathVariable String userName) 
	{
		JournalEntry old = journalEntryService.findById(myid).orElse(null);
		if(old!=null) {
			old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
			old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
		}
		journalEntryService.saveEntry(old);
		return new ResponseEntity<>(old, HttpStatus.NOT_FOUND);
	}

}
