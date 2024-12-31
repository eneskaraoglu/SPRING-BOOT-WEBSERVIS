package tr.com.bilisim.webservis.util;

import org.springframework.security.core.context.SecurityContextHolder;

import tr.com.bilisim.webservis.entities.ErpKullanici;

public class SecurityUtil {
    public static ErpKullanici getAuthenticatedUser() {
        return (ErpKullanici) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
