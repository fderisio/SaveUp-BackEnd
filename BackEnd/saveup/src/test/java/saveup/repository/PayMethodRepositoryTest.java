package saveup.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import saveup.AbstractSaveUpIntegrationTests;
import saveup.domain.EntityTestUtils;
import saveup.domain.User;
import saveup.domain.PayMethod;

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
		//assertThat(repository.findById(999999L)).isNotPresent();
	}
	
	/* method for test purposes */
	private void assertNumUsers(int expected) {
		assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "payment_method")).isEqualTo(expected);
	}
	
}
