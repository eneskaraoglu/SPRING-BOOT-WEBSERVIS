package tr.com.bilisim.webservis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tr.com.bilisim.webservis.entities.ErpKullanici;
import tr.com.bilisim.webservis.entities.FirmaListeEntity;
import tr.com.bilisim.webservis.repostories.FirmaListeRepository;
import tr.com.bilisim.webservis.util.SecurityUtil;

@Service
@RequiredArgsConstructor
public class FirmaListeService {

    private final FirmaListeRepository firmaListeRepository;

    public List<FirmaListeEntity> getAllFirma() {
        return firmaListeRepository.findAll();
    }

    public FirmaListeEntity getFirmaById(Long id) {
        return firmaListeRepository.findById(id).orElse(null);
    }
    
	public List<FirmaListeEntity> getFilteredFirma( String firmaAdi, String firmaKodu) {
		ErpKullanici user = SecurityUtil.getAuthenticatedUser();
		return firmaListeRepository.findWithFilter(Integer.valueOf(user.getFabrika()),firmaAdi, firmaKodu);
	}
}
