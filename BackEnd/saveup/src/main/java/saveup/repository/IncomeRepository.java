package saveup.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import saveup.domain.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long>{
	
	Optional<Income> findById(Long id);

	List<Income> findByUserId(Long id);
	
}
