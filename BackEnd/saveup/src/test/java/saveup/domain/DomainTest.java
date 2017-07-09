package saveup.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class DomainTest {

	@Test
	public void UserTest() {
		User user = new User ("Carol", "Jackson", "carol@aol.com", "hello123");
		assertThat(user.getFirstName()).isEqualTo("Carol");
	}

	@Test
	public void ExpCategoryTest() {
		User user = new User ("Carol", "Jackson", "carol@aol.com", "hello123");
		Category expCategory = new Category ("Leisure", user, false);
		assertThat(expCategory.getName()).isEqualTo("Leisure");
	}
	
	@Test
	public void IncomeTest() {
		User user = new User (1L, "Carol", "Jackson", "carol@aol.com", "hello123");
		Income income = new Income (user, 5000, "12.04.2016", true);
		assertThat(income.getUser().getId()).isEqualTo(1L);
	}
	
	@Test
	public void PayMethodTest() {
		User user = new User ("Carol", "Jackson", "carol@aol.com", "hello123");
		PayMethod payMethod = new PayMethod (user, "Visa", "HSBC");
		assertThat(payMethod.getUser().getFirstName()).isEqualTo("Carol");
	}
	
	@Test
	public void ExpenseTest() {
		User user = new User (1L, "Carol", "Jackson", "carol@aol.com", "hello123");
		Category expCategory = new Category ("Leisure", user, false);
		PayMethod payMethod = new PayMethod (user, "Visa", "HSBC");
		Expense expense = new Expense (expCategory, "Cinema", "2017-06-14T00:00:00.000Z", 20.50, payMethod);
		assertThat(expense.getCategory().getUser().getId()).isEqualTo(1L);
	}

}
