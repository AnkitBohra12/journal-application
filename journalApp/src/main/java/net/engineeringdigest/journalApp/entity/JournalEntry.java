package net.engineeringdigest.journalApp.entity;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document (collection = "journal_entries")						// this was a simple POJO file we have to (ORM) map it with a collection which will located in our database

@Data				// can generate all getter / setter and constructors 
					// at the complile time the code will be generated 

@NoArgsConstructor
public class JournalEntry {
	
	@Id
	private ObjectId id;
	
	@NonNull
	private String title;
	
	private String content;
	
	private LocalDateTime date;

}
