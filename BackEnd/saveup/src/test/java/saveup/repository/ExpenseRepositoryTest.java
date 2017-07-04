package saveup.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import saveup.AbstractSaveUpIntegrationTests;
import saveup.domain.Category;
import saveup.domain.EntityTestUtils;
import saveup.domain.Expense;
import saveup.domain.PayMethod;
import saveup.domain.User;

public class ExpenseRepositoryTest extends AbstractSaveUpIntegrationTests {

	private static final int NUM_TEST_EXPENSES = 4;
	
	@Autowired
	ExpenseRepository expenseRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	PayMethodRepository payMethodsRepository;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Test
	public void count() {
		assertThat(expenseRepository.count()).isEqualTo(NUM_TEST_EXPENSES);
	}
	
	@Test
	public void save() {
		// creates an user
		User user = userRepository.save(EntityTestUtils.createHomero());
		
		// creates a category
		Category category = EntityTestUtils.createNewCategory();
		category.setUser(user);
		categoryRepository.save(category);
		
		// creates a payment method
		PayMethod payMethod = EntityTestUtils.createPayMethod();
		payMethod.setUser(user);
		payMethodsRepository.save(payMethod);
		
		// creates and saves an expense
		Expense expense = EntityTestUtils.createExpense();
		expense.setUserExpenseCategory(category);
		expense.setUserPayMethod(payMethod);
		expenseRepository.save(expense);
		
		assertNumUsers(NUM_TEST_EXPENSES + 1);

	}
	
	@Test
	public void findById() {
		assertThat(expenseRepository.findById(1L).get().getUserExpenseCategory().getName()).isEqualTo("Leisure");
		//assertThat(repository.findById(999999L)).isNotPresent();
	}
	
	/* method for test purposes */
	private void assertNumUsers(int expected) {
		assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "expense")).isEqualTo(expected);
	}
	
}
