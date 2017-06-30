package saveup.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "income")
public class UserIncome {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(optional = false)
	private User user;
	
	@Column(nullable = false, length = 6)
	private Integer income;
	
	@Column(name = "started_at", nullable = false)
	private String startedAt;
	
	@Column(nullable = false)
	private boolean monthly = true;
	
	public UserIncome() {
		/* required by JPA */
	}
	
	public UserIncome(User user, Integer income, String startedAt, boolean monthly) {
		this.user = user;
		this.income = income;
		this.startedAt = startedAt;
		this.monthly = monthly;
	}
}
