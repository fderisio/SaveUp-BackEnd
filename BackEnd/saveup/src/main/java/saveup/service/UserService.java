package saveup.service;

import java.util.List;

import saveup.domain.User;

public interface UserService {
	
	User registerNewUser(User user);

	User update(User user);

	void deleteById(Long id);

	User findById(Long id);

	User findByUsername(String username);

	List<User> findAll();
	
}
