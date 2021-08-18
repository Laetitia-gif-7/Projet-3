package fr.eql.ai109.projet3.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

import fr.eql.ai109.projet3.entity.Utilisateur;
import fr.eql.ai109.projet3.ibusiness.CompteIBusiness;

@ManagedBean(name="mbCompte")
@SessionScoped
public class CompteMangedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String email;
	private String motDePasse;
	private Utilisateur utilisateur;
	
	@EJB
	private CompteIBusiness compteIBusiness;
	
	public String connection() {
		String forward = null;
		utilisateur = compteIBusiness.connection(email, motDePasse);
		if (utilisateur != null) {
			forward = "/connected.xhtml?faces-redirection=true";
		} else {
			FacesMessage facesMessage = new FacesMessage(
					FacesMessage.SEVERITY_WARN,
					"Identifiant et/ou mot de passe incorrect(s)",
					"Identifiant et/ou mot de passe incorrect(s)"
					);
			FacesContext.getCurrentInstance().addMessage("loginForm:inpLogin", facesMessage);
			FacesContext.getCurrentInstance().addMessage("loginForm:inpPassword", facesMessage);
			forward = "/login.xhtml?faces-redirection=false";
		}
		return forward;
	}
	
	public String deconnection() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/connected.xhtml?faces-redirect=true";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	
	
	
	
	

}
