package tr.com.bilisim.webservis.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tr.com.bilisim.webservis.service.ErpUserDetailsService;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private ErpUserDetailsService userDetailsService;

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
	        jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {

	    final String authorizationHeader = request.getHeader("Authorization");

	    String username = null;
	    String jwt = null;

	    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	        jwt = authorizationHeader.substring(7);
	        try {
	            username = jwtUtil.extractUsername(jwt);
	        } catch (ExpiredJwtException e) {
	            response.setStatus(HttpStatus.UNAUTHORIZED.value());
	            response.setContentType("application/json;charset=UTF-8");
	            response.getWriter().write("{\"timeout\": \"Oturum süresi doldu. Lütfen tekrar giriş yapın.\"}");
	            return;
	        } catch (MalformedJwtException e) {
	            response.setStatus(HttpStatus.BAD_REQUEST.value());
	            response.setContentType("application/json;charset=UTF-8");
	            response.getWriter().write("{\"error\": \"Token formatı geçersiz.\"}");
	            return;
	        } catch (SignatureException e) {
	            response.setStatus(HttpStatus.UNAUTHORIZED.value());
	            response.setContentType("application/json;charset=UTF-8");
	            response.getWriter().write("{\"error\": \"Token imzası geçersiz.\"}");
	            return;
	        } catch (JwtException e) {
	            response.setStatus(HttpStatus.BAD_REQUEST.value());
	            response.setContentType("application/json;charset=UTF-8");
	            response.getWriter().write("{\"error\": \"Token doğrulanırken bir hata oluştu.\"}" );
	            return;
	        }
	    }

	    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	        if (jwtUtil.isTokenValid(jwt, username)) {
	            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	            var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	            SecurityContextHolder.getContext().setAuthentication(authToken);
	        }
	    }

	    filterChain.doFilter(request, response);
	}

}
