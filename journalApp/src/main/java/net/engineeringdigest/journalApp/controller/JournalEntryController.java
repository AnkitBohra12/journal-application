/* package net.engineeringdigest.journalApp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.engineeringdigest.journalApp.entity.JournalEntry;

@RestController
@RequestMapping("/_journal")
public class JournalEntryController {
	
	private Map<Long, JournalEntry> journalEntries = new HashMap<>();			//In memory data use
	
	@GetMapping
	public List<JournalEntry> getAll() {
		return new ArrayList<>(journalEntries.values());	// local host:8080/journal	
	}
	
	@PostMapping
	public boolean createEntry(@RequestBody JournalEntry myEntry) {			// post at the same URL (localhost:8080/journal)
		//journalEntries.put(myEntry.getId(), myEntry);						// this will invoke 	
		return true; 
	}
	
	@GetMapping("id/{myId}")
	public JournalEntry getJournalEntryById(@PathVariable Long myId) {
		return journalEntries.get(myId);
	}
	
	@DeleteMapping("id/{myId}")
	public JournalEntry deleteJournalEntryById(@PathVariable Long myId) {
		return journalEntries.remove(myId);
	}
	
	@PutMapping("/id/{id}")
	public JournalEntry updateJournalEntryById(@PathVariable Long id, @RequestBody JournalEntry myEntry) {
		return journalEntries.put(id, myEntry);
	}

} */
