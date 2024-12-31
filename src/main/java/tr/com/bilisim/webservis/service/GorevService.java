package tr.com.bilisim.webservis.service;

import java.rmi.ServerException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import tr.com.bilisim.webservis.exception.GeneralException;
import tr.com.bilisim.webservis.exception.GorevNotFoundException;
import tr.com.bilisim.webservis.exception.HttpClientException;
import tr.com.bilisim.webservis.exception.RateLimitException;


@Service
public class GorevService {

    @Value("${jboss.address}")
    private String baseUrl;

    public String getGorev(String gorevId) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String url = baseUrl + "/UrtWEB/gorev?gorevId=" + gorevId;

        try {
            return restTemplate.getForObject(url, String.class);
        } catch (HttpClientErrorException e) {
            // 4xx hatalari
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new GorevNotFoundException("Görev bulunamadı: " + gorevId);
            } else if (e.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS) {
                throw new RateLimitException("Çok fazla istek gönderildi.");
            } else {
                throw new HttpClientException("İstek sırasında bir hata oluştu: " + e.getMessage());
            }
        } catch (HttpServerErrorException e) {
            // 5xx hataları
            throw new ServerException("Sunucu hatası: " + e.getMessage());
        } catch (Exception e) {
            // Diğer tüm hatalar
            throw new GeneralException("Bilinmeyen bir hata oluştu: " + e.getMessage());
        }
    }

}
