package saveup.domain;

import java.io.Serializable;
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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = { "password" })
@Table(name = "user")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonView(JsonViews.Public.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@JsonView(JsonViews.Public.class)
	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;
	
	@JsonView(JsonViews.Public.class)
	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;
	
	@JsonView(JsonViews.Public.class)
	@Column(nullable = false, unique = true, length = 75)
	private String email;
	
	@Column(nullable = false, length = 75)
	private String password;
	
	@Column(name = "last_login", updatable = false, nullable = true)
	private String lastLogIn;
	
	@JsonView(JsonViews.Public.class)
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@OnDelete(action = OnDeleteAction.CASCADE) // deletes users even with categories attached
	@OrderBy("name")
	private List<Category> categories = new ArrayList<>();
	
	@JsonView(JsonViews.Public.class)
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OrderBy("id")
	private List<Income> incomes = new ArrayList<>();
	
	@JsonView(JsonViews.Public.class)
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OrderBy("name")
	private List<PayMethod> paymethods = new ArrayList<>();
	
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

}
