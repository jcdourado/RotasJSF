package model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class Usuario {
	
	private String nome;
	
	private String usuario;
	private String email;
	
	private String senha;
	
	private boolean logado;
	
	public boolean isLogado() {
		return logado;
	}
	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	@NotEmpty(message="Nome não pode estar vazio")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Email(message="O campo precisa ter um email válido")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
