package br.com.estevam.forum.config.security;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.estevam.forum.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${forum.jwt.expiration}")
	private String expiration;

	@Value("${forum.jwt.secret}")
	private String secret;
	
	public String createToken(Authentication authenticate) {
		
		final var hoje = new Date();
		final var dataExp = new Date(hoje.getTime() + Long.parseLong(expiration));
		
		Usuario usuario = (Usuario) authenticate.getPrincipal();
			
		return Jwts.builder()
				.setIssuer("API do Fórum Alura")
				.setSubject(usuario.getId().toString())
				.setIssuedAt(hoje)
				.setExpiration(dataExp)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public Optional<Claims> parseToken(String token) {

		try {
			return Optional.of(Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody());
		} catch (Exception e) {
			return Optional.ofNullable(null);
		}
		
	}

}
