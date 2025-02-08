package net.engineeringdigest.journalApp.entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;

@Document (collection = "users")						// this was a simple POJO file we have to (ORM) map it with a collection which will located in our database

@Data				// can generate all getter / setter and constructors 
					// at the compile time the code will be generated 
public class User {
	
	@Id
	private ObjectId id;
	
	
	// it will not create automatically we have to create a key in application.properties
	@Indexed(unique = true)				// indicate that it is unique
	@NonNull							// can't be null
	private String userName;
	@NonNull
	private String password;
	@DBRef								// Reference of journal_entries 
	private List<JournalEntry> journalEntries = new ArrayList<>();

	private List<String> role;
}
