package saveup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import saveup.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findById(Long id);
	
}
