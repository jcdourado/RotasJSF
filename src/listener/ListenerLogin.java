package listener;

import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import mb.LoginMB;

public class ListenerLogin implements PhaseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent e) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		if(!"/login.xhtml".equals(ctx.getViewRoot().getViewId())){
			Application app = ctx.getApplication();
			LoginMB user = app.evaluateExpressionGet(ctx, "#{loginMB}", LoginMB.class);
			if(!user.getUsuario().isLogado()){
				NavigationHandler handler = app.getNavigationHandler();
				handler.handleNavigation(ctx, "", "login?faces-redirect=true");
				ctx.renderResponse();
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent e) {
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
