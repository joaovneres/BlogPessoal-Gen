package org.generation.blogPessoal.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
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

@RestController // informar que esta classe é uma controller
@RequestMapping("/postagem") // o que aparece lá em cima (Criando um endpoint)
@CrossOrigin(origins = "*", allowedHeaders = "*") // esta classe aceita requisição de qualquer origem
public class PostagemController {

	@Autowired
	private PostagemRepository repository;

	// Cadastar postagem (tela)
	@PostMapping("/cadastrar")
	public ResponseEntity<Postagem> cadastrar(@Valid @RequestBody Postagem oPostagem) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(oPostagem));
	}

	// Atualizar alguma postagem
	@PutMapping("/atualizar")
	public ResponseEntity<Postagem> alterar(@Valid @RequestBody Postagem oPostagem) {
		return ResponseEntity.ok(repository.save(oPostagem));
	}

	// Deletar um postagem
	@DeleteMapping("/deletar/{idPostagem}")
	public void deletar(@PathVariable Long idPostagem) {
		repository.deleteById(idPostagem);
	}

	// Consultar postagem pelo id
	@GetMapping("/consultar/{idPostagem}")
	public ResponseEntity<Postagem> buscarId(@PathVariable Long idPostagem) {
		return repository.findById(idPostagem).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	// Consultar postagem pela data
	@GetMapping("/consultar/{dataPostagem}")
	public ResponseEntity<Optional<List<Postagem>>> buscarTema(@PathVariable Date dataPostagem) {
		return ResponseEntity.ok(repository.findByDataPostagem(dataPostagem));
	}

	// Listar todas as postagens
	@GetMapping("/consultar/all")
	public ResponseEntity<List<Postagem>> listar() {
		return ResponseEntity.ok(repository.findAll());
	}
}