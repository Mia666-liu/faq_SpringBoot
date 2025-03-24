package lifespace.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@Entity	//要加上@Entity才能成為JPA的一個Entity類別
@Table(name = "faq")
public class FaqVO {
	
	@Id
	@Column(name = "faq_id")
	private String faqId;
	
	@Column(name = "admin_id")
	private String adminId;
	
	@Column(name = "faq_ask", nullable = false)
	private String faqAsk;
	
	@Column(name = "faq_answer", nullable = false)
	private String faqAnswer;
	
	@Column(name = "faq_status", nullable = false)
	private Integer faqStatus;
	
	@Column(name = "created_time")
	private Timestamp createTime;
}
