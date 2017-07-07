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
import saveup.domain.PayMethod;
import saveup.domain.User;

public class PayMethodRepositoryTest extends AbstractSaveUpIntegrationTests {

	private static final int NUM_TEST_PAYMENT_METHODS = 4;
	
	@Autowired
	PayMethodRepository payMethodsRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Test
	public void count() {
		assertThat(payMethodsRepository.count()).isEqualTo(NUM_TEST_PAYMENT_METHODS);
	}
	
	@Test
	public void save() {
		User user = userRepository.findById(1L).get();
		PayMethod payMethod = EntityTestUtils.createPayMethod();
		payMethod.setUser(user);
		payMethodsRepository.save(payMethod);
		assertNumUsers(NUM_TEST_PAYMENT_METHODS + 1);
	}
	
	@Test
	public void findById() {
		assertThat(payMethodsRepository.findById(1L).get().getName()).isEqualTo("MasterCard");
	}
	
	@Test
	public void findByUserId() {
		List<String> categories = payMethodsRepository.findByUserId(1L).stream().map(PayMethod::getName).collect(toList());
		assertThat(categories).containsExactlyInAnyOrder("MasterCard", "American Express");
		List<Long> ids = payMethodsRepository.findByUserId(1L).stream().map(PayMethod::getId).collect(toList());
		assertThat(ids).containsExactlyInAnyOrder(1L, 4L);
	}
	
	@Test
	public void deleteById() {
		assertNumUsers(NUM_TEST_PAYMENT_METHODS);
		PayMethod paymethod = payMethodsRepository.findById(1L).get();
		payMethodsRepository.delete(paymethod.getId());
		payMethodsRepository.flush();
		assertNumUsers(NUM_TEST_PAYMENT_METHODS - 1);
	}
	
	/* method for test purposes */
	private void assertNumUsers(int expected) {
		assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "payment_method")).isEqualTo(expected);
	}
	
}
