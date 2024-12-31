package tr.com.bilisim.webservis.pagecontroller;

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
import tr.com.bilisim.webservis.entities.SatinAlmaSiparisOnayViewEntity;
import tr.com.bilisim.webservis.service.SatinAlmaSiparisOnayService;

@Controller
@RequestMapping("/page/satinalma/siparis")
public class PageSatinAlmaSiparisOnayController {

    @Autowired
    private final SatinAlmaSiparisOnayService satinAlmaSiparisOnayService;

    @Autowired
    public PageSatinAlmaSiparisOnayController(SatinAlmaSiparisOnayService satinAlmaSiparisOnayService) {
        this.satinAlmaSiparisOnayService = satinAlmaSiparisOnayService;
    }

    @GetMapping()
    public String getOnayTalepleri(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(required = false) String satSipDurum,
                                    Model model,
                                    @AuthenticationPrincipal ErpKullanici user) {
        
        if (satSipDurum == null || satSipDurum.isEmpty()) {
        	satSipDurum = "B"; 
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<SatinAlmaSiparisOnayViewEntity> onayTalepleri = satinAlmaSiparisOnayService.getPagedData(satSipDurum, pageable);
        
        model.addAttribute("onayTalepleri", onayTalepleri.getContent());
        model.addAttribute("satSipDurum", satSipDurum);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", onayTalepleri.getTotalPages());
        model.addAttribute("totalItems", onayTalepleri.getTotalElements());

        return "satinalma-siparis-onay";
    }
 
    @PostMapping("/onayla/{id}")
    public String approveRequest(@PathVariable Long id, @RequestParam String sipAciklama) {
    	String satSipDurum = "O";
    	satinAlmaSiparisOnayService.approveRequest(id, sipAciklama, satSipDurum);
        return "redirect:/page/satinalma/siparis";
    }
    
    @PostMapping("/reddet/{id}")
    public String rejectRequest(@PathVariable Long id, @RequestParam String sipAciklama) {
    	String satSipDurum = "I";
    	satinAlmaSiparisOnayService.approveRequest(id, sipAciklama, satSipDurum);
        return "redirect:/page/satinalma/siparis";
    }
    
}
