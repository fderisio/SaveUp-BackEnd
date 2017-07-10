package saveup.service;

import java.util.List;

import saveup.domain.Expense;

public interface ExpenseService {

	Expense registerNewExpense(Expense expense, Long categoryId, Long paymentId);

	Expense findById(Long id);

	Expense update(Expense expense);
	
	void deleteById(Long id);

	List<Expense> findAllByCategoryId(Long categoryId);
	
	List<Expense> findAll();

	List<Expense> retrieveAllExpensesForUser(Long userId);
	
}
