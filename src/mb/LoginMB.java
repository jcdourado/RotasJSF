package mb;

import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dao.UsuarioDAO;
import model.Usuario;

@ManagedBean
@SessionScoped
public class LoginMB {
	private Usuario usuario = new Usuario();
	private UsuarioDAO dao = new UsuarioDAO();
	
	public String logar(){
		try {
			usuario = dao.consultar(usuario);
			if(usuario.getNome() != null){
				usuario.setLogado(true);
				return "rotas?faces-redirect=true";
			}
		} catch (ClassNotFoundException | SQLException  e) {
			e.printStackTrace();
		}
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Usu�rio ou senha inv�lidos!","");
		FacesContext ctx = FacesContext.getCurrentInstance();
		ctx.addMessage("formBody:msg", msg);
		return "login";
	}
	
	public String abreRegistrar(){
		return "usuario?faces-redirect=true";
	}
	
	public String ver(){
		return "usuario?faces-redirect=true";
	}
	
	public String alterar(){
		FacesContext ctx = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage();
		try {
			dao.alterar(usuario);
			 msg = new FacesMessage("Usu�rio alterado com sucesso!");
			
		} catch (ClassNotFoundException | SQLException e) {
			msg = new FacesMessage("Usu�rio n�o alterado!");
			e.printStackTrace();
		} 
		ctx.addMessage("formBody:txtUsuario2", msg);
		return "";
		
	}
	
	public String registrar() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage();
		try {
			if(!dao.adicionar(usuario)){
				 msg = new FacesMessage("Usu�rio registrado com sucesso!");
				 usuario = new Usuario();
			} else{
				msg = new FacesMessage("Usu�rio n�o registrado!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			msg = new FacesMessage("Usu�rio n�o registrado!");
			e.printStackTrace();
		} 
		ctx.addMessage("formBody:txtUsuario", msg);
		return "";
	}
	
	public String sair(){
		usuario = new Usuario();
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession)ctx.getExternalContext().getSession(false);
        session.invalidate();
		return "login";
	}
	
	public String excluir(){
		try {
			dao.remover(usuario.getUsuario());
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Usu�rio excluido com sucesso!","");
			FacesContext ctx = FacesContext.getCurrentInstance();
			ctx.addMessage("formBody:msg", msg);
			return sair();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"N�o foi poss�vel excluir!","N�o foi poss�vel excluir este usu�rio");
		FacesContext ctx = FacesContext.getCurrentInstance();
		ctx.addMessage("formLogin:msg", msg);
		return "";
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
