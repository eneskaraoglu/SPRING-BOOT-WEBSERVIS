package tr.com.bilisim.webservis.pagecontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tr.com.bilisim.webservis.entities.ErpKullanici;
import tr.com.bilisim.webservis.service.GorevService;

	
@Controller
public class PageGorevController {

	@Autowired
	private GorevService gorevService;
	

	@GetMapping("/gorev/gorev")
	public String gorevForm(@RequestParam(name = "gorevId", required = false) String gorevId, Model model , 
			@AuthenticationPrincipal ErpKullanici user) {
		if (user!=null) {
			System.out.println("FABRIKA:"+user.getFabrika());
		}
		
		model.addAttribute("gorevId", gorevId);
		model.addAttribute("loginForm", null);
		return "gorev";
	}
	
	@GetMapping("/ws/gorev")
	public String wsGorevForm(@RequestParam(name = "gorevId", required = false) String gorevId, Model model , 
			@AuthenticationPrincipal ErpKullanici user) {
		if (user!=null) {
			System.out.println("FABRIKA:"+user.getFabrika());
		}
		
		model.addAttribute("gorevId", gorevId);
		model.addAttribute("loginForm", null);
		return "gorev";
	}

	@PostMapping("/ws/login")
	public String login(@RequestParam String username, @RequestParam String password,
			@RequestParam("gorevId") String gorevId, Model model, Authentication authentication) {
		if (authentication != null && authentication.isAuthenticated()) {
			return "redirect:/gorev/gorev?gorevId=" + gorevId;
		} else {
			model.addAttribute("error", "Geçersiz kimlik bilgileri.");
			return "login";
		}

	}

	// http://localhost:8098/ws/gorev?gorevId=1113
	@PostMapping("/gorev/gorev")
	public String gorevCalistir(@RequestParam("gorevId") String gorevId, Model model, Authentication authentication) {

		try {
			gorevService.getGorev(gorevId);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "sonuc";
		}

		model.addAttribute("gorev", "Çalıştırılan gorevId : " + gorevId);
		return "sonuc";
	}
}

