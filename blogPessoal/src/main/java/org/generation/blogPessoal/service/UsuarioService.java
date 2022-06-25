package org.generation.blogPessoal.service;

import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.model.UsuarioLogin;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository oUsuarioRepository;

	public Optional<Usuario> cadastrarUsuario(Usuario oUsuario) {
		if (oUsuarioRepository.findByEmailUsuario(oUsuario.getEmailUsuario()).isPresent()) {
			return Optional.empty();
		}
		oUsuario.setSenhaUsuario(criptografarSenha(oUsuario.getSenhaUsuario()));
		return Optional.of(oUsuarioRepository.save(oUsuario));
	}

	public Optional<Usuario> atualizarUsuario(Usuario oUsuario) {
		if (oUsuarioRepository.findById(oUsuario.getIdUsuario()).isPresent()) {
			Optional<Usuario> buscarUsuario = oUsuarioRepository.findByEmailUsuario(oUsuario.getEmailUsuario());
			if ((buscarUsuario.isPresent()) && (buscarUsuario.get().getIdUsuario() != oUsuario.getIdUsuario()))
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
			oUsuario.setSenhaUsuario(criptpgrafarSenha(oUsuario.getSenhaUsuario()));
			return Optional.ofNullable(oUsuarioRepository.save(oUsuario));
		}
		return Optional.empty();
	}
	
	public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> oLogin){
		Optional<Usuario> oUsuario = oUsuarioRepository.findByEmailUsuario(oLogin.get().getSenhaLogin());
		
	}
}
