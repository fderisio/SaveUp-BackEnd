package saveup.service;

import java.util.List;

import saveup.domain.Income;

public interface IncomeService {

	void saveIncomeForUser(Income income, Long id);
	
	Income findById(Long id);
	
	List<Income> findAll();
	
	List<Income> findAllByUserId(Long id);
	
	void deleteById(Long id);
}
