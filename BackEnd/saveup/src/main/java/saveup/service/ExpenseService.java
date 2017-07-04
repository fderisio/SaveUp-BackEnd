package saveup.service;

import java.util.List;

import saveup.domain.Expense;

public interface ExpenseService {

	Expense registerNewExpense(Expense expense);

	Expense findById(Long id);

	List<Expense> findAll();

	List<Expense> findAllByCategoryId(Long id);

	List<Expense> findByTextContaining(String searchText);
	
}
