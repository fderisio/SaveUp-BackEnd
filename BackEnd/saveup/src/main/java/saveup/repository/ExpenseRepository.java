package saveup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import saveup.domain.UserIncome;

public interface ExpenseRepository extends JpaRepository<UserIncome, Long>{
	
	Optional<UserIncome> findByid(Long id);
	
}
