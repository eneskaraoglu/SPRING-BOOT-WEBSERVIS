package tr.com.bilisim.webservis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tr.com.bilisim.webservis.entities.StokEnvanterListeEntity;
import tr.com.bilisim.webservis.service.StokEnvanterListeService;

@SpringBootTest
public class StokEnvanterListeTest {

	@Autowired
    private StokEnvanterListeService stokEnvanterListeService;
    
    @Test
    public void testgetEnvanterById() {
    	StokEnvanterListeEntity stokEnvanterListeEntity = stokEnvanterListeService.getEnvanterById(1L);
        assertEquals("DENEME STOK", stokEnvanterListeEntity.getMalhizKodu());
    }
    
}
