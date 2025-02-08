package net.engineeringdigest.journalApp.service;



import java.lang.reflect.Array;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;


@Component
public class UserService {
	
	@Autowired
	private UserRepository userRepository; 
	
	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	
	public void saveEntry(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Array.asList("USER"));
		userRepository.save(user);
	}
	
	public void saveNewUser(User user) {
		userRepository.save(user);
	}
	
	public List<User> getAll(){
		return userRepository.findAll();
	}
	
	public Optional<User> findById(ObjectId id) {
		return userRepository.findById(id);	
	}
	
	public void deleteById(ObjectId id ) {
		userRepository.deleteById(id);
	}
	
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
}
