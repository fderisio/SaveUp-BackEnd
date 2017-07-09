package saveup.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import saveup.AbstractSaveUpIntegrationTests;
import saveup.domain.EntityTestUtils;
import saveup.domain.User;

public class UserServiceTests extends AbstractSaveUpIntegrationTests {

	private static final int NUM_TEST_USERS = 3;
	private static final int NUM_TEST_EXPENSES = 4;

	@Autowired
	UserService userService;
	@Autowired
	ExpenseService expenseService;
	
	@Test
	public void findAll() {
		assertThat(userService.findAll()).hasSize(NUM_TEST_USERS);
		assertThat(expenseService.findAll()).hasSize(NUM_TEST_EXPENSES);
	}

	@Test
	public void update() {
		userService.update(EntityTestUtils.createHomero());
		assertThat(userService.findAll()).hasSize(NUM_TEST_USERS + 1);
	}

	@Test
	public void deleteById() { 
		User user = userService.findById(1L);
		userService.deleteById(user.getId());
		assertThat(userService.findAll()).hasSize(NUM_TEST_USERS - 1);
	}

}
