package home.task.back.service;

import java.util.List;
import java.util.Optional;
import home.task.back.model.User;


public interface UserService {
	Optional<User> getById(Integer id);
	void save(User user);
	void delete(Integer id);
	List<User> getAll();
}
