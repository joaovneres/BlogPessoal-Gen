package org.generation.blogPessoal.repository;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	public Optional<Usuario> findByEmailUsuario(String emailUsuario);
	
	public List<Usuario> findAllByNomeUsuarioContainingIgnoreCase(@Param("nome") String nomeUsuario);
}