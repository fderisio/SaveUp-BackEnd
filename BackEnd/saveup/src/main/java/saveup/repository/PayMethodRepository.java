package saveup.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import saveup.domain.PayMethod;

@Repository
public interface PayMethodRepository extends JpaRepository<PayMethod, Long>{
	
	Optional<PayMethod> findById(Long id);

	List<PayMethod> findByUserId(Long id);
	
}
