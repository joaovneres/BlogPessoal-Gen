package org.generation.blogPessoal.seguranca;

import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioRepository oUsuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String oUserName) throws UsernameNotFoundException{
		Optional<Usuario> oUser = oUsuarioRepository.findByEmailUsuario(oUserName);
		
		oUser.orElseThrow(() -> new UsernameNotFoundException(oUserName + " not found."));
		
		return oUser.map(UserDetailsImpl::new).get();
	}
}
