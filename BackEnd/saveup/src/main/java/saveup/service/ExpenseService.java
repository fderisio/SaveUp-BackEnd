package saveup.service;

import java.util.List;

import saveup.domain.Expense;

public interface ExpenseService {

	Expense saveExpenseForCategory(Expense expense, Long id);

	Expense findById(Long id);

	List<Expense> findAll();

	List<Expense> findAllByCategoryId(Long id);

	List<Expense> findAllContainingText(String searchText);
	
}
