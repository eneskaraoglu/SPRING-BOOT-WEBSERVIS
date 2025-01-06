package tr.com.bilisim.webservis.repostories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tr.com.bilisim.webservis.entities.KaliteRed;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface KaliteRedRepository extends JpaRepository<KaliteRed, Long> {
    
    // Rapor numarasına göre arama
    Optional<KaliteRed> findByRedRaporNo(Long redRaporNo);
    
    // Tarih aralığına göre listeleme
    List<KaliteRed> findByTarihBetween(LocalDate baslangic, LocalDate bitis);
    
    // Firma ID'ye göre listeleme
    List<KaliteRed> findByFirmaId(Long firmaId);
    
    // İş emri numarasına göre listeleme
    List<KaliteRed> findByIsEmriNo(Long isEmriNo);
    
    // Malzeme ID'ye göre listeleme
    List<KaliteRed> findByMalzemeId(Long malzemeId);
    
    // Belirli bir tarihten sonraki red kayıtları
    List<KaliteRed> findByTarihAfter(LocalDate tarih);
    
    // Firma ve tarih aralığına göre arama
    List<KaliteRed> findByFirmaIdAndTarihBetween(Long firmaId, LocalDate baslangic, LocalDate bitis);
    
    // JPQL ile kompleks sorgu örneği
    @Query("SELECT k FROM KaliteRed k WHERE k.raslanmaYuzde > :yuzde AND k.firmaId = :firmaId")
    List<KaliteRed> findHighRejectionRatesByFirma(@Param("yuzde") double yuzde, @Param("firmaId") Long firmaId);
    
    // Native SQL sorgu örneği
    @Query(value = "SELECT * FROM ERPKALITE.T_KAL_RED WHERE RED_TIP = :redTip AND FABRIKA_KOD = :fabrikaKod", 
           nativeQuery = true)
    List<KaliteRed> findByRedTipAndFabrika(@Param("redTip") String redTip, @Param("fabrikaKod") Integer fabrikaKod);
    
    // Özet istatistik sorgusu
    @Query("SELECT COUNT(k), k.redTip, k.firmaId FROM KaliteRed k " +
           "WHERE k.tarih BETWEEN :baslangic AND :bitis " +
           "GROUP BY k.redTip, k.firmaId")
    List<Object[]> getRedTipIstatistikleri(@Param("baslangic") LocalDate baslangic, 
                                          @Param("bitis") LocalDate bitis);
}