package tr.com.bilisim.webservis.service;

import lombok.RequiredArgsConstructor;
import tr.com.bilisim.webservis.entities.ErpKullanici;
import tr.com.bilisim.webservis.entities.StokEnvanterListeEntity;
import tr.com.bilisim.webservis.repostories.StokEnvanterListeRepository;
import tr.com.bilisim.webservis.util.SecurityUtil;

import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class StokEnvanterListeService {

    private final StokEnvanterListeRepository stokEnvanterListeRepository;

    public List<StokEnvanterListeEntity> getAllEnvanter() {
        return stokEnvanterListeRepository.findAll();
    }

    public StokEnvanterListeEntity getEnvanterById(Long id) {
        return stokEnvanterListeRepository.findById(id).orElse(null);
    }
    
	public List<StokEnvanterListeEntity> getFilteredEnvanter( String envanterTuru, String malzemeSeviyesi,
			String malhizAdi, String malhizKodu) {
		int filterCount = 0;

		if (envanterTuru != null)
			filterCount++;
		if (malzemeSeviyesi != null)
			filterCount++;
		if (StringUtils.hasText(malhizAdi))
			filterCount++;
		if (StringUtils.hasText(malhizKodu))
			filterCount++;
		if (filterCount < 2) {
			throw new IllegalArgumentException("En az iki filtre kriteri belirtilmelidir.");
		}
		ErpKullanici user = SecurityUtil.getAuthenticatedUser();
		return stokEnvanterListeRepository.findWithFilter(Integer.valueOf(user.getFabrika()),envanterTuru, malzemeSeviyesi, malhizAdi,
				malhizKodu);
	}
}
