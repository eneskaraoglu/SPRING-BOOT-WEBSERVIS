package tr.com.bilisim.webservis.repostories.stok;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tr.com.bilisim.webservis.entities.stok.StokMalzeme;

import java.util.List;
import java.util.Optional;

@Repository
public interface StokMalzemeRepository extends JpaRepository<StokMalzeme, Long> {
    
    Optional<StokMalzeme> findByMalzemeKodu(String malzemeKodu);
    
    List<StokMalzeme> findByFabrikaKodu(Integer fabrikaKodu);
    
    @Query("SELECT s FROM StokMalzeme s WHERE LOWER(s.malzemeAdi) LIKE LOWER(CONCAT('%', :aramaMetni, '%'))")
    List<StokMalzeme> malzemeAdinaGoreAra(String aramaMetni);
    
    boolean existsByMalzemeKodu(String malzemeKodu);
}