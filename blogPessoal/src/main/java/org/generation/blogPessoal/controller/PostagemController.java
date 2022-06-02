package org.generation.blogPessoal.controller;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // informar que esta classe é uma controller
@RequestMapping("/postagens") // o que aparece lá em cima (Criando um endpoint)
@CrossOrigin(origins = "*") // esta classe aceita requisição de qualquer origem
public class PostagemController {

	@Autowired // por conta da debaixo ser uma interface, este comando força que acessemos,
				// mesmo não instanciando
				// transferência de responsabilidade, injeção de depêndencia
	private PostagemRepository repository;

	@GetMapping
	public ResponseEntity<List<Postagem>> listarPostagem() {
		return ResponseEntity.ok(repository.findAll());
	}

	@PostMapping
	public ResponseEntity<Postagem> inserirPostagem(@RequestBody Postagem oPostagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(oPostagem));	
	}
}