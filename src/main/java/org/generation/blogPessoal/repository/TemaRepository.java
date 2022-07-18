package org.generation.blogPessoal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.generation.blogPessoal.model.Tema;

public interface TemaRepository extends JpaRepository<Tema, Long> {
	
	public Optional<Tema> findByNomeTema(String nomeTema);
	
	public List<Tema> findAllByNomeTemaContainingIgnoreCase(@Param("tema") String nomeTema);
}