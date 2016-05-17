package services;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Email {
	private String to;
	private String nome;
	private String usuario; 
	private String senha;
	
	public void sendEmail() throws EmailException {
	    
		   SimpleEmail email = new SimpleEmail();
		   //Utilize o hostname do seu provedor de email
		   System.out.println("alterando hostname...");
		   email.setHostName("smtp.gmail.com");
		   //Quando a porta utilizada não é a padrão (gmail = 465)
		   email.setSmtpPort(465);
		   //Adicione os destinatários
		   email.addTo(to, nome);
		   //Configure o seu email do qual enviará
		   email.setFrom("qq@gmail.com", "Julio Cezar Dourado");
		   //Adicione um assunto
		   email.setSubject("Senha -- Rotas");
		   //Adicione a mensagem do email
		   email.setMsg("O seu usuário é: " +usuario +"\n A sua senha é :" + senha);
		   //Para autenticar no servidor é necessário chamar os dois métodos abaixo
		   System.out.println("autenticando...");
		   email.setSSL(true);
		   email.setAuthentication("aa@gmail.com", "aa");
		   System.out.println("enviando...");
		   email.send();
		   System.out.println("Email enviado!");
		}
	
	public Email(String to, String usuario, String nome, String senha) {
		this.to = to;
		this.nome = nome;
		this.senha = senha;
		this.usuario = usuario;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSenha() {
		return senha;
	}
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
