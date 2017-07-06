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
import saveup.domain.JsonViews;

@Entity
@Data
@ToString
@Table(name = "payment_method")
public class PayMethod implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonView(JsonViews.Public.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id")
	private User user;
	
	@JsonView(JsonViews.Public.class)
	@Column(nullable = false, length = 20)
	private String name;
	
	@JsonView(JsonViews.Public.class)
	@Column(nullable = true, length = 40)
	private String bank;
	
	@OneToMany(mappedBy = "payMethod", cascade = CascadeType.ALL, orphanRemoval = true)
	@OnDelete(action = OnDeleteAction.CASCADE) // deletes payment methods even with expenses attached
	@OrderBy("expense_date")
	private List<Expense> expenses = new ArrayList<>();
	
	public PayMethod() {
		/* required by JPA */
	}
	
	public PayMethod(User user, String name) {
		this.user = user;
		this.name = name;
	}
	
	public PayMethod(User user, String name, String bank) {
		this.user = user;
		this.name = name;
		this.bank = bank;
	}
	
	public void addExpense(Expense expense) {
		getExpenses().add(expense);
		expense.setPayMethod(this);
	}
}
