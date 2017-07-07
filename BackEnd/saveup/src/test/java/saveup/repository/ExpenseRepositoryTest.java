package saveup.repository;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

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
		category.addExpense(expense);
		payMethod.addExpense(expense);
		expense.setCategory(category);
		expense.setPayMethod(payMethod);
		expenseRepository.save(expense);
				
		assertNumUsers(NUM_TEST_EXPENSES + 1);
	}
	
	@Test
	public void findById() {
		assertThat(expenseRepository.findById(1L).get().getCategory().getName()).isEqualTo("Leisure");
	}
	
	@Test
	public void findAllByCategoryId() {
		List<String> expensesTotals = expenseRepository.findAllByCategoryId(1L).stream().map(Expense::getText).collect(toList());
		assertThat(expensesTotals).containsExactlyInAnyOrder("Dinner", "Cinema");
	}
	
	@Test
	public void deleteById() {
		assertNumUsers(NUM_TEST_EXPENSES);
		Expense expense = expenseRepository.findById(1L).get();
		expenseRepository.delete(expense.getId());
		expenseRepository.flush();
		assertNumUsers(NUM_TEST_EXPENSES - 1);
	}
	
	/* method for test purposes */
	private void assertNumUsers(int expected) {
		assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "expense")).isEqualTo(expected);
	}
}
