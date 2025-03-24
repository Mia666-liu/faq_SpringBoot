package lifespace.controller;

import lifespace.model.AddFaqDTO;
import lifespace.model.FaqDTO;
import lifespace.model.FaqService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/faq")
public class FaqController {
	
	@Autowired
	FaqService faqSvc;
	
	@GetMapping("query")
	public List<FaqDTO> getAll() {
		return faqSvc.getAll();	
		
	}
	
	@PostMapping("deprecated")
	public void deleteFaq(@RequestParam String faqId) {
		faqSvc.deleteFaq(faqId);
	}
	
	@PostMapping("insert")
	public ResponseEntity<String> insertFaq(@RequestParam String faqAsk,
	                                        @RequestParam String faqAnswer,
	                                        @RequestParam String adminId) {
		
		faqSvc.insertFaq(faqAsk, faqAnswer, adminId);
	    return ResponseEntity.ok("新增成功");
	}

	@PostMapping("insertvalid")
	public ResponseEntity<?> addFaqError(@Valid @RequestBody AddFaqDTO dto, BindingResult bindingResult) {
		
	    return faqSvc.addFaqError(dto, bindingResult);
	}

	
	@PostMapping("update")
	public ResponseEntity<String> updateFaq(@RequestParam String faqId,
	                                        @RequestParam String faqAsk,
	                                        @RequestParam String faqAnswer) {
	    faqSvc.updateFaq(faqId, faqAsk, faqAnswer);
	    return ResponseEntity.ok("修改成功");
	}

	
	
}