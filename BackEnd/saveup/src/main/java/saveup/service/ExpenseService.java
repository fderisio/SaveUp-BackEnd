package saveup.service;

import java.util.List;

import saveup.domain.Category;
import saveup.domain.Expense;
import saveup.domain.PayMethod;

public interface ExpenseService {

	Expense save(Expense expense, Category category, PayMethod paymethod);

	Expense findById(Long id);

	Expense update(Expense expense);
	
	void deleteById(Long id);

	List<Expense> findAllByCategoryId(Long categoryId);
	
	List<Expense> findAll();

	List<Expense> retrieveAllExpensesForUser(Long userId);
	
}
