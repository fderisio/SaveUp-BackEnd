package saveup.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import saveup.domain.Category;
import saveup.domain.Expense;
import saveup.domain.PayMethod;
import saveup.repository.ExpenseRepository;

@Transactional(readOnly = true)
@Service
public class DefaultExpenseService implements ExpenseService{
	
	private static final Logger logger = LoggerFactory.getLogger(DefaultExpenseService.class);

	private final ExpenseRepository expenseRepository;
	private final CategoryService categoryService;
	private final PayMethodService paymethodService;
	
	@Autowired
	public DefaultExpenseService(ExpenseRepository expenseRepository,
			CategoryService categoryService, PayMethodService paymethodService) {
		this.expenseRepository = expenseRepository;
		this.categoryService = categoryService;
		this.paymethodService = paymethodService;
	}
	
	@Transactional(readOnly = false)
	@Override
	public void registerNewExpense(Expense expense, Long categoryId, Long paymentId) {
		logger.trace("Saving expense [{}].", expense);

		// make sure we are saving a new expense and not accidentally
		// updating an existing one.
		expense.setId(null);

		expenseRepository.save(expense);
		
		// add expense to category list
		Category category = categoryService.findById(categoryId);
		category.addExpense(expense);
		
		// add expense to payment method list
		PayMethod paymethod = paymethodService.findById(paymentId);
		paymethod.addExpense(expense);
		
		expense.setCategory(category);
		expense.setPayMethod(paymethod);
	}

	@Transactional(readOnly = false)
	@Override
	public Expense update(Expense expense) {
		logger.trace("Updating expense [{}].", expense);
		return expenseRepository.save(expense);
	}
	
	@Override
	public Expense findById(Long id) {
		logger.trace("Finding expense with ID: {}", id);
		return expenseRepository.findById(id).orElseThrow(
			() -> new EntityNotFoundException("Could not find expense with ID [" + id + "]"));
	}

	@Override
	public List<Expense> findAllByCategoryId(Long categoryId) {
		logger.trace("Finding all expenses with category ID: {}", categoryId);
		return this.expenseRepository.findAllByCategoryId(categoryId);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void deleteById(Long id) {
		logger.trace("Deleting expense with ID [{}].", id);
		expenseRepository.delete(id);
	}

	@Override
	public List<Expense> findAll() {
		logger.trace("Finding all expenses");
		return this.expenseRepository.findAll();
	}

	@Override
	public List<Expense> retrieveAllExpensesForUser(Long userId) {
		List<Category> userCategories = categoryService.findAllByUserId(userId);
		List<Expense> userExpenses = new ArrayList<Expense>();
		for (Integer i = 0; i<userCategories.size(); i++) {
			List<Expense> expenses = expenseRepository.findAllByCategoryId(userCategories.get(i).getId());
			userExpenses.addAll(expenses);
		}
		return userExpenses;
	}

	
}
