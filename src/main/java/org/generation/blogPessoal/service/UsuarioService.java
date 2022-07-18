package org.generation.blogPessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.model.UsuarioLogin;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository oUsuarioRepository;

	// Cadastrar Usuário
	public Optional<Usuario> cadastrarUsuario(Usuario oUsuario) {
		if (oUsuarioRepository.findByEmailUsuario(oUsuario.getEmailUsuario()).isPresent()) {
			return Optional.empty();
		}
		oUsuario.setSenhaUsuario(criptografarSenha(oUsuario.getSenhaUsuario()));
		return Optional.of(oUsuarioRepository.save(oUsuario));
	}

	// Logar no sistema
	public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> oLogin) {
		Optional<Usuario> oUsuario = oUsuarioRepository.findByEmailUsuario(oLogin.get().getSenhaLogin());

		if (oUsuario.isPresent()) {
			if (compararSenhas(oLogin.get().getSenhaLogin(), oUsuario.get().getSenhaUsuario())) {
				oLogin.get().setIdLogin(oUsuario.get().getIdUsuario());
				oLogin.get().setNomeLogin(oUsuario.get().getNomeUsuario());
				oLogin.get().setSenhaLogin(oUsuario.get().getSenhaUsuario());
				oLogin.get().setTokenLogin(gerarBasicToken(oLogin.get().getEmailLogin(), oLogin.get().getSenhaLogin()));

				return oLogin;
			}
		}
		return Optional.empty();
	}

	// Atualizar o usuário
	public Optional<Usuario> atualizarUsuario(Usuario oUsuario) {
		if (oUsuarioRepository.findById(oUsuario.getIdUsuario()).isPresent()) {
			Optional<Usuario> buscarUsuario = oUsuarioRepository.findByEmailUsuario(oUsuario.getEmailUsuario());
			if ((buscarUsuario.isPresent()) && (buscarUsuario.get().getIdUsuario() != oUsuario.getIdUsuario()))
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
			oUsuario.setSenhaUsuario(criptografarSenha(oUsuario.getSenhaUsuario()));
			return Optional.ofNullable(oUsuarioRepository.save(oUsuario));
		}
		return Optional.empty();
	}

	// Criptografar senha
	private String criptografarSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(senha);
	}

	// Comparar senhas para logar
	private Boolean compararSenhas(String senhaDigitada, String senhaCadastrada) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(senhaDigitada, senhaCadastrada);
	}

	// Gerar token de acesso
	private String gerarBasicToken(String usuario, String senha) {

		String token = usuario + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);
	}
}
