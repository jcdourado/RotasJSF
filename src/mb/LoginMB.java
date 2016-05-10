package mb;

import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuário excluido com sucesso!!","Usuário excluido");
				FacesContext ctx = FacesContext.getCurrentInstance();
				ctx.addMessage("", msg);
				return "rotas";
			}
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuário ou senha inválidos!","Não foi encontrado este usuário");
			FacesContext ctx = FacesContext.getCurrentInstance();
			ctx.addMessage("formBody:txtUsuario", msg);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String abreRegistrar(){
		return "usuario";
	}
	
	public String registrar() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage();
		try {
			if(dao.adicionar(usuario)){
				 msg = new FacesMessage("Usuário registrado com sucesso!");
				 usuario = new Usuario();
			} else{
				msg = new FacesMessage("Usuário não registrado!");
			}
		} catch (ClassNotFoundException e) {
			msg = new FacesMessage("Usuário não registrado!");
			e.printStackTrace();
		} catch (SQLException e) {
			msg = new FacesMessage("Usuário não registrado!");
			e.printStackTrace();
		}
		ctx.addMessage("formBody:txtUsuario", msg);
		return "";
	}
	
	public String excluir(){
		try {
			dao.remover(usuario.getUsuario());
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuário excluido com sucesso!","");
			FacesContext ctx = FacesContext.getCurrentInstance();
			ctx.addMessage("formBody:msg", msg);
			return "login";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Não foi possível excluir!","Não foi possível excluir este usuário");
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
