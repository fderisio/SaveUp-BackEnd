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

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "income")
public class Income implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonView(JsonViews.Public.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id")
	private User user;
	
	@JsonView(JsonViews.Public.class)
	@Column(nullable = false, length = 6)
	private Integer amount;
	
	@JsonView(JsonViews.Public.class)
	@Column(name = "started_at", nullable = false)
	private String startedAt;
	
	@JsonView(JsonViews.Public.class)
	@Column(nullable = false)
	private boolean monthly = true;
	
	@JsonView(JsonViews.Public.class)
	@Column(name = "end_at", nullable = true)
	private String endAt;
	
	public Income() {
		/* required by JPA */
	}
	
	// for monthly incomes
	public Income(User user, Integer amount, String startedAt, boolean monthly) {
		this.user = user;
		this.amount = amount;
		this.startedAt = startedAt;
		this.monthly = monthly;
	}
	
	// for non monthly incomes
	public Income(User user, Integer amount, String startedAt, boolean monthly, String endAt) {
		this.user = user;
		this.amount = amount;
		this.startedAt = startedAt;
		this.monthly = monthly;
		this.endAt = endAt;
	}
}
