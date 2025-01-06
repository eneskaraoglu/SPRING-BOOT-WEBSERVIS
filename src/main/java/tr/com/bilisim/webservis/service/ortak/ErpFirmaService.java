package tr.com.bilisim.webservis.service.ortak;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tr.com.bilisim.webservis.entities.ortak.ErpFirma;
import tr.com.bilisim.webservis.exception.GeneralException;
import tr.com.bilisim.webservis.repostories.ortak.ErpFirmaRepository;

@Service
@RequiredArgsConstructor
public class ErpFirmaService {

    private final ErpFirmaRepository firmaRepository;

    // Temel CRUD Operasyonları
    @Transactional(readOnly = true)
    public List<ErpFirma> getAllFirma() {
        return firmaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ErpFirma getFirmaById(Long id) {
        return firmaRepository.findById(id)
                .orElseThrow(() -> new GeneralException("Firma bulunamadı ID: " + id));
    }

    @Transactional(readOnly = true)
    public ErpFirma getFirmaByKod(String firmaKodu) {
        return firmaRepository.findByFirmaKodu(firmaKodu)
                .orElseThrow(() -> new GeneralException("Firma bulunamadı Kod: " + firmaKodu));
    }

    @Transactional
    public ErpFirma createFirma(ErpFirma firma) {
        // Validasyonlar
        validateFirma(firma);
        
        // Firma kodu kontrolü
        if (firmaRepository.existsByFirmaKodu(firma.getFirmaKodu())) {
            throw new GeneralException("Bu firma kodu zaten kullanımda: " + firma.getFirmaKodu());
        }

        // Vergi no kontrolü
        if (firma.getVergiNo() != null && firmaRepository.existsByVergiNo(firma.getVergiNo())) {
            throw new GeneralException("Bu vergi numarası zaten kullanımda: " + firma.getVergiNo());
        }

        return firmaRepository.save(firma);
    }

    @Transactional
    public ErpFirma updateFirma(Long id, ErpFirma firma) {
        ErpFirma existingFirma = getFirmaById(id);
        
        // Validasyonlar
        validateFirma(firma);

        // Vergi no değişmişse kontrol et
        if (firma.getVergiNo() != null && !firma.getVergiNo().equals(existingFirma.getVergiNo()) 
            && firmaRepository.existsByVergiNo(firma.getVergiNo())) {
            throw new GeneralException("Bu vergi numarası zaten kullanımda: " + firma.getVergiNo());
        }

        // Temel bilgileri güncelle
        updateFirmaFields(existingFirma, firma);
        
        return firmaRepository.save(existingFirma);
    }

    @Transactional
    public void deleteFirma(Long id) {
        if (!firmaRepository.existsById(id)) {
            throw new GeneralException("Firma bulunamadı ID: " + id);
        }
        firmaRepository.deleteById(id);
    }

    // Özel İş Mantığı Metodları
    @Transactional(readOnly = true)
    public Page<ErpFirma> searchFirma(String firmaAdi, Integer firmaTuru, Integer fabrikaKod, Pageable pageable) {
        return firmaRepository.findByFilters(firmaAdi, firmaTuru, fabrikaKod, pageable);
    }

    @Transactional(readOnly = true)
    public List<ErpFirma> getFirmalarBySektor(String sektor) {
        return firmaRepository.findByAnySector(sektor);
    }

    @Transactional(readOnly = true)
    public List<ErpFirma> getEFaturaFirmalari() {
        return firmaRepository.findEFaturaFirmalari();
    }

    @Transactional(readOnly = true)
    public List<ErpFirma> getFirmalarByLocation(Integer il, Integer ilce) {
        return firmaRepository.findByBulunduguIlAndBulunduguIlce(il, ilce);
    }

    @Transactional(readOnly = true)
    public Map<Integer, Long> getFirmaTuruDagilimi() {
        return firmaRepository.getFirmaTuruDagilimi().stream()
                .collect(Collectors.toMap(
                        arr -> (Integer) arr[0],
                        arr -> (Long) arr[1]
                ));
    }

    @Transactional(readOnly = true)
    public List<ErpFirma> getRiskliMuhasebeFirmalari() {
        return firmaRepository.findRiskliMuhasebeFirmalari();
    }

    // Yardımcı Metodlar
    private void validateFirma(ErpFirma firma) {
        if (firma.getFirmaKodu() == null || firma.getFirmaKodu().trim().isEmpty()) {
            throw new GeneralException("Firma kodu boş olamaz");
        }
        
        if (firma.getFirmaAdi() == null || firma.getFirmaAdi().trim().isEmpty()) {
            throw new GeneralException("Firma adı boş olamaz");
        }
        
        if (firma.getFabrikaKod() == null) {
            throw new GeneralException("Fabrika kodu boş olamaz");
        }

        // Vergi numarası formatı kontrolü
        if (firma.getVergiNo() != null && !firma.getVergiNo().matches("\\d{10}")) {
            throw new GeneralException("Geçersiz vergi numarası formatı");
        }

        // TC Kimlik kontrolü (gerçek kişi ise)
        if (firma.getGercekOrTuzel() != null && firma.getGercekOrTuzel() == 1 
            && firma.getTcKimlikNo() != null && !firma.getTcKimlikNo().matches("\\d{11}")) {
            throw new GeneralException("Geçersiz TC Kimlik numarası formatı");
        }
    }

    private void updateFirmaFields(ErpFirma existingFirma, ErpFirma newFirma) {
        existingFirma.setFirmaAdi(newFirma.getFirmaAdi());
        existingFirma.setFirmaTuru(newFirma.getFirmaTuru());
        existingFirma.setFirmaSektoru(newFirma.getFirmaSektoru());
        existingFirma.setSorumlu(newFirma.getSorumlu());
        existingFirma.setSorumluUnvan(newFirma.getSorumluUnvan());
        existingFirma.setVergiDairesi(newFirma.getVergiDairesi());
        existingFirma.setVergiNo(newFirma.getVergiNo());
        existingFirma.setTicSicilNo(newFirma.getTicSicilNo());
        existingFirma.setKaliteSorumlu(newFirma.getKaliteSorumlu());
        existingFirma.setKaliteSorumluUnvan(newFirma.getKaliteSorumluUnvan());
        existingFirma.setFirmaDetayTuru(newFirma.getFirmaDetayTuru());
        existingFirma.setUreticiKod(newFirma.getUreticiKod());
        existingFirma.setGercekOrTuzel(newFirma.getGercekOrTuzel());
        existingFirma.setAlisVade(newFirma.getAlisVade());
        existingFirma.setSatisVade(newFirma.getSatisVade());
        existingFirma.setDovizId(newFirma.getDovizId());
        existingFirma.setFirmaEpostaKodu(newFirma.getFirmaEpostaKodu());
        existingFirma.setEfatGonderilecek(newFirma.getEfatGonderilecek());
        existingFirma.setEarsivGonderilecek(newFirma.getEarsivGonderilecek());
    }
}