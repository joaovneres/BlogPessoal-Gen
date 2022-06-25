package org.generation.blogPessoal.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // aqui seria a dao, pois tem os comandos de selecionar e afins
public interface PostagemRepository extends JpaRepository<Postagem, Long> {

	public Optional<List<Postagem>> findByDataPostagem(Date dataPostagem);
}