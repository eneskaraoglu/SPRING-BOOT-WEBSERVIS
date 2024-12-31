package tr.com.bilisim.webservis.repostories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tr.com.bilisim.webservis.entities.StokEnvanterListeEntity;

@Repository
public interface StokEnvanterListeRepository extends JpaRepository<StokEnvanterListeEntity, Long> {

    @Query("SELECT s FROM StokEnvanterListeEntity s " +
            "WHERE (:fabrikaKod IS NULL OR s.fabrikaKod = :fabrikaKod) " +
            "AND (:envanterTuru IS NULL OR s.envanterTuru = :envanterTuru) " +
            "AND (:malzemeSeviyesi IS NULL OR s.malzemeSeviyesi = :malzemeSeviyesi) " +
            "AND (:malhizAdi IS NULL OR UPPER(s.malhizAdi) LIKE CONCAT('%', UPPER(:malhizAdi), '%')) " +
            "AND (:malhizKodu IS NULL OR UPPER(s.malhizKodu) LIKE CONCAT('%', UPPER(:malhizKodu), '%'))")
     List<StokEnvanterListeEntity> findWithFilter(
             @Param("fabrikaKod") Integer fabrikaKod,
             @Param("envanterTuru") String envanterTuru,
             @Param("malzemeSeviyesi") String malzemeSeviyesi,
             @Param("malhizAdi") String malhizAdi,
             @Param("malhizKodu") String malhizKodu
     );
}
