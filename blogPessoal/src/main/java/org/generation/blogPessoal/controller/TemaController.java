package org.generation.blogPessoal.controller;

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

import java.util.List;

import javax.validation.Valid;

import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.TemaRepository;

	@RestController
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping("/tema")
	public class TemaController {
		
		@Autowired
		private TemaRepository repository;
		
		@GetMapping
		public ResponseEntity<List<Tema>> getAll(){
			return ResponseEntity.ok(repository.findAll());
		}
		
		@GetMapping("/{id}")
		public ResponseEntity<Tema> getById(@PathVariable Long idTema){
			return repository.findById(idTema).map(resp -> ResponseEntity.ok(resp))
					.orElse(ResponseEntity.notFound().build());
		}
		
		@GetMapping("/nome/{nome}")
		public ResponseEntity<List<Tema>> getByDesc(@PathVariable String descricaoTema){
			return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricaoTema));
		}
		
		@PostMapping
		public ResponseEntity<Tema> post (@Valid @RequestBody Tema oTema){
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(repository.save(oTema));
		}

		@PutMapping
		public ResponseEntity<Tema> put (@Valid @RequestBody Tema oTema){
			return ResponseEntity.ok(repository.save(oTema));				
		}
		
		@DeleteMapping("/{id}")
		public void delete(@PathVariable Long idTema) {
			repository.deleteById(idTema);
		}
}
