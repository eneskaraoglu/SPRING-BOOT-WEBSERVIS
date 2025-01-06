package tr.com.bilisim.webservis.service.stok;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.com.bilisim.webservis.entities.stok.StokMalzeme;
import tr.com.bilisim.webservis.exception.GeneralException;
import tr.com.bilisim.webservis.repostories.stok.StokMalzemeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StokMalzemeService {
    
    private final StokMalzemeRepository stokMalzemeRepository;
    
    public List<StokMalzeme> tumMalzemeleriGetir() {
        return stokMalzemeRepository.findAll();
    }
    
    public StokMalzeme malzemeGetir(Long id) {
        return stokMalzemeRepository.findById(id)
            .orElseThrow(() -> new GeneralException("Malzeme bulunamadı: " + id));
    }
    
    @Transactional
    public StokMalzeme malzemeKaydet(StokMalzeme malzeme) {
        if (malzeme.getId() == null && stokMalzemeRepository.existsByMalzemeKodu(malzeme.getMalzemeKodu())) {
            throw new GeneralException("Bu malzeme kodu zaten kullanımda: " + malzeme.getMalzemeKodu());
        }
        return stokMalzemeRepository.save(malzeme);
    }
    
    @Transactional
    public void malzemeSil(Long id) {
        if (!stokMalzemeRepository.existsById(id)) {
            throw new GeneralException("Malzeme bulunamadı: " + id);
        }
        stokMalzemeRepository.deleteById(id);
    }
    
    public List<StokMalzeme> malzemeAdinaGoreAra(String aramaMetni) {
        return stokMalzemeRepository.malzemeAdinaGoreAra(aramaMetni);
    }
    
    public List<StokMalzeme> fabrikaKodunaGoreMalzemeleriGetir(Integer fabrikaKodu) {
        return stokMalzemeRepository.findByFabrikaKodu(fabrikaKodu);
    }
}