
package saveup.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import saveup.domain.User;
import saveup.repository.UserRepository;

@Transactional(readOnly = true)
@Service
public class DefaultUserService implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultUserService.class);

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public DefaultUserService(UserRepository userRepository,
			PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Transactional(readOnly = false)
	@Override
	public User registerNewUser(User user) {
		logger.trace("Registering new user [{}].", user);
		// Make sure we are saving a new user and not accidentally
		// updating an existing user.
		user.setId(null);

		// Encrypt password before storing in database.
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		// A new user cannot have categories, pay methods or incomes before the account exists.
		user.getCategories().clear();
		user.getPaymethods().clear();
		user.getIncomes().clear();
		
		User registeredUser = userRepository.save(user);
		
		return registeredUser;
	}

	@Transactional(readOnly = false)
	@Override
	public User update(User user) {
		logger.trace("Updating user [{}].", user);
		return userRepository.save(user);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteById(Long id) {
		logger.trace("Deleting user with ID [{}].", id);
		userRepository.delete(id);
	}

	@Override
	public User findById(Long id) {
		logger.trace("Finding user by ID [{}].", id);
		return userRepository.findById(id).orElseThrow(
			() -> new EntityNotFoundException("Could not find User with ID [" + id + "]"));
	}

	@Override
	public List<User> findAll() {
		logger.trace("Finding all users.");
		return userRepository.findAll();
	}

	@Override
	public User findByEmailAndPassword(String email, String password) {
		logger.trace("Finding user by email [{}].", email);
		return userRepository.findByEmailAndPassword(email, password).orElseThrow(
				() -> new EntityNotFoundException("Could not find User with email [" + email + "]"));
	}
	
//// -- Spring Security: UserDetailsService ----------------------------------
//
//	@Override
//	public UserDetails loadUserByUsername(Long userId) throws UsernameNotFoundException {
//		logger.trace("Loading user by user ID [{}] for Spring Security.", userId);
//		return userRepository.findByUsernameWithRolesEagerlyLoaded(userId).orElseThrow(
//			() -> new UsernameNotFoundException("Could not find user with username [" + username + "]"));
//	}

}
