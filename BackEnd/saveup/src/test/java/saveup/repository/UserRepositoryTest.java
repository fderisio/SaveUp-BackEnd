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
import saveup.domain.User;

public class UserRepositoryTest extends AbstractSaveUpIntegrationTests {

	private static final int NUM_TEST_USERS = 3;
	
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
	}
	
	@Test
	public void findByEmailAndPassword() {
		assertThat(repository.findByEmailAndPassword("eva@aol.com", "hello123").get().getFirstName()).isEqualTo("Eva");
	}
	
	@Test
	public void findAll() {
		List<String> firstNames = repository.findAll().stream().map(User::getFirstName).collect(toList());
		assertThat(firstNames).containsExactlyInAnyOrder("Eva", "Jack", "Susan");
	}

	@Test
	public void deleteById() { 
		assertNumUsers(NUM_TEST_USERS);
		User user = repository.findById(1L).get();
		repository.delete(user.getId());
		repository.flush();
		assertNumUsers(NUM_TEST_USERS - 1);
	}


	/* method for test purposes */
	private void assertNumUsers(int expected) {
		assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "user")).isEqualTo(expected);
	}
}
