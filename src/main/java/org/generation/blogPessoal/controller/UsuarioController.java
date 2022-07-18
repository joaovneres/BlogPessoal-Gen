package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.model.UsuarioLogin;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.generation.blogPessoal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private UsuarioService oUserService;

	// Cadastrar no sistema (tela)
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> cadastrar(@Valid @RequestBody Usuario oUsuario) {
		return oUserService.cadastrarUsuario(oUsuario).map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	// Logar no sistema (tela)
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> logar(@Valid @RequestBody Optional<UsuarioLogin> oUser) {
		return oUserService.autenticarUsuario(oUser).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	// Atualizar usuário no sistema (tela)
	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> atualizar(@Valid @RequestBody Usuario oUsuario) {
		return ResponseEntity.ok(repository.save(oUsuario)); // metodo já pronto
	}

	// Deletar usuário banco de dados, passando o Id dele
	@DeleteMapping("/deletar/{idUsuario}")
	public void deletar(@PathVariable Long idUsuario) {
		repository.deleteById(idUsuario);
	}

	// Consultar usuário pelo Id
	@GetMapping("/consultar/{idUsuario}")
	public ResponseEntity<Usuario> buscarId(@PathVariable Long idUsuario) {
		return repository.findById(idUsuario).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	// Consultar usuário pelo nome
	@GetMapping("/consultar/{nomeUsuario}")
	public ResponseEntity<List<Usuario>> buscarNome(@PathVariable String nomeUsuario) {
		return ResponseEntity.ok(repository.findAllByNomeUsuarioContainingIgnoreCase(nomeUsuario));
	}

	// Listar usuários
	@GetMapping("/consultar/all")
	public ResponseEntity<List<Usuario>> listar() {
		return ResponseEntity.ok(repository.findAll());
	}

}
