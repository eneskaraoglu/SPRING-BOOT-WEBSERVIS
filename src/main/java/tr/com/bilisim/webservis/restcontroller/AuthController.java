package tr.com.bilisim.webservis.restcontroller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import tr.com.bilisim.webservis.entities.ErpKullanici;
import tr.com.bilisim.webservis.jwt.AuthRequest;
import tr.com.bilisim.webservis.jwt.JwtUtil;
import tr.com.bilisim.webservis.security.ApiAuthenticationFailureHandler;
import tr.com.bilisim.webservis.security.ApiAuthenticationSuccessHandler;
import tr.com.bilisim.webservis.util.SecurityUtil;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public void login(@RequestBody AuthRequest loginRequest, HttpServletResponse response) throws IOException, ServletException {
        try {
        	Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        	String token = jwtUtil.generateToken(loginRequest.getUsername());
            new ApiAuthenticationSuccessHandler().onAuthenticationSuccess(null, response, authentication,token);
            
        } catch (Exception e) {
            new ApiAuthenticationFailureHandler().onAuthenticationFailure(null, response, (AuthenticationException) e);
        }
    }
}
