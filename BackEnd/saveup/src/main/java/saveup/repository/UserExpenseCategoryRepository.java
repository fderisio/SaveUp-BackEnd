package saveup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import saveup.domain.UserExpenseCategory;

public interface UserExpenseCategoryRepository extends JpaRepository<UserExpenseCategory, Long>{
	
	Optional<UserExpenseCategory> findByid(Long id);
	
}
