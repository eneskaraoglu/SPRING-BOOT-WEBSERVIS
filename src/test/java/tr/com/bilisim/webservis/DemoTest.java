package tr.com.bilisim.webservis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tr.com.bilisim.webservis.entities.StokEnvanterListeEntity;
import tr.com.bilisim.webservis.service.StokEnvanterListeService;

@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)
@SpringBootTest
public class DemoTest {

	@Autowired
    private StokEnvanterListeService stokEnvanterListeService;
    
	
    @Test
    @DisplayName("EnvanterById test edilmesi")
    public void testgetEnvanterById() {
    	StokEnvanterListeEntity stokEnvanterListeEntity = stokEnvanterListeService.getEnvanterById(1L);
        assertEquals("DENEME STOK", stokEnvanterListeEntity.getMalhizKodu(),"Gelene Envanter, İstenen değil!!!");
        assertEquals("DENEME STOK", stokEnvanterListeEntity.getMalhizKodu(),"Gelene Envanter, İstenen değil!!!");
        assertNotEquals(new StokEnvanterListeEntity(), stokEnvanterListeEntity.getClass(), "Gelen obje StokEnvanterListeEntity değil.");
    }
    
    @Test
    public void testgetEnvanterById2() {
    	StokEnvanterListeEntity stokEnvanterListeEntity = stokEnvanterListeService.getEnvanterById(1L);
        assertEquals("DENEME STOK", stokEnvanterListeEntity.getMalhizKodu(),"Gelene Envanter, İstenen değil!!!");
        assertNotEquals(new StokEnvanterListeEntity(), stokEnvanterListeEntity.getClass(), "Gelen obje StokEnvanterListeEntity değil.");
    }
    
    @BeforeAll
    static public void beforeAll() {
		System.out.println("DemoTest beforeAll");
	}
    
    @AfterAll
    static public void afterAll() {
		System.out.println("DemoTest afterAll");	
	}
    

}
