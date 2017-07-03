package saveup.domain;

public class EntityTestUtils {

	public static User createHomero() {
		User homero = new User(3L, "Homero", "Tompson", "jack15@aol.com", "hello1234");
		return homero;
	}
	
	public static Category createNewCategory() {
		Category expCategory = new Category ("Leisure", createHomero(), false);
		return expCategory;
	}
	
	public static Income createIncome() {
		Income income = new Income (createHomero(), 5000, "12.04.2016", true);
		return income;
	}
	
	public static PayMethod createPayMethod() {
		PayMethod payMethod = new PayMethod (createHomero(), "Visa", "HSBC");
		return payMethod;
	}
	
	public static Expense createExpense() {
		Expense expense = new Expense (createNewCategory(), "Cinema", "12.06.2017", 20.50, createPayMethod());
		return expense;
	}
	
}
