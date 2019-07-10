package home.task.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import home.task.back.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
