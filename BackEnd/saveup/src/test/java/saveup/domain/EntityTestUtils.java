package saveup.domain;

public class EntityTestUtils {

	public static User createHomero() {
		User homero = new User(3L, "Homero", "Tompson", "jack15@aol.com", "hello1234");
		return homero;
	}
	
	public static UserExpenseCategory createNewCategory() {
		UserExpenseCategory expCategory = new UserExpenseCategory ("Leisure", createHomero(), false);
		return expCategory;
	}
	
	public static UserIncome createIncome() {
		UserIncome income = new UserIncome (createHomero(), 5000, "12.04.2016", true);
		return income;
	}
	
	public static UserPayMethod createPayMethod() {
		UserPayMethod payMethod = new UserPayMethod (createHomero(), "Visa", "HSBC");
		return payMethod;
	}
	
	public static Expense createExpeense() {
		Expense expense = new Expense (createNewCategory(), "Cinema", "12.06.2017", 20.50, createPayMethod());
		return expense;
	}
	
}
