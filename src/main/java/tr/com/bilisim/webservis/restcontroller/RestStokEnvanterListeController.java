package tr.com.bilisim.webservis.restcontroller;

import lombok.RequiredArgsConstructor;
import tr.com.bilisim.webservis.entities.StokEnvanterListeEntity;
import tr.com.bilisim.webservis.service.StokEnvanterListeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

@RestController
@RequestMapping("/api/stok/envanter-liste")
@RequiredArgsConstructor
public class RestStokEnvanterListeController {

    private final StokEnvanterListeService stokEnvanterListeService;

    @Operation(summary = "Envanterlerin Listelendiği endpoint", description = "Envanterlerin Listelendiği endpoint")
    @GetMapping("/all")
    public ResponseEntity<List<StokEnvanterListeEntity>> getAllEnvanter() {
        List<StokEnvanterListeEntity> envanterList = stokEnvanterListeService.getAllEnvanter();
        return envanterList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(envanterList);
    }

    @Operation(summary = "Envanterlerin MLZ_ID bazında Listelendiği endpoint", description = "Envanterlerin MLZ_ID bazında Listelendiği endpoint")
    @GetMapping("/{id}")
    public ResponseEntity<StokEnvanterListeEntity> getEnvanterById(
        @Parameter(description = "Parametre olarak mlz_id kullanılmalı") @PathVariable Long id) {
        StokEnvanterListeEntity envanter = stokEnvanterListeService.getEnvanterById(id);
        return envanter != null ? ResponseEntity.ok(envanter) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Envanterlerin Parametreli Olarak Listelendiği endpoint", description = "Envanterlerin Parametreli Olarak Listelendiği endpoint")
    @GetMapping
    public ResponseEntity<List<StokEnvanterListeEntity>> getFilteredEnvanter(
        @Parameter(description = "Envanter Türü 0:Malzeme-Hizmet, 1:Yarı Mamül, 2:Son Ürün") @RequestParam(required = false) String envanterTuru,
        @Parameter(description = "Malzeme Seviyesi 1:1.serviye, 2:2.serviye, 3:3.serviye") @RequestParam(required = false) String malzemeSeviyesi,
        @Parameter(description = "Malzeme Adı : ")@RequestParam(required = false) String malhizAdi,
        @Parameter(description = "Malzeme Kodu :") @RequestParam(required = false) String malhizKodu) {

        List<StokEnvanterListeEntity> filteredEnvanter = stokEnvanterListeService.getFilteredEnvanter(envanterTuru, malzemeSeviyesi, malhizAdi, malhizKodu);
        return filteredEnvanter.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(filteredEnvanter);
    }
}
