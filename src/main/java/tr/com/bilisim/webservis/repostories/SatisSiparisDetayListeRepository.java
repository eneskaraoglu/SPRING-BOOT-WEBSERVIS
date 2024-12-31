package tr.com.bilisim.webservis.repostories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tr.com.bilisim.webservis.entities.StokEnvanterListeEntity;

@Repository
public interface SatisSiparisDetayListeRepository extends JpaRepository<StokEnvanterListeEntity, Long> {

    @Query("SELECT s FROM SatisSiparisDetayListe s " +
            "WHERE (:fabrikaKod IS NULL OR s.fabrikaKod = :fabrikaKod) " )
     List<StokEnvanterListeEntity> findWithFilter(
             @Param("fabrikaKod") Integer fabrikaKod
     );
}
