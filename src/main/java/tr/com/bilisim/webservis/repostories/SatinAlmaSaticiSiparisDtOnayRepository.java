package tr.com.bilisim.webservis.repostories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tr.com.bilisim.webservis.entities.SatinAlmaSaticiSiparisDtOnayEntity;

@Repository
public interface SatinAlmaSaticiSiparisDtOnayRepository extends JpaRepository<SatinAlmaSaticiSiparisDtOnayEntity, Long> {
}
