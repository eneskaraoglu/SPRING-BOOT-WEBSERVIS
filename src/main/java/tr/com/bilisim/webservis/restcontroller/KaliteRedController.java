package tr.com.bilisim.webservis.restcontroller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.bilisim.webservis.entities.KaliteRed;
import tr.com.bilisim.webservis.service.KaliteRedService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/kalite-red")
@RequiredArgsConstructor
@Tag(name = "Kalite Red İşlemleri", description = "Kalite red kayıtları için API endpoint'leri")
public class KaliteRedController {

    private final KaliteRedService kaliteRedService;

    @PostMapping
    @Operation(summary = "Yeni kalite red kaydı oluştur")
    public ResponseEntity<KaliteRed> kaydet(@RequestBody KaliteRed kaliteRed) {
        return ResponseEntity.ok(kaliteRedService.kaydet(kaliteRed));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Kalite red kaydını güncelle")
    public ResponseEntity<KaliteRed> guncelle(@PathVariable Long id, @RequestBody KaliteRed kaliteRed) {
        return ResponseEntity.ok(kaliteRedService.guncelle(id, kaliteRed));
    }

    @GetMapping("/{id}")
    @Operation(summary = "ID'ye göre kalite red kaydı getir")
    public ResponseEntity<KaliteRed> getirById(@PathVariable Long id) {
        return ResponseEntity.ok(kaliteRedService.getirById(id));
    }

    @GetMapping
    @Operation(summary = "Tüm kalite red kayıtlarını listele")
    public ResponseEntity<List<KaliteRed>> tumListe() {
        return ResponseEntity.ok(kaliteRedService.getirTumListe());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Kalite red kaydını sil")
    public ResponseEntity<Void> sil(@PathVariable Long id) {
        kaliteRedService.sil(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tarih-aralik")
    @Operation(summary = "Tarih aralığına göre kalite red kayıtlarını getir")
    public ResponseEntity<List<KaliteRed>> getirTarihAralik(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate baslangic,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate bitis) {
        return ResponseEntity.ok(kaliteRedService.getirTarihAralik(baslangic, bitis));
    }

    @GetMapping("/firma/{firmaId}")
    @Operation(summary = "Firmaya göre kalite red kayıtlarını getir")
    public ResponseEntity<List<KaliteRed>> getirFirmaRedKayitlari(@PathVariable Long firmaId) {
        return ResponseEntity.ok(kaliteRedService.getirFirmaRedKayitlari(firmaId));
    }

    @GetMapping("/rapor/{raporNo}")
    @Operation(summary = "Rapor numarasına göre kalite red kaydı getir")
    public ResponseEntity<KaliteRed> getirRaporNo(@PathVariable Long raporNo) {
        return ResponseEntity.ok(kaliteRedService.getirRaporNo(raporNo)
                .orElseThrow(() -> new RuntimeException("Rapor bulunamadı: " + raporNo)));
    }

    @GetMapping("/is-emri/{isEmriNo}")
    @Operation(summary = "İş emri numarasına göre kalite red kayıtlarını getir")
    public ResponseEntity<List<KaliteRed>> getirIsEmriRedKayitlari(@PathVariable Long isEmriNo) {
        return ResponseEntity.ok(kaliteRedService.getirIsEmriRedKayitlari(isEmriNo));
    }

    @GetMapping("/istatistik")
    @Operation(summary = "Belirli tarih aralığında red tipi istatistiklerini getir")
    public ResponseEntity<Map<String, Long>> getirRedTipIstatistik(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate baslangic,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate bitis) {
        return ResponseEntity.ok(kaliteRedService.getirRedTipIstatistik(baslangic, bitis));
    }

    @GetMapping("/yuksek-red-orani")
    @Operation(summary = "Belirli bir yüzdenin üzerindeki red kayıtlarını getir")
    public ResponseEntity<List<KaliteRed>> getirYuksekRedOraniKayitlari(
            @RequestParam Long firmaId,
            @RequestParam double yuzde) {
        return ResponseEntity.ok(kaliteRedService.getirYuksekRedOraniKayitlari(firmaId, yuzde));
    }

    @PutMapping("/toplu-guncelle")
    @Operation(summary = "Birden fazla kalite red kaydını güncelle")
    public ResponseEntity<Void> topluGuncelle(@RequestBody List<KaliteRed> kaliteRedList) {
        kaliteRedService.topluGuncelle(kaliteRedList);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/durum")
    @Operation(summary = "Kalite red kaydının durumunu güncelle")
    public ResponseEntity<Void> durumGuncelle(
            @PathVariable Long id,
            @RequestParam String yeniRedTip) {
        kaliteRedService.durumGuncelle(id, yeniRedTip);
        return ResponseEntity.ok().build();
    }
}