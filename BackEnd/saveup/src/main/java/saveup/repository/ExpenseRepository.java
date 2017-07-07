package saveup.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import saveup.domain.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{
	
	Optional<Expense> findById(Long id);

	List<Expense> findAllByCategoryId(Long id);
	
	List<Expense> findAll();
	
}
