package tr.com.bilisim.webservis.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class ApiUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public ApiUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
        setFilterProcessesUrl("/api/login"); // API giriş noktası
        setAuthenticationFailureHandler(new ApiAuthenticationFailureHandler()); // Özel failure handler
    }
}
