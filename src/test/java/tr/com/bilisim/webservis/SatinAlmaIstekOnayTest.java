package tr.com.bilisim.webservis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import tr.com.bilisim.webservis.entities.StokEnvanterListeEntity;
import tr.com.bilisim.webservis.repostories.SatinAlmaIstekOnayRepository;
import tr.com.bilisim.webservis.service.SatinAlmaIstekOnayService;
import tr.com.bilisim.webservis.service.StokEnvanterListeService;

@ExtendWith(MockitoExtension.class)
public class SatinAlmaIstekOnayTest {
    
    @Mock
    private SatinAlmaIstekOnayRepository satinAlmaIstekOnayRepository;

    @InjectMocks
    private SatinAlmaIstekOnayService satinAlmaIstekOnayService;

    @Test
    public void testFindUserById() {
       /* User mockUser = new User(1L, "John Doe");
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        User user = userService.findUserById(1L);
        assertEquals("John Doe", user.getName());*/
    }
    
}
