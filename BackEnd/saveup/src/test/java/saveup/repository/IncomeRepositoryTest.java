package saveup.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import saveup.AbstractSaveUpIntegrationTests;
import saveup.domain.EntityTestUtils;
import saveup.domain.User;
import saveup.domain.Income;

public class IncomeRepositoryTest extends AbstractSaveUpIntegrationTests {

	private static final int NUM_TEST_INCOMES = 2;
	
	@Autowired
	IncomeRepository incomeRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Test
	public void count() {
		assertThat(incomeRepository.count()).isEqualTo(NUM_TEST_INCOMES);
	}
	
	@Test
	public void save() {
		User user = userRepository.save(EntityTestUtils.createHomero());
		Income income = EntityTestUtils.createIncome();
		income.setUser(user);
		incomeRepository.save(income);
		assertNumUsers(NUM_TEST_INCOMES + 1);
	}
	
	@Test
	public void findById() {
		assertThat(incomeRepository.findById(1L).get().getUser().getFirstName()).isEqualTo("Eva");
		//assertThat(repository.findById(999999L)).isNotPresent();
	}
	
	/* method for test purposes */
	private void assertNumUsers(int expected) {
		assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "income")).isEqualTo(expected);
	}
}
