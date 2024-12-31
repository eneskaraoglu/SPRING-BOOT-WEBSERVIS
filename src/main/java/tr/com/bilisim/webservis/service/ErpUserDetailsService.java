package tr.com.bilisim.webservis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tr.com.bilisim.webservis.entities.ErpKullanici;

import java.util.Collections;

@Service
public class ErpUserDetailsService implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        String query = "SELECT KULLANICI, SIFRE, FABRIKA FROM ERPORT.ERP_KULLANICILAR WHERE KULLANICI = ?";
        
        try {
            return (UserDetails) jdbcTemplate.queryForObject(query, new Object[]{username}, (rs, rowNum) -> {
                String kullanici = rs.getString("KULLANICI");
                String sifre = rs.getString("SIFRE");
                String fabrika = rs.getString("FABRIKA");

                // Return a CustomUser object with the credentials
                return new ErpKullanici(kullanici, sifre, fabrika);
            });
        } catch (Exception e) {
            throw new UsernameNotFoundException("Kullanıcı bulunamadı: " + username);
        }
    }
}
