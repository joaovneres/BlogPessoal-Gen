package org.generation.blogPessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.TemaRepository;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/tema")
public class TemaController {

	@Autowired
	private TemaRepository repository;

	// Cadastar tema (tela)
	@PostMapping("/cadastrar")
	public ResponseEntity<Tema> cadastrar(@Valid @RequestBody Tema oTema) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(oTema));
	}

	// Atualizar algum tema
	@PutMapping("/atualizar")
	public ResponseEntity<Tema> alterar(@Valid @RequestBody Tema oTema) {
		return ResponseEntity.ok(repository.save(oTema));
	}

	// Deletar um tema
	@DeleteMapping("/deletar/{idTema}")
	public void deletar(@PathVariable Long idTema) {
		repository.deleteById(idTema);
	}

	// Consultar tema pelo id
	@GetMapping("/consultar/{idTema}")
	public ResponseEntity<Tema> buscarId(@PathVariable Long idTema) {
		return repository.findById(idTema).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	// Consultar tema pelo nome
	@GetMapping("/consultar/{nomeTema}")
	public ResponseEntity<List<Tema>> buscarTema(@PathVariable String nomeTema) {
		return ResponseEntity.ok(repository.findAllByNomeTemaContainingIgnoreCase(nomeTema));
	}

	// Listar todos os temas
	@GetMapping("/consultar/all")
	public ResponseEntity<List<Tema>> listar() {
		return ResponseEntity.ok(repository.findAll());
	}

}