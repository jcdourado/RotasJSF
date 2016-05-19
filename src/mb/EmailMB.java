package mb;

import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;
import org.hibernate.validator.constraints.Email;

import dao.UsuarioDAO;
import model.Usuario;

@ManagedBean
@RequestScoped
public class EmailMB {
	@Email(message="Insira um email válido")
	private String to = "";
	private String nome = "";
	private String usuario = ""; 
	private String senha = "";
	
	public String enviar(){
		UsuarioDAO dao = new UsuarioDAO();
		FacesContext ctx = FacesContext.getCurrentInstance();
		FacesMessage msg;
		try {
			Usuario userRecebido =  dao.consultar(to);
			if(userRecebido != null){
				services.Email e = new services.Email(to, userRecebido.getUsuario(),userRecebido.getNome(), userRecebido.getSenha());
				e.sendEmail();
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Email enviado com sucesso!", "Email enviado");
				ctx.addMessage(null, msg);
				return "";
			}
			
		} catch (ClassNotFoundException | SQLException | EmailException e) {
			e.printStackTrace();
		}
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Email inexistente!", "Falha no envio");
		ctx.addMessage(null, msg);
		return "";
	}
	
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
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
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}	
}
