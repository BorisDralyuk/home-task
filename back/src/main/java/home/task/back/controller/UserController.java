package home.task.back.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import home.task.back.model.User;
import home.task.back.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	
	@Autowired
	private UserService userService;
	
	@GetMapping(value="/users")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> users = this.userService.getAll();
		
	    if(users.isEmpty()) {
	    	return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
	    }
	    return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@GetMapping(value="/users/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") Integer userId){
		Optional<User> user = this.userService.getById(userId);
		
	    if(!user.isPresent()) {
	    	return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	    }
	    return new ResponseEntity<User>(user.get(), HttpStatus.OK);
	}
	
	@GetMapping(value="/users/{id}/subscribers")
	public ResponseEntity<List<User>> getAllSubscribers(@PathVariable("id") Integer userId){
		Optional<User> user = this.userService.getById(userId);
		
	    if(!user.isPresent()) {
	    	return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
	    }
	    List<User> res = user.get().getSubscribers();
	    return new ResponseEntity<List<User>>(res, HttpStatus.OK);
	}
	
	
	@GetMapping(value="/users/{id}/subscriptions")
	public ResponseEntity<List<User>> getAllSubscriptions(@PathVariable("id") Integer userId){
		Optional<User> user = this.userService.getById(userId);
		
	    if(!user.isPresent()) {
	    	return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
	    }
	    List<User> res = user.get().getSubscriptions();
	    return new ResponseEntity<List<User>>(res, HttpStatus.OK);
	}
	

	@PostMapping(value="/users")
	public ResponseEntity<User> save(@RequestBody @Valid User user){
		HttpHeaders headers = new HttpHeaders();
		
	    if(user == null) {
	    	return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
	    }
	    this.userService.save(user);
	    return new ResponseEntity<User>(user, headers, HttpStatus.CREATED);
	}
	
	@PostMapping(value="/users/{id}/subscriber/{subscriberId}")
	public HttpStatus subscribe(@PathVariable("id") Integer userId, @PathVariable("subscriberId") Integer subscriberId){
		if(userId == subscriberId) {
			return HttpStatus.BAD_REQUEST;
		}
		Optional<User> user =  this.userService.getById(userId);
		Optional<User> subUser =  this.userService.getById(subscriberId);
		
		if(user.get().getSubscribers().contains(subUser.get())) {
			User u = user.get();
	    	u.getSubscribers().remove(subUser.get());
	    	this.userService.save(u);
			return HttpStatus.CREATED;
		}
		
	    if(user.isPresent() && subUser.isPresent()) {
	    	User u = user.get();
	    	u.getSubscribers().add(subUser.get());
	    	this.userService.save(u);
	    }
	    return HttpStatus.CREATED;
	}
	
	@PostMapping(value="/users/subscription/{id}")
	public void subscription(@PathVariable("id") Integer userId, @RequestBody @Valid User user){
		Optional<User> subscriptionUser =  this.userService.getById(userId);
		
	    if(subscriptionUser.isPresent()) {
	    	User s = subscriptionUser.get();
	    	s.getSubscriptions().add(user);
	    	this.userService.save(s);
	    }

	}
	
}
