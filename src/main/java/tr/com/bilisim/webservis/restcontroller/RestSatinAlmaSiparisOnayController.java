package tr.com.bilisim.webservis.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import tr.com.bilisim.webservis.entities.SatinAlmaSiparisOnayViewEntity;
import tr.com.bilisim.webservis.service.SatinAlmaSiparisOnayService;

@RestController
@RequestMapping("/api/satinalma/siparis-onay")
public class RestSatinAlmaSiparisOnayController {

	@Autowired
	private final SatinAlmaSiparisOnayService satinAlmaSiparisOnayService;

	@Autowired
	public RestSatinAlmaSiparisOnayController(SatinAlmaSiparisOnayService satinAlmaSiparisOnayService) {
		this.satinAlmaSiparisOnayService = satinAlmaSiparisOnayService;
	}

	@Operation(summary = "Satınalma Sipariş Liste endpoint", description = "Satınalma Siparişlerinin listelendiği endpointtir.")
	@GetMapping
	public ResponseEntity<Map<String, Object>> getOnayTalepleri(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@Parameter(description = "Sipariş Durum Paramteresi B:Beklemede, O:Onaylı,I:İptal") @RequestParam(required = false) String satSipDurum) {

		if (satSipDurum == null || satSipDurum.isEmpty()) {
			satSipDurum = "B";
		}

		Pageable pageable = PageRequest.of(page, size);
		Page<SatinAlmaSiparisOnayViewEntity> onayTalepleri = satinAlmaSiparisOnayService.getPagedData(satSipDurum, pageable);

		Map<String, Object> response = new HashMap<>();
		response.put("onayTalepleri", onayTalepleri.getContent());
		response.put("satSipDurum", satSipDurum);
		response.put("currentPage", page);
		response.put("totalPages", onayTalepleri.getTotalPages());
		response.put("totalItems", onayTalepleri.getTotalElements());

		return ResponseEntity.ok(response);
	}

	@Operation(summary = "Satınalma Sipariş Onaylama", description = "Satınalma Siparişlerinin Onaylandığı endpointtir.")
	@PostMapping("/onayla/{id}")
	public ResponseEntity<String> approveRequest(
			@Parameter(description = "Sipariş Durumu update edeilecek id. satSiparisDtId ") @PathVariable Long id,
			@Parameter(description = "Sipariş Açıklama alanına not eklemek için kullanılır") @RequestParam String sipAciklama) {
		try {
			String satSipDurum = "O";
			satinAlmaSiparisOnayService.approveRequest(id, sipAciklama, satSipDurum);
			return ResponseEntity.ok("Request approved successfully.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to approve request: " + e.getMessage());
		}
	}

	@Operation(summary = "Satınalma Sipariş Red", description = "Satınalma Siparişlerinin reddedildiği endpointtir.")
	@PostMapping("/reddet/{id}")
	public ResponseEntity<String> rejectRequest(
			@Parameter(description = "Sipariş Durumu update edeilecek id. satSiparisDtId ") @PathVariable Long id,
			@Parameter(description = "Sipariş Açıklama alanına not eklemek için kullanılır") @RequestParam String sipAciklama) {
		try {
			String satSipDurum = "I";
			satinAlmaSiparisOnayService.approveRequest(id, sipAciklama, satSipDurum);
			return ResponseEntity.ok("Request rejected successfully.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to reject request: " + e.getMessage());
		}
	}
}
