package tr.com.bilisim.webservis.repostories.ortak;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tr.com.bilisim.webservis.entities.ortak.ErpFirma;

@Repository
public interface ErpFirmaRepository extends JpaRepository<ErpFirma, Long> {
    
    // Temel Sorgular
    Optional<ErpFirma> findByFirmaKodu(String firmaKodu);
    
    boolean existsByFirmaKodu(String firmaKodu);
    
    List<ErpFirma> findByFirmaAdiContainingIgnoreCase(String firmaAdi);
    
    // Fabrika bazlı sorgular
    List<ErpFirma> findByFabrikaKod(Integer fabrikaKod);
    
    @Query("SELECT f FROM ErpFirma f WHERE f.fabrikaKod = :fabrikaKod AND f.firmaTuru = :firmaTuru")
    List<ErpFirma> findByFabrikaKodAndFirmaTuru(
        @Param("fabrikaKod") Integer fabrikaKod, 
        @Param("firmaTuru") Integer firmaTuru
    );
    
    // Sektör bazlı sorgular
    List<ErpFirma> findByFirmaSektoru(String firmaSektoru);
    
    @Query("SELECT f FROM ErpFirma f WHERE f.firmaSektoru = :sektor OR " +
           "f.firmaSektoru2 = :sektor OR f.firmaSektoru3 = :sektor OR f.firmaSektoru4 = :sektor")
    List<ErpFirma> findByAnySector(@Param("sektor") String sektor);
    
    // Vergi no bazlı sorgular
    Optional<ErpFirma> findByVergiNo(String vergiNo);
    
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM ErpFirma f WHERE f.vergiNo = :vergiNo")
    boolean existsByVergiNo(@Param("vergiNo") String vergiNo);
    
    // Lokasyon bazlı sorgular
    List<ErpFirma> findByBulunduguIlAndBulunduguIlce(Integer il, Integer ilce);
    
    List<ErpFirma> findByUlke(String ulke);
    
    // E-Fatura/E-Arşiv ile ilgili sorgular
    @Query("SELECT f FROM ErpFirma f WHERE f.efatGonderilecek = 1")
    List<ErpFirma> findEFaturaFirmalari();
    
    @Query("SELECT f FROM ErpFirma f WHERE f.earsivGonderilecek = 1")
    List<ErpFirma> findEArsivFirmalari();
    
    // Sayfalama ve Filtreleme
    Page<ErpFirma> findByFirmaTuru(Integer firmaTuru, Pageable pageable);
    
    @Query("SELECT f FROM ErpFirma f WHERE " +
           "(:firmaAdi IS NULL OR LOWER(f.firmaAdi) LIKE LOWER(CONCAT('%', :firmaAdi, '%'))) AND " +
           "(:firmaTuru IS NULL OR f.firmaTuru = :firmaTuru) AND " +
           "(:fabrikaKod IS NULL OR f.fabrikaKod = :fabrikaKod)")
    Page<ErpFirma> findByFilters(
        @Param("firmaAdi") String firmaAdi,
        @Param("firmaTuru") Integer firmaTuru,
        @Param("fabrikaKod") Integer fabrikaKod,
        Pageable pageable
    );
    
    // İstatistiksel Sorgular
    @Query("SELECT COUNT(f) FROM ErpFirma f WHERE f.firmaTuru = :firmaTuru")
    long countByFirmaTuru(@Param("firmaTuru") Integer firmaTuru);
    
    @Query("SELECT f.firmaTuru, COUNT(f) FROM ErpFirma f GROUP BY f.firmaTuru")
    List<Object[]> getFirmaTuruDagilimi();
    
    // Özel İş Kuralları İçin Sorgular
    @Query("SELECT f FROM ErpFirma f WHERE " +
           "f.riskTutari > 0 AND f.muhasebeIliskili = 1")
    List<ErpFirma> findRiskliMuhasebeFirmalari();
    
    @Query("SELECT f FROM ErpFirma f WHERE " +
           "f.yetSaticiGost = 1 AND f.yetServisGost = 1")
    List<ErpFirma> findYetkiliSaticiVeServisFirmalar();
}