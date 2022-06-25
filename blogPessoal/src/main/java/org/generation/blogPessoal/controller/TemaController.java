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
<<<<<<< HEAD
		public ResponseEntity<Tema> getById(@PathVariable Long idTema){
			return repository.findById(idTema).map(resp -> ResponseEntity.ok(resp))
=======
		public ResponseEntity<Tema> getById(@Valid @PathVariable long id){
			return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
>>>>>>> 9d9e73770394fac00f332b425ba832cd32337e13
					.orElse(ResponseEntity.notFound().build());
		}
		
		@GetMapping("/nome/{nome}")
<<<<<<< HEAD
		public ResponseEntity<List<Tema>> getByDesc(@PathVariable String descricaoTema){
			return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricaoTema));
		}
		
		@PostMapping
		public ResponseEntity<Tema> post (@Valid @RequestBody Tema oTema){
=======
		public ResponseEntity<List<Tema>> getByName(@Valid @PathVariable String nome){
			return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(nome));
		}
		
		@PostMapping
		public ResponseEntity<Tema> post (@Valid @RequestBody Tema tema){
>>>>>>> 9d9e73770394fac00f332b425ba832cd32337e13
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(repository.save(oTema));
		}

		@PutMapping
<<<<<<< HEAD
		public ResponseEntity<Tema> put (@Valid @RequestBody Tema oTema){
			return ResponseEntity.ok(repository.save(oTema));				
=======
		public ResponseEntity<Tema> put (@Valid @RequestBody Tema tema){
			return ResponseEntity.ok(repository.save(tema));				
>>>>>>> 9d9e73770394fac00f332b425ba832cd32337e13
		}
		
		@DeleteMapping("/{id}")
		public void delete(@PathVariable Long idTema) {
			repository.deleteById(idTema);
		}
}
