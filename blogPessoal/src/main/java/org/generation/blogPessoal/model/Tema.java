package org.generation.blogPessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_tema")
public class Tema {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTema;

	@NotNull(message = "Digite o nome do tema.")
	private String nomeTema;

	@NotNull(message = "Dê uma descrição ao tema.")
	private String descricaoTema;

	@OneToMany(mappedBy = "oTema", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("oTema")
	private List<Postagem> oPostagem;

	public Tema(Long idTema, @NotNull(message = "Digite o nome do tema.") String nomeTema,
			@NotNull(message = "Dê uma descrição ao tema.") String descricaoTema, List<Postagem> oPostagem) {
		this.idTema = idTema;
		this.nomeTema = nomeTema;
		this.descricaoTema = descricaoTema;
		this.oPostagem = oPostagem;
	}

	public Tema() {
	}

	public String getNomeTema() {
		return nomeTema;
	}

	public void setNomeTema(String nomeTema) {
		this.nomeTema = nomeTema;
	}

	public Long getIdTema() {
		return idTema;
	}

	public void setIdTema(Long idTema) {
		this.idTema = idTema;
	}

	public String getDescricaoTema() {
		return descricaoTema;
	}

	public void setDescricaoTema(String descricaoTema) {
		this.descricaoTema = descricaoTema;
	}

	public List<Postagem> getoPostagem() {
		return oPostagem;
	}

	public void setoPostagem(List<Postagem> oPostagem) {
		this.oPostagem = oPostagem;
	}

}