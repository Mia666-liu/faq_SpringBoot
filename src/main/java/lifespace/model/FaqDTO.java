package lifespace.model;

import lombok.*;

import java.sql.Timestamp;


@Data
public class FaqDTO {

	private String faqId;

	private String adminId;

	private String faqAsk;

	private String faqAnswer;

	private Integer faqStatus;

	private Timestamp createTime;
}
