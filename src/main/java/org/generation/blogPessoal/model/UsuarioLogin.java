package org.generation.blogPessoal.model;

public class UsuarioLogin {

	private Long idLogin;
	
	private String nomeLogin;

	private String emailLogin;

	private String senhaLogin;

	private String tokenLogin;

	
	public UsuarioLogin(Long idLogin, String nomeLogin, String emailLogin, String senhaLogin, String tokenLogin) {
		this.idLogin = idLogin;
		this.nomeLogin = nomeLogin;
		this.emailLogin = emailLogin;
		this.senhaLogin = senhaLogin;
		this.tokenLogin = tokenLogin;
	}
	
	public UsuarioLogin() {}

	public Long getIdLogin() {
		return idLogin;
	}

	public void setIdLogin(Long idLogin) {
		this.idLogin = idLogin;
	}

	public String getNomeLogin() {
		return nomeLogin;
	}

	public void setNomeLogin(String nomeLogin) {
		this.nomeLogin = nomeLogin;
	}

	public String getEmailLogin() {
		return emailLogin;
	}

	public void setEmailLogin(String emailLogin) {
		this.emailLogin = emailLogin;
	}

	public String getSenhaLogin() {
		return senhaLogin;
	}

	public void setSenhaLogin(String senhaLogin) {
		this.senhaLogin = senhaLogin;
	}

	public String getTokenLogin() {
		return tokenLogin;
	}

	public void setTokenLogin(String tokenLogin) {
		this.tokenLogin = tokenLogin;
	}

	}
