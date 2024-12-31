package tr.com.bilisim.webservis.pagecontroller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tr.com.bilisim.webservis.entities.ErpKullanici;
import tr.com.bilisim.webservis.entities.SatinAlmaIstekOnayViewEntity;
import tr.com.bilisim.webservis.repostories.SatinAlmaIstekOnayRepository;
import tr.com.bilisim.webservis.service.SatinAlmaIstekOnayService;

@Controller
@RequestMapping("/page/satinalma/istek")
public class PageSatinAlmaIstekOnayController {

    @Autowired
    private final SatinAlmaIstekOnayService satinAlmaIstekOnayService;

    @Autowired
    public PageSatinAlmaIstekOnayController(SatinAlmaIstekOnayService satinAlmaIstekOnayService) {
        this.satinAlmaIstekOnayService = satinAlmaIstekOnayService;
    }

    @GetMapping()
    public String getOnayTalepleri(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(required = false) String istekDurum,
                                    Model model,
                                    @AuthenticationPrincipal ErpKullanici user) {
        
        if (istekDurum == null || istekDurum.isEmpty()) {
            istekDurum = "01"; 
        }
        
        Pageable pageable = PageRequest.of(page, size);
        Page<SatinAlmaIstekOnayViewEntity> onayTalepleri = satinAlmaIstekOnayService.getPagedData(istekDurum,  pageable);
        
        model.addAttribute("onayTalepleri", onayTalepleri.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("istekDurum", istekDurum);
        model.addAttribute("totalPages", onayTalepleri.getTotalPages());
        model.addAttribute("totalItems", onayTalepleri.getTotalElements());

        return "satinalma-istek-onay";
    }
 
    @PostMapping("/onayla/{id}")
    public String approveRequest(@PathVariable Long id, @AuthenticationPrincipal ErpKullanici user) {
    	satinAlmaIstekOnayService.approveRequest(id);
        return "redirect:/page/satinalma/istek";
    }

    @PostMapping("/reddet/{id}")
    public String rejectRequest(@PathVariable Long id, @RequestParam String redGerekcesi, 
    		@AuthenticationPrincipal ErpKullanici user) {
        satinAlmaIstekOnayService.rejectRequest(id, redGerekcesi);
        return "redirect:/page/satinalma/istek";
    }

}
