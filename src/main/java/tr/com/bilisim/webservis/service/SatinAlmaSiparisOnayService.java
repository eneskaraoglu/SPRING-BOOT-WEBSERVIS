package tr.com.bilisim.webservis.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import tr.com.bilisim.webservis.entities.ErpKullanici;
import tr.com.bilisim.webservis.entities.SatinAlmaSaticiSiparisDtOnayEntity;
import tr.com.bilisim.webservis.entities.SatinAlmaSiparisOnayViewEntity;
import tr.com.bilisim.webservis.repostories.SatinAlmaSaticiSiparisDtOnayRepository;
import tr.com.bilisim.webservis.repostories.SatinAlmaSiparisOnayRepository;
import tr.com.bilisim.webservis.util.SecurityUtil;

@Service
public class SatinAlmaSiparisOnayService {

    private final SatinAlmaSiparisOnayRepository satinAlmaSiparisOnayRepository;
    private final SatinAlmaSaticiSiparisDtOnayRepository satinAlmaSaticiSiparisDtOnayRepository;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public SatinAlmaSiparisOnayService(SatinAlmaSiparisOnayRepository satinAlmaSiparisOnayRepository, SatinAlmaSaticiSiparisDtOnayRepository satinAlmaSaticiSiparisDtOnayRepository) {
        this.satinAlmaSiparisOnayRepository = satinAlmaSiparisOnayRepository;
        this.satinAlmaSaticiSiparisDtOnayRepository = satinAlmaSaticiSiparisDtOnayRepository;
    }
    
    public Page<SatinAlmaSiparisOnayViewEntity> getPagedData(String satSipDurum,Pageable pageable) {
    	ErpKullanici user = SecurityUtil.getAuthenticatedUser();

        return satinAlmaSiparisOnayRepository.fetchData(user.getKullanici(),satSipDurum, user.getFabrika(),  pageable);
    }
    
    public void approveRequest(Long satSiparisDtId, String aciklama, String satSipDurum) {

    	Date tarih = new Date(); 
    	
        List<Long> sipOnayData = getSatSipOnayDt(satSiparisDtId);

        Long satSipId = sipOnayData.get(0);  
        Long sipOnayciId = sipOnayData.size() > 1 ? sipOnayData.get(1) : null;
        
        Optional<SatinAlmaSiparisOnayViewEntity> siparisOnay = satinAlmaSiparisOnayRepository.findBySatSiparisDtId(satSiparisDtId);
        SatinAlmaSiparisOnayViewEntity entity = siparisOnay.get();
        
        System.out.println("satSipDtId:"+satSipId);
        System.out.println("sipOnayciId:"+sipOnayciId);

        satinAlmaSiparisOnayRepository.updateRequest(satSipDurum, aciklama, sipOnayciId, tarih, satSiparisDtId);
    
        ErpKullanici user = SecurityUtil.getAuthenticatedUser();
        
 
        SatinAlmaSaticiSiparisDtOnayEntity onay = new SatinAlmaSaticiSiparisDtOnayEntity();
        onay.setFabrikaKod(Long.valueOf(user.getFabrika()));
		onay.setSipOnayciId(sipOnayciId);
		onay.setOnayDurum(satSipDurum);
		onay.setKullanici(user.getKullanici());
		onay.setIslemTarihi(new Date());
		onay.setAciklama(aciklama);
		onay.setSatSiparisId(entity.getSatSiparisId());
		onay.setLastUpdateDate(new Date());
		onay.setCreateDate(new Date());

		satinAlmaSaticiSiparisDtOnayRepository.save(onay);
    }
    
    public List<Long> getSatSipOnayDt(Long satSiparisDtId) {

        String sqlSatSipOnay = "SELECT SAT_SIPARIS_ID, SIP_ONAYCI_ID FROM T_STN_TEKLIF_SATICI_SIPARIS_DT WHERE SAT_SIPARIS_DT_ID =:p1";
        Query satSipOnayQuery = em.createNativeQuery(sqlSatSipOnay);
        satSipOnayQuery.setParameter("p1", satSiparisDtId);

        Object[] result = (Object[]) satSipOnayQuery.getSingleResult();
        
        List<Long> sonucList = new ArrayList<>();
        
        if (result != null) {
            sonucList.add(Long.valueOf(result[0].toString()));
            if (result[1] != null) {
                sonucList.add(Long.valueOf(result[1].toString()));
            } else {
                sonucList.add(null);
            }
        }
        
        return sonucList;
    }
    
}
