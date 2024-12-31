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
public class PageController {

	@Autowired
	private GorevService gorevService;
	
	@GetMapping("/index")
    public String index() {
        return "index";
    }

	@GetMapping("/login")
	public String loginForm( Model model) {
		model.addAttribute("loginForm", null);
		return "login";
	}
	
}
