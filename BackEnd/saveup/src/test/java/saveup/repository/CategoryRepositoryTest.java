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
import saveup.domain.User;

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
	
	@Test
	public void findByUserId() {
		List<String> categories = categoryRepository.findAllByUserId(1L).stream().map(Category::getName).collect(toList());
		assertThat(categories).containsExactlyInAnyOrder("Leisure", "Chemist");
		List<Long> ids = categoryRepository.findAllByUserId(1L).stream().map(Category::getId).collect(toList());
		assertThat(ids).containsExactlyInAnyOrder(1L, 2L);
	}
	
	@Test
	public void deleteById() { // Works only for an unused category
		assertNumUsers(NUM_TEST_CATEGORIES);
		Category category = categoryRepository.findById(2L).get();
		categoryRepository.delete(category.getId());
		categoryRepository.flush();
		assertNumUsers(NUM_TEST_CATEGORIES - 1);
	}
	
	/* method for test purposes */
	private void assertNumUsers(int expected) {
		assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "category")).isEqualTo(expected);
	}
}
