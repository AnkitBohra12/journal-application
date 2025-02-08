package net.engineeringdigest.journalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement		// search for the transactional methods
									// after that it will create a transactional context or contianer 
									// where are the methods will be there and perform db tasks 
									// it will treated like one operation, if one fails then all will be rollback
									// atomaticity achieve ( means if one fails then all fails if one succed then all will )
									// isolation achieve ( if ther are two user running the api the it will create two different context box and perform there operations)
public class JournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);
	}

	@Bean
	public PlatformTransactionManager add(MongoDatabaseFactory dbFactory) {
		return new MongoTransactionManager(dbFactory);
	}
	
}
