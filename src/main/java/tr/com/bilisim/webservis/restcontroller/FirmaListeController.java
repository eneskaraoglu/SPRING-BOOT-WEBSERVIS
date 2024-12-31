package tr.com.bilisim.webservis.restcontroller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import tr.com.bilisim.webservis.entities.FirmaListeEntity;
import tr.com.bilisim.webservis.service.FirmaListeService;

@RestController
@RequestMapping("/api/ortak/firma-liste")
@RequiredArgsConstructor
public class FirmaListeController {

    private final FirmaListeService firmaListeService;

    @Operation(summary = "Firmaların Listelendiği endpoint", description = "Firmaların Listelendiği endpoint")
    @GetMapping("/all")
    public ResponseEntity<List<FirmaListeEntity>> getAllFirma() {
        List<FirmaListeEntity> envanterList = firmaListeService.getAllFirma();
        return envanterList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(envanterList);
    }

    @Operation(summary = "Firmaların FIRMA_ID bazında Listelendiği endpoint", description = "Firmaların FIRMA bazında Listelendiği endpoint")
    @GetMapping("/{id}")
    public ResponseEntity<FirmaListeEntity> getFirmaById(
        @Parameter(description = "Parametre olarak firma_id kullanılmalı") @PathVariable Long id) {
    	FirmaListeEntity envanter = firmaListeService.getFirmaById(id);
        return envanter != null ? ResponseEntity.ok(envanter) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Firmaların Firma Kodu, Adına göre filitreli Listelendiği endpoint", description = "Firmaların Firma Kodu, Adına göre filitreli Listelendiği endpoint")
    @GetMapping
    public ResponseEntity<List<FirmaListeEntity>> getFilteredFirma(
    	@Parameter(description = "Firma Adı :") @RequestParam(required = false) String fimraAdi,
        @Parameter(description = "Firma Kodu :") @RequestParam(required = false) String firmaKodu) {

        List<FirmaListeEntity> filteredFirma = firmaListeService.getFilteredFirma(fimraAdi, firmaKodu);
        return filteredFirma.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(filteredFirma);
    }
}
