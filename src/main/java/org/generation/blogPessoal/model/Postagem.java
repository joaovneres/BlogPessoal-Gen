package org.generation.blogPessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity // esse comando faz com que essa classe seja interpretada pelo banco de dados
		// como entidade
@Table(name = "postagem") // esse comando faz criar a tabela dentro do banco de dados
public class Postagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// os dois comandos aqui de cima fazem com que crie a variável id
	// automaticamente e como ela fosse a PK
	private Long idPostagem;

	@NotNull(message = "Digite o título da postagem.") // além de não permitir nulo, não permite vazio
	@Size(min = 5, max = 100)
	private String tituloPostagem;

	@NotNull(message = "O texto da postagem está vazio, digite algo.")
	@Size(min = 5, max = 255)
	private String textoPostagem;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPostagem = new java.sql.Date(System.currentTimeMillis()); // calcula a hora exato que o objeto
																				// entra na classe
	@ManyToOne
	@JsonIgnoreProperties("oPostagem")
	private Tema oTema;

	@ManyToOne
	@JsonIgnoreProperties("oPostagem")
	private Usuario oUsuario;

	public Postagem(Long idPostagem,
			@NotNull(message = "Digite o título da postagem.") @Size(min = 5, max = 100) String tituloPostagem,
			@NotNull(message = "O texto da postagem está vazio, digite algo.") @Size(min = 5, max = 255) String textoPostagem,
			Date dataPostagem, Tema oTema, Usuario oUsuario) {
		this.idPostagem = idPostagem;
		this.tituloPostagem = tituloPostagem;
		this.textoPostagem = textoPostagem;
		this.dataPostagem = dataPostagem;
		this.oTema = oTema;
		this.oUsuario = oUsuario;
	}

	public Postagem() {
	}

	public Long getIdPostagem() {
		return idPostagem;
	}

	public void setIdPostagem(Long idPostagem) {
		this.idPostagem = idPostagem;
	}

	public String getTituloPostagem() {
		return tituloPostagem;
	}

	public Usuario getoUsuario() {
		return oUsuario;
	}

	public void setoUsuario(Usuario oUsuario) {
		this.oUsuario = oUsuario;
	}

	public void setTituloPostagem(String tituloPostagem) {
		this.tituloPostagem = tituloPostagem;
	}

	public String getTextoPostagem() {
		return textoPostagem;
	}

	public void setTextoPostagem(String textoPostagem) {
		this.textoPostagem = textoPostagem;
	}

	public Date getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(Date dataPostagem) {
		this.dataPostagem = dataPostagem;
	}

	public Tema getoTema() {
		return oTema;
	}

	public void setoTema(Tema oTema) {
		this.oTema = oTema;
	}

}
