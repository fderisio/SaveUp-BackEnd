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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@ToString
@Table(name = "category")
public class Category implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonView(JsonViews.Public.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@JsonView(JsonViews.Public.class)
	@Column(nullable = false, length = 20)
	private String name;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id")
	private User user;
	
	@JsonView(JsonViews.Public.class)
	@Column(nullable = false)
	private boolean fixed = false;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("expense_date")
	@OnDelete(action = OnDeleteAction.CASCADE) // deletes category even with expenses attached
	private List<Expense> expenses = new ArrayList<>();
	
	public Category() {
		/* required by JPA */
	}
	
	public Category(String name, User user, boolean fixed) {
		this.name = name;
		this.user = user;
		this.fixed = fixed;
	}
	
	public void addExpense(Expense expense) {
		getExpenses().add(expense);
		expense.setCategory(this);
	}
}
