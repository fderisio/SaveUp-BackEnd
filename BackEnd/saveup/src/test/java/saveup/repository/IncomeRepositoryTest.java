package saveup.repository;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import saveup.AbstractSaveUpIntegrationTests;
import saveup.domain.EntityTestUtils;
import saveup.domain.Income;
import saveup.domain.User;

public class IncomeRepositoryTest extends AbstractSaveUpIntegrationTests {

	private static final int NUM_TEST_INCOMES = 3;
	
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
	
	@Test
	public void findByUserId() {
		List<Integer> incomes = incomeRepository.findByUserId(1L).stream().map(Income::getAmount).collect(toList());
		assertThat(incomes).containsExactlyInAnyOrder(8000, 9000);
		List<Integer> incomes2 = incomeRepository.findByUserId(2L).stream().map(Income::getAmount).collect(toList());
		assertThat(incomes2).containsExactlyInAnyOrder(5000);
	}
	
	@Test
	public void deleteById() {
		assertNumUsers(NUM_TEST_INCOMES);
		Income income = incomeRepository.findById(1L).get();
		incomeRepository.delete(income.getId());
		incomeRepository.flush();
		assertNumUsers(NUM_TEST_INCOMES - 1);
	}

	/* method for test purposes */
	private void assertNumUsers(int expected) {
		assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "income")).isEqualTo(expected);
	}
}
