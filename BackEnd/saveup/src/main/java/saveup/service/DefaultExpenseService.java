package saveup.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import saveup.domain.Expense;
import saveup.repository.ExpenseRepository;

public class DefaultExpenseService implements ExpenseService{
	
	private static final Logger logger = LoggerFactory.getLogger(DefaultCategoryService.class);

	private final ExpenseRepository expenseRepository;
	
	@Autowired
	public DefaultExpenseService(ExpenseRepository expenseRepository) {
		this.expenseRepository = expenseRepository;
	}
	
	@Transactional(readOnly = false)
	@Override
	public Expense registerNewExpense(Expense expense) {
		logger.trace("Saving expense [{}].", expense);

		// Make sure we are saving a new expense and not accidentally
		// updating an existing one.
		expense.setId(null);

		return expenseRepository.save(expense);
	}

	@Override
	public Expense findById(Long id) {
		logger.trace("Finding expense with ID: {}", id);
		return expenseRepository.findById(id).orElseThrow(
			() -> new EntityNotFoundException("Could not find expense with ID [" + id + "]"));
	}

	@Override
	public List<Expense> findAll() {
		logger.trace("Finding all expenses");
		return this.expenseRepository.findAll();
	}

	@Override
	public List<Expense> findAllByCategoryId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Expense> findByTextContaining(String searchText) {
		// TODO Auto-generated method stub
		return null;
	}

}
