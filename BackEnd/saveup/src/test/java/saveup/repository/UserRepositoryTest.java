package saveup.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import saveup.AbstractSaveUpIntegrationTests;
import saveup.domain.EntityTestUtils;

public class UserRepositoryTest extends AbstractSaveUpIntegrationTests {

	private static final int NUM_TEST_USERS = 2;
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Test
	public void count() {
		assertThat(repository.count()).isEqualTo(NUM_TEST_USERS);
	}
	
	@Test
	public void save() {
		repository.save(EntityTestUtils.createHomero());
		assertNumUsers(NUM_TEST_USERS + 1);
	}
	
	@Test
	public void findById() {
		assertThat(repository.findById(1L).get().getFirstName()).isEqualTo("Eva");
		//assertThat(repository.findById(999999L)).isNotPresent();
	}
	
	/* method for test purposes */
	private void assertNumUsers(int expected) {
		assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "user")).isEqualTo(expected);
	}

}
