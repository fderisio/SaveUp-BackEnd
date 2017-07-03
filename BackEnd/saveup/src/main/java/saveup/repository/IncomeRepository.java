package saveup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import saveup.domain.Income;

public interface IncomeRepository extends JpaRepository<Income, Long>{
	
	Optional<Income> findById(Long id);
	
}
