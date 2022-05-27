package org.generation.blogPessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity //esse comando faz com que essa classe seja interpretada pelo banco de dados como entidade
@Table(name = "postagem") //esse comando faz criar a tabela dentro do banco de dados
public class Postagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	//os dois comandos aqui de cima fazem com que crie a variável id automaticamente e como ela fosse a PK
	private long id;
	
	@NotBlank //além de não permitir nulo, não permite vazio
	@Size(min=5, max=100)
	private String titulo;
	@NotBlank
	@Size(min=5, max=500)
	private String texto;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new java.sql.Date(System.currentTimeMillis()); // calcula a hora exato que o objeto entra na
																		// classe

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
