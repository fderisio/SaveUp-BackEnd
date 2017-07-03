package saveup.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import saveup.AbstractSaveUpIntegrationTests;
import saveup.domain.EntityTestUtils;
import saveup.domain.User;
import saveup.domain.Category;

public class CategoryRepositoryTest extends AbstractSaveUpIntegrationTests {

	private static final int NUM_TEST_CATEGORIES = 3;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Test
	public void count() {
		assertThat(categoryRepository.count()).isEqualTo(NUM_TEST_CATEGORIES);
	}
	
	@Test
	public void save() {
		User user = userRepository.save(EntityTestUtils.createHomero());
		Category category = EntityTestUtils.createNewCategory();
		category.setUser(user);
		categoryRepository.save(category);
		assertNumUsers(NUM_TEST_CATEGORIES + 1);
	}
	
	@Test
	public void findById() {
		assertThat(categoryRepository.findById(1L).get().getName()).isEqualTo("Leisure");
		//assertThat(repository.findById(999999L)).isNotPresent();
	}
	
	/* method for test purposes */
	private void assertNumUsers(int expected) {
		assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "category")).isEqualTo(expected);
	}
}
