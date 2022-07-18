package org.generation.blogPessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;

	@NotNull(message = "O nome é obrigatório!")
	@Size(min = 2, max = 100)
	private String nomeUsuario;

	@Schema(example = "email@email.com")
	@NotNull(message = "O atributo email é Obrigatório!")
	@Email(message = "O email deve ser válido!")
	private String emailUsuario;

	@NotNull(message = "A senha é obrigatória!")
	@Size(min = 8, max = 100)
	private String senhaUsuario;

	@OneToMany(mappedBy = "oUsuario", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("oUsuario")
	private List<Postagem> oPostagem;

	// metodo construtor
	public Usuario(Long idUsuario,
			@NotNull(message = "O nome é obrigatório!") @Size(min = 2, max = 100) String nomeUsuario,
			@NotNull(message = "O atributo email é Obrigatório!") @Email(message = "O email deve ser válido!") String emailUsuario,
			@NotNull(message = "A senha é obrigatória!") @Size(min = 8, max = 100) String senhaUsuario) {
		this.idUsuario = idUsuario;
		this.nomeUsuario = nomeUsuario;
		this.emailUsuario = emailUsuario;
		this.senhaUsuario = senhaUsuario;
	}

	// metodo construtor vazio
	public Usuario() {
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}
}
