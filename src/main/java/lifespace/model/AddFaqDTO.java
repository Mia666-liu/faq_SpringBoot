package lifespace.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class AddFaqDTO {
	@NotBlank(message = "抓不到管理員")
	private String adminId;

	@NotBlank(message = "標題請勿空白")
	private String faqAsk;

	@NotBlank(message = "內容請勿空白")
	private String faqAnswer;

}
