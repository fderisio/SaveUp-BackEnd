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
		UserExpenseCategory expCategory = new UserExpenseCategory ("Leisure", user, false);
		assertThat(expCategory.getName()).isEqualTo("Leisure");
	}
	
	@Test
	public void IncomeTest() {
		User user = new User (1L, "Carol", "Jackson", "carol@aol.com", "hello123");
		UserIncome income = new UserIncome (user, 5000, "12.04.2016", true);
		assertThat(income.getUser().getId()).isEqualTo(1L);
	}
	
	@Test
	public void PayMethodTest() {
		User user = new User ("Carol", "Jackson", "carol@aol.com", "hello123");
		UserPayMethod payMethod = new UserPayMethod (user, "Visa", "HSBC");
		assertThat(payMethod.getUser().getFirstName()).isEqualTo("Carol");
	}
	
	@Test
	public void ExpenseTest() {
		User user = new User (1L, "Carol", "Jackson", "carol@aol.com", "hello123");
		UserExpenseCategory expCategory = new UserExpenseCategory ("Leisure", user, false);
		UserPayMethod payMethod = new UserPayMethod (user, "Visa", "HSBC");
		Expense expense = new Expense (expCategory, "Cinema", "12.06.2017", 20.50, payMethod);
		assertThat(expense.getUserExpenseCategory().getUser().getId()).isEqualTo(1L);
	}

}
