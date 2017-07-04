package saveup.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;
	
	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;
	
	@Column(nullable = false, unique = true, length = 75)
	private String email;
	
	@Column(nullable = false, length = 40)
	private String password;
	
	@Column(name = "last_login", updatable = false, nullable = true)
	private Date lastLogIn;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("id")
	private List<Category> categories = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("id")
	private List<Income> incomes = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("id")
	private List<PayMethod> paymethods = new ArrayList<>();
	
//	// NEW
//	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
//	@OrderBy("exp_date")
//	private List<Expense> expenses = new ArrayList<>();
//	//
	
	public User() {
		/* required by JPA */
	}
	
	public User(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	
	public User(Long id, String firstName, String lastName, String email, String password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	
	public void addCategory(Category category) {
		getCategories().add(category);
		category.setUser(this);
	}
	
	public void addPayMethod(PayMethod paymethod) {
		getPaymethods().add(paymethod);
		paymethod.setUser(this);
	}
	
	public void addIncome(Income income) {
		getIncomes().add(income);
		income.setUser(this);
	}
	
//	// NEW
//	public void addExpense(Category category, Expense expense) {
//		getExpenses().add(expense);
//		category.setUser(this);
//		expense.setUserExpenseCategory(category);
//		
//	}
	
}
