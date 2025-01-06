package tr.com.bilisim.webservis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.bilisim.webservis.entities.KaliteRed;
import tr.com.bilisim.webservis.repostories.KaliteRedRepository;
import tr.com.bilisim.webservis.exception.GeneralException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KaliteRedService {

    private final KaliteRedRepository kaliteRedRepository;

    @Transactional
    public KaliteRed kaydet(KaliteRed kaliteRed) {
        // Zorunlu alanların kontrolü
        if (kaliteRed.getMalzemeId() == null || kaliteRed.getFirmaId() == null) {
            throw new GeneralException("Malzeme ID ve Firma ID zorunlu alanlardır.");
        }
        return kaliteRedRepository.save(kaliteRed);
    }

    @Transactional
    public KaliteRed guncelle(Long id, KaliteRed kaliteRed) {
        KaliteRed mevcutKayit = kaliteRedRepository.findById(id)
            .orElseThrow(() -> new GeneralException("Kalite red kaydı bulunamadı. ID: " + id));
        
        // Değişmemesi gereken alanların korunması
        kaliteRed.setRedId(id);
        kaliteRed.setCreateDate(mevcutKayit.getCreateDate());
        
        return kaliteRedRepository.save(kaliteRed);
    }

    public KaliteRed getirById(Long id) {
        return kaliteRedRepository.findById(id)
            .orElseThrow(() -> new GeneralException("Kalite red kaydı bulunamadı. ID: " + id));
    }

    public List<KaliteRed> getirTumListe() {
        return kaliteRedRepository.findAll();
    }

    @Transactional
    public void sil(Long id) {
        if (!kaliteRedRepository.existsById(id)) {
            throw new GeneralException("Silinecek kayıt bulunamadı. ID: " + id);
        }
        kaliteRedRepository.deleteById(id);
    }

    public List<KaliteRed> getirTarihAralik(LocalDate baslangic, LocalDate bitis) {
        return kaliteRedRepository.findByTarihBetween(baslangic, bitis);
    }

    public List<KaliteRed> getirFirmaRedKayitlari(Long firmaId) {
        return kaliteRedRepository.findByFirmaId(firmaId);
    }

    public Optional<KaliteRed> getirRaporNo(Long raporNo) {
        return kaliteRedRepository.findByRedRaporNo(raporNo);
    }

    public List<KaliteRed> getirIsEmriRedKayitlari(Long isEmriNo) {
        return kaliteRedRepository.findByIsEmriNo(isEmriNo);
    }

    public Map<String, Long> getirRedTipIstatistik(LocalDate baslangic, LocalDate bitis) {
        List<Object[]> istatistikler = kaliteRedRepository.getRedTipIstatistikleri(baslangic, bitis);
        return istatistikler.stream()
            .collect(Collectors.groupingBy(
                obj -> (String) obj[1],
                Collectors.summingLong(obj -> (Long) obj[0])
            ));
    }

    public List<KaliteRed> getirYuksekRedOraniKayitlari(Long firmaId, double yuzde) {
        return kaliteRedRepository.findHighRejectionRatesByFirma(yuzde, firmaId);
    }

    @Transactional
    public void topluGuncelle(List<KaliteRed> kaliteRedList) {
        for (KaliteRed kaliteRed : kaliteRedList) {
            if (kaliteRed.getRedId() == null) {
                throw new GeneralException("Güncellenecek kayıtların ID'leri boş olamaz.");
            }
            // Her bir kaydın varlığını kontrol et
            kaliteRedRepository.findById(kaliteRed.getRedId())
                .orElseThrow(() -> new GeneralException("Güncellenecek kayıt bulunamadı. ID: " + kaliteRed.getRedId()));
        }
        kaliteRedRepository.saveAll(kaliteRedList);
    }

    @Transactional
    public void durumGuncelle(Long id, String yeniRedTip) {
        KaliteRed kaliteRed = getirById(id);
        kaliteRed.setRedTip(yeniRedTip);
        kaliteRedRepository.save(kaliteRed);
    }
}