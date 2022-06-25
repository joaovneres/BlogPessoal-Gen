package org.generation.blogPessoal.controller;

import java.util.List;

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

	@GetMapping("/{idPostagem}")
	public ResponseEntity<Postagem> buscarId(@PathVariable Long idPostagem){ //@PathVariable pega a variavel do caminho da url
		return repository.findById(idPostagem)
				.map(resp -> ResponseEntity.ok(resp)) //caso encontre
				.orElse(ResponseEntity.notFound().build()); //caso não encontre
	}
	
	@GetMapping("/titulo/{titulo}") //criamos um novo endpoint
	public ResponseEntity<List<Postagem>> buscarTitulo(@PathVariable String tituloPostagem){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(tituloPostagem));
	}
	
	@PostMapping
	public ResponseEntity<Postagem> inserirPostagem(@Valid @RequestBody Postagem oPostagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(oPostagem));	
	}
	
	@PutMapping
	public ResponseEntity<Postagem> atualizarPostagem(@Valid @RequestBody Postagem oPostagem){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(oPostagem));
	}
	
	@DeleteMapping("/{idPostagem}")
	public void delete(@PathVariable Long idPostagem) {
		repository.deleteById(idPostagem);
	}
}