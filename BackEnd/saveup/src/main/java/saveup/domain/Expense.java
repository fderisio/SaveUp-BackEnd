package saveup.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "expense")
public class Expense implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonView(JsonViews.Public.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@JsonView(JsonViews.Public.class)
	@JsonIgnoreProperties({ "name", "fixed" })
	@ManyToOne(optional = false)
	@JoinColumn(name = "category_id")
	private Category category;
	
	@JsonView(JsonViews.Public.class)
	@Column(nullable = false, length = 40)
	private String text;
	
	@JsonView(JsonViews.Public.class)
	@Column(nullable = true, length = 30)
	private String store;
	
	@JsonView(JsonViews.Public.class)
	@Column(name = "expense_date", nullable = false, length = 10)
	private String expenseDate;
	
	@JsonView(JsonViews.Public.class)
	@Column(nullable = false, length = 15)
	private Double total;
	
	@JsonView(JsonViews.Public.class)
	@JsonIgnoreProperties({ "name", "bank" })
	@ManyToOne(optional = false)
	@JoinColumn(name = "payment_method_id")
	private PayMethod payMethod;
	
	public Expense() {
		/* required by JPA */
	}
	
	/* constructor without store */
	public Expense(Category category, String text,
			String expenseDate,Double total, PayMethod payMethod) {
		this.category = category;
		this.text = text;
		this.expenseDate = expenseDate;
		this.total = total;
		this.payMethod = payMethod;
	}
	
	/* full constructor */
	public Expense(Category category, String text,
			String store, String expenseDate,Double total, PayMethod payMethod) {
		this.category = category;
		this.text = text;
		this.store = store;
		this.expenseDate = expenseDate;
		this.total = total;
		this.payMethod = payMethod;
	}
		
}
