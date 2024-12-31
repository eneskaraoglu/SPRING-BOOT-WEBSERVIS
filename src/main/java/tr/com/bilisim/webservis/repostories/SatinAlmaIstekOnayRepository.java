package tr.com.bilisim.webservis.repostories;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tr.com.bilisim.webservis.entities.SatinAlmaIstekOnayViewEntity;

@Repository
public interface SatinAlmaIstekOnayRepository extends JpaRepository<SatinAlmaIstekOnayViewEntity, Long> {
    
    @Query(value = 
            "SELECT  A.ISTEK_DT_ID, A.TEXT, A.ACIKLAMA, A.LAST_UPDATE_DATE, A.KAYIT_GIR_TAR, A.ONAY_TARIHI, "
            + " A.RED_GEREKCESI, A.ISTEK_DRM, A.ONAY_DRM, A.FABRIKA_KOD, A.KAYIT_GIR_KULLANICI, A.ONAYLAYAN, A.JSON_DATA "
                    + " FROM ERPKULLANICI.V_B2B_SATIN_ALMA_ISTEK_ONAY A "
                    + " WHERE  ( EXISTS (SELECT 1 FROM ERPSAT.V_STN_ISTEK_ONAY VO WHERE VO.ONAY_YAPACAK = :p1 AND VO.ONAYLANACAK_KULLANICI = A.KAYIT_GIR_KULLANICI)) "
                    + " AND A.FABRIKA_KOD = :p3 AND A.ISTEK_DRM = :p2 ORDER BY A.LAST_UPDATE_DATE DESC "
            , nativeQuery = true)
    Page<SatinAlmaIstekOnayViewEntity> fetchData(@Param("p1") String onayYapacak, @Param("p3") String fabrikaKod,@Param("p2") String istekDurum, Pageable pageable);


    @Transactional
    @Modifying
    @Query(value = "UPDATE T_STN_ISTEK_DT SET ISTEK_DRM = :durum, ONAYLAYAN = :onaylayan, ONAY_TARIHI = :tarih, LAST_UPDATE_DATE = :tarih WHERE ISTEK_DT_ID = :istekDtId", nativeQuery = true)
    int updateRequest(@Param("durum") String tip, 
                      @Param("onaylayan") String onaylayan, 
                      @Param("tarih") Date tarih, 
                      @Param("istekDtId") Long Id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE T_STN_ISTEK_DT SET ISTEK_DRM =:durum, RED_GEREKCESI =:redaciklama, ONAYLAYAN =:onaylayan, ONAY_TARIHI =:tarih, LAST_UPDATE_DATE =:tarih  where ISTEK_DT_ID =:istekDtId", nativeQuery = true)
    int rejectRequest(@Param("durum") String tip, 
                      @Param("onaylayan") String onaylayan, 
                      @Param("redaciklama") String redaciklama, 
                      @Param("tarih") Date tarih, 
                      @Param("istekDtId") Long Id);
    
}
