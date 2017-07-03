package saveup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import saveup.domain.PayMethod;

public interface PayMethodRepository extends JpaRepository<PayMethod, Long>{
	
	Optional<PayMethod> findById(Long id);
	
}
