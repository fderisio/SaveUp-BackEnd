package saveup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import saveup.domain.UserPayMethod;

public interface UserPayMethodRepository extends JpaRepository<UserPayMethod, Long>{
	
	Optional<UserPayMethod> findByid(Long id);
	
}
