package lifespace.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class FaqService {

	@Autowired
	FaqRepository repository;

	// 自動產生下一個 FAQ ID（像 FAQ01、FAQ02）
	private String generateNextFaqId() {
		String lastId = repository.findTopByOrderByFaqIdDesc(); // 例如 FAQ09

		int nextNumber = 1; // 如果沒有資料，從 FAQ01 開始

		if (lastId != null && lastId.matches("FAQ\\d+")) {
			nextNumber = Integer.parseInt(lastId.substring(3)) + 1;
		}

		return String.format("FAQ%02d", nextNumber); // 轉成 FAQ01、FAQ02 ...
	}

	public void insertFaq(String faqAsk, String faqAnswer, String adminId) {
		FaqVO faq = new FaqVO();
		faq.setFaqId(generateNextFaqId());
		faq.setFaqAsk(faqAsk);
		faq.setFaqAnswer(faqAnswer);
		faq.setAdminId(adminId);
		faq.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
		faq.setFaqStatus(1);
		repository.save(faq);

	}

//	新增的驗證訊息
	public ResponseEntity<?> addFaqError(AddFaqDTO dto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<>();

			bindingResult.getFieldErrors().forEach(error -> {
				errors.put(error.getField(), error.getDefaultMessage());
			});

			return ResponseEntity.badRequest().body(errors);
		} else {
			insertFaq(dto.getFaqAsk(),dto.getFaqAnswer(), dto.getAdminId());
			
			return ResponseEntity.ok().build();
		}

	}

	public void updateFaq(String faqId, String faqAsk, String faqAnswer) {
		Optional<FaqVO> voTemp = repository.findById(faqId);
		if (voTemp.isPresent()) {
			FaqVO vo = voTemp.get();
			vo.setFaqAsk(faqAsk);
			vo.setFaqAnswer(faqAnswer);
			repository.save(vo);
		}
	}

	public void deleteFaq(String faqId) {
		Optional<FaqVO> voTemp = repository.findById(faqId);
		if (voTemp.isPresent()) {
			FaqVO vo = voTemp.get();
			vo.setFaqStatus(0);
			repository.save(vo);
		}
	}

	public FaqVO getOneFaq(String faqId) {
		Optional<FaqVO> optional = repository.findById(faqId);
//		return optional.get();
		return optional.orElse(null); // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
	}

	public List<FaqDTO> getAll() {
		List<FaqDTO> list = new ArrayList<>();
		List<FaqVO> list2 = repository.findAll();
		for (FaqVO vo : list2) {
			list.add(voToFaqDTO(vo));
		}
		return list;
	}

	private FaqVO dtoToFaqVo(FaqDTO dto) {
		FaqVO faqVo = new FaqVO();
		faqVo.setFaqId(dto.getFaqId());
		faqVo.setAdminId(dto.getAdminId());
		faqVo.setFaqAsk(dto.getFaqAsk());
		faqVo.setFaqAnswer(dto.getFaqAnswer());
		faqVo.setFaqStatus(dto.getFaqStatus());
		faqVo.setCreateTime(dto.getCreateTime());
		return faqVo;
	}

	private FaqDTO voToFaqDTO(FaqVO vo) {
		FaqDTO faqDto = new FaqDTO();
		faqDto.setFaqId(vo.getFaqId());
		faqDto.setAdminId(vo.getAdminId());
		faqDto.setFaqAsk(vo.getFaqAsk());
		faqDto.setFaqAnswer(vo.getFaqAnswer());
		faqDto.setFaqStatus(vo.getFaqStatus());
		faqDto.setCreateTime(vo.getCreateTime());
		return faqDto;
	}

}
