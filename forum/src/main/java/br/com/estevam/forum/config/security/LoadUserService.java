package br.com.estevam.forum.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.estevam.forum.repository.UsuarioRepository;

@Service
public class LoadUserService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		var result = usuarioRepository.findByEmail(username);
		
		if (result.isEmpty())
			throw new UsernameNotFoundException("Username Not Found");

		return result.get();
	}

}
