package tr.com.bilisim.webservis.repostories;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tr.com.bilisim.webservis.entities.SatinAlmaSiparisOnayViewEntity;

@Repository
public interface SatinAlmaSiparisOnayRepository extends JpaRepository<SatinAlmaSiparisOnayViewEntity, Long> {
    
    @Query(value = "SELECT     A.SAT_SIPARIS_DT_ID, A.TEXT, A.ACIKLAMA, A.TARIH, A.SAT_SIP_DURUM,  A.LAST_UPDATE_DATE,  "
            + " A.SIP_ONAYCI_ID, A.SAT_SIPARIS_ID, A.KULLANICI_ROL_ID, A.FABRIKA_KOD, A.ISLEM_TARIHI, A.JSON_DATA "
                    + " FROM ERPKULLANICI.V_B2B_SATIN_ALMA_SIPARIS_ONAY A "
                    + " WHERE a.KULLANICI_ROL_ID =:p1 AND A.SAT_SIP_DURUM =:p2  AND A.FABRIKA_KOD =:p3  "
            , nativeQuery = true)
    Page<SatinAlmaSiparisOnayViewEntity> fetchData(@Param("p1") String sipOnayciId, @Param("p2") String satSipDurum, @Param("p3") String fabrikaKod, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "UPDATE T_STN_TEKLIF_SATICI_SIPARIS_DT SET SAT_SIP_DURUM =:satSipDurum, "
			+ " ACIKLAMA =  ACIKLAMA || :aciklama, SIP_ONAYCI_ID =:sipOnayciId, "
			+ "LAST_UPDATE_DATE =:tarih, ISLEM_TARIHI =:tarih  "
			+ "where SAT_SIPARIS_DT_ID =:satSiparisDtId", nativeQuery = true)
    int updateRequest(@Param("satSipDurum") String satSipDurum, 
                      @Param("aciklama") String aciklama, 
                      @Param("sipOnayciId") Long sipOnayciId, 
                      @Param("tarih") Date tarih, 
                      @Param("satSiparisDtId") Long satSiparisDtId);
    
    Optional<SatinAlmaSiparisOnayViewEntity> findBySatSiparisDtId(Long satSiparisDtId);
    
}
