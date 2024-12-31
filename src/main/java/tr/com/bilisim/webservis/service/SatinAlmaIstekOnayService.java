package tr.com.bilisim.webservis.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tr.com.bilisim.webservis.entities.ErpKullanici;
import tr.com.bilisim.webservis.entities.SatinAlmaIstekOnayViewEntity;
import tr.com.bilisim.webservis.repostories.SatinAlmaIstekOnayRepository;
import tr.com.bilisim.webservis.util.SecurityUtil;

@Service
public class SatinAlmaIstekOnayService {

    private final SatinAlmaIstekOnayRepository satinAlmaIstekOnayRepository;

    @Autowired
    public SatinAlmaIstekOnayService(SatinAlmaIstekOnayRepository satinAlmaIstekOnayRepository) {
        this.satinAlmaIstekOnayRepository = satinAlmaIstekOnayRepository;
    }
    
    public Page<SatinAlmaIstekOnayViewEntity> getPagedData(String istekDurum,Pageable pageable) {
    	ErpKullanici user = SecurityUtil.getAuthenticatedUser();
        return satinAlmaIstekOnayRepository.fetchData(user.getKullanici(), user.getFabrika(), istekDurum, pageable);
    }

    public void approveRequest(Long id) {
        String tip = "02"; 
        Date tarih = new Date(); 
        ErpKullanici user = SecurityUtil.getAuthenticatedUser();
        satinAlmaIstekOnayRepository.updateRequest(tip, user.getKullanici(), tarih, id);
    }

    public void rejectRequest(Long id, String redaciklama) {
        String tip = "07"; 
        Date tarih = new Date(); 
        ErpKullanici user = SecurityUtil.getAuthenticatedUser();
        satinAlmaIstekOnayRepository.rejectRequest(tip, user.getKullanici(),redaciklama, tarih, id);
    }
    
}
