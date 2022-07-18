package org.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;

	// Preparação de um banco de dados de teste
	@BeforeAll
	void start() {

		// Deletar tudo do bando de dados
		usuarioRepository.deleteAll();

		// Salvar usuários no BD, nesse caso 4
		usuarioRepository.save(new Usuario(0L, "João Neres", "neres@gmail.com", "12345678"));
		usuarioRepository.save(new Usuario(0L, "Gean Sousa", "sousa@gmail.com", "12345678"));
		usuarioRepository.save(new Usuario(0L, "Viviana Neres", "vn@gmail.com", "12345678"));
		usuarioRepository.save(new Usuario(0L, "Adauto Sousa", "as@gmail.com", "12345678"));

	}

	// Início dos testes
	@Test
	@DisplayName("Retorna 1 usuário")
	public void returnOneUser() {

		// Testar se o sistema retorna o email
		Optional<Usuario> oUsuario = usuarioRepository.findByEmailUsuario("neres@gmail.com");

		/*
		 * Verificar se o sistema encontrou um igual ao email buscado
		 * "É verdade que a busca retornou o email neres@gmail.com.br" é verdadeira Se
		 * for verdaeira, o teste passa, senão o teste falha.
		 */

		assertTrue(oUsuario.get().getEmailUsuario().equals("neres@gmail.com"));
	}
}
