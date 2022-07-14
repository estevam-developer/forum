package br.com.estevam.forum.config.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.estevam.forum.modelo.Usuario;
import br.com.estevam.forum.repository.UsuarioRepository;
import io.jsonwebtoken.Claims;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	
	private UsuarioRepository usuarioRepository;

	public TokenAuthenticationFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;		
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		var token = extractToken(request);
		
		var optional = tokenService.parseToken(token);
		
		authenticate(optional);
		
		filterChain.doFilter(request, response);
	}

	private void authenticate(Optional<Claims> optional) {
		if (optional.isPresent()) {
			var usuario = retrieveUser(optional.get());
			
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
	}

	private Usuario retrieveUser(Claims claims) {
		var userId = Long.parseLong(claims.getSubject());
		
		return usuarioRepository.findById(userId).get();		
	}

	private String extractToken(HttpServletRequest request) {
		
		return request.getHeader("Authorization") == null ? "" : request.getHeader("Authorization").substring(7);
		
	}
	
	

}
