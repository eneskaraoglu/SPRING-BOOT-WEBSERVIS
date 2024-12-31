package tr.com.bilisim.webservis.restcontroller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import tr.com.bilisim.webservis.jwt.AuthRequest;
import tr.com.bilisim.webservis.jwt.JwtUtil;
import tr.com.bilisim.webservis.security.ApiAuthenticationFailureHandler;
import tr.com.bilisim.webservis.security.ApiAuthenticationSuccessHandler;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // Frontend için CORS ayarı
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    // Login endpoint
    @PostMapping("/login")
    public void login(@RequestBody AuthRequest loginRequest, HttpServletResponse response) throws IOException, ServletException {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            String token = jwtUtil.generateToken(loginRequest.getUsername());
            new ApiAuthenticationSuccessHandler().onAuthenticationSuccess(null, response, authentication, token);

        } catch (Exception e) {
            new ApiAuthenticationFailureHandler().onAuthenticationFailure(null, response, (AuthenticationException) e);
        }
    }

    // OPTIONS isteğini yanıtlamak için
    @RequestMapping(value = "/login", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().build();
    }
}
