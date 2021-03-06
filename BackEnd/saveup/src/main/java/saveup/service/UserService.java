package saveup.service;

import java.util.List;

import saveup.domain.User;

public interface UserService {
	
	User registerNewUser(User user);

	User update(User user);

	void deleteById(Long id);

	User findById(Long id);

	List<User> findAll();

	User findByEmailAndPassword(String email, String password);

//	UserDetails loadUserByUsername(Long userId) throws UsernameNotFoundException;
	
}
