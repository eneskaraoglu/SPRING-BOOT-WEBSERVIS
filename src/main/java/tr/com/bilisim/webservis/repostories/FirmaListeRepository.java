package tr.com.bilisim.webservis.repostories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tr.com.bilisim.webservis.entities.FirmaListeEntity;

@Repository
public interface FirmaListeRepository extends JpaRepository<FirmaListeEntity, Long> {

    @Query("SELECT s FROM FirmaListeEntity s " +
            "WHERE (:fabrikaKod IS NULL OR s.fabrikaKod = :fabrikaKod) " +
            "AND (:firmaAdi IS NULL OR UPPER(s.firmaAdi) LIKE CONCAT('%', UPPER(:firmaAdi), '%')) " +
            "AND (:firmaKodu IS NULL OR UPPER(s.firmaKodu) LIKE CONCAT('%', UPPER(:firmaKodu), '%'))")
     List<FirmaListeEntity> findWithFilter(
             @Param("fabrikaKod") Integer fabrikaKod,
             @Param("firmaAdi") String firmaAdi,
             @Param("firmaKodu") String firmaKodu
     );
}
