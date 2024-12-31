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
import tr.com.bilisim.webservis.entities.SatinAlmaIstekOnayViewEntity;
import tr.com.bilisim.webservis.service.SatinAlmaIstekOnayService;

@RestController
@RequestMapping("/api/satinalma/istek-onay")
public class RestSatinAlmaOnayController {

	private final SatinAlmaIstekOnayService satinAlmaIstekOnayService;

	@Autowired
	public RestSatinAlmaOnayController(SatinAlmaIstekOnayService satinAlmaIstekOnayService) {
		this.satinAlmaIstekOnayService = satinAlmaIstekOnayService;
	}

	@Operation(summary = "Satınalma İstek Liste endpoint", description = "Satınalma İsteklerinin listelendiği endpointtir.")
	@GetMapping
	public ResponseEntity<Map<String, Object>> getOnayTalepleri(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@Parameter(description = "İstek Durum Paramteresi 01:Onay Bekleyen, 02:Onaylı,07:Red Edilen") @RequestParam(required = false) String istekDurum) {

		if (istekDurum == null || istekDurum.isEmpty()) {
			istekDurum = "01";
		}

		Pageable pageable = PageRequest.of(page, size);
		Page<SatinAlmaIstekOnayViewEntity> onayTalepleri = satinAlmaIstekOnayService.getPagedData(istekDurum, pageable);

		Map<String, Object> response = new HashMap<>();

		response.put("onayTalepleri", onayTalepleri.getContent());
		response.put("currentPage", page);
		response.put("istekDurum", istekDurum);
		response.put("totalPages", onayTalepleri.getTotalPages());
		response.put("totalItems", onayTalepleri.getTotalElements());

		return ResponseEntity.ok(response);
	}

	@Operation(summary = "Satınalma İstek Onaylama", description = "Satınalma İsteklerin Onaylandığı endpointtir.")
	@PostMapping("/onayla/{id}")
	public ResponseEntity<String> approveRequest(
			@Parameter(description = "İstek Durumu update edeilecek id. istekDtId ") @PathVariable Long id
			) {
		try {
			satinAlmaIstekOnayService.approveRequest(id);
			return ResponseEntity.ok("Request approved successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to approve request: " + e.getMessage());
		}
	}
	
	@Operation(summary = "Satınalma İstek Red", description = "Satınalma İsteklerin red edildi endpointtir.")
	@PostMapping("/reddet/{id}")
	public ResponseEntity<String> rejectRequest(
			@Parameter(description = "İstek Durumu red edeilecek id. istekDtId ") @PathVariable Long id,
			@Parameter(description = "İstek Durumu red için açıklama alanı ") @RequestParam String redGerekcesi) {
		try {
			satinAlmaIstekOnayService.rejectRequest(id, redGerekcesi);
			return ResponseEntity.ok("Request rejected successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to reject request: " + e.getMessage());
		}
	}
}
