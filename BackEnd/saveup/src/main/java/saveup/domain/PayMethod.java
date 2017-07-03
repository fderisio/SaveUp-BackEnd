package saveup.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "payment_method")
public class PayMethod {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(nullable = false, length = 20)
	private String name;
	
	@Column(nullable = true)
	private String bank;
	
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
}
