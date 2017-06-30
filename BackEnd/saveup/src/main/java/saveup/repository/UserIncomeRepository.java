package saveup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import saveup.domain.Expense;

public interface UserIncomeRepository extends JpaRepository<Expense, Long>{
	
	Optional<Expense> findByid(Long id);
	
}
