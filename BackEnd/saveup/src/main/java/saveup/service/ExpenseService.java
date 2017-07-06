package saveup.service;

import java.util.List;

import saveup.domain.Expense;

public interface ExpenseService {

	Expense registerNewExpense(Expense expense, Long categoryId, Long paymethodId);

	Expense findById(Long id);

	List<Expense> findAll();

	Expense update(Expense expense);
	
	void deleteById(Long id);
	
}
