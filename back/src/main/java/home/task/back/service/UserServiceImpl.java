package home.task.back.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import home.task.back.model.User;
import home.task.back.repository.UserRepository;



@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	public Optional<User> getById(Integer id) {
		return userRepository.findById(id);
	}

	public void save(User user) {
		userRepository.save(user);
	}

	public void delete(Integer id) {
		userRepository.deleteById(id);
	}

	public List<User> getAll() {
		return userRepository.findAll();
	}

}
