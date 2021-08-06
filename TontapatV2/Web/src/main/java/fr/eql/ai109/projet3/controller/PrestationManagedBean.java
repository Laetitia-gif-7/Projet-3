package fr.eql.ai109.projet3.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
//import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import fr.eql.ai109.projet3.entity.PrestationBU;
import fr.eql.ai109.projet3.entity.Utilisateur;
import fr.eql.ai109.projet3.ibusiness.PrestationIBusiness;

// @RequestScoped
// @ViewScoped

@ManagedBean(name = "mbPresta")
@ViewScoped
public class PrestationManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{mbCompte.utilisateur}")
	private Utilisateur utilisateurConnecte;
	
	private List<PrestationBU> prestations;
	
	private String viewXHTML = "reserveParClient.xhtml";
	
	public String getViewXHTML() {
		return viewXHTML;
	}

	public void setViewXHTML(String viewXHTML) {
		this.viewXHTML = viewXHTML;
	}

	@EJB
	private PrestationIBusiness prestaIBusiness;
	
	@PostConstruct
	public void init() {
		prestations = prestaIBusiness.findPrestationsByUtilisateur(utilisateurConnecte);
		System.out.println("done");
		for (PrestationBU prestationExt : prestations) {
			System.out.println("prestExt :" + prestationExt.getPrestation().getDebutPrestation());
			System.out.println("state string :"+ prestationExt.getStateString());
		}
	}
	
	// should make action on a specific prestation
	public void valide(int idPrestation) {
		//	PrestationExt prestations.valide();
		System.out.println("Valider : " + idPrestation);
	}
	
	public void annule(int idPrestation) {
		//	PrestationExt prestations.valide();
		System.out.println("Annuler la prestation :" + idPrestation);
	}
	
	public Utilisateur getUtilisateurConnecte() {
		return utilisateurConnecte;
	}

	public void setUtilisateurConnecte(Utilisateur utilisateurConnecte) {
		this.utilisateurConnecte = utilisateurConnecte;
	}

	public List<PrestationBU> getPrestations() {
		return prestations;
	}

	public void setPrestations(List<PrestationBU> prestations) {
		this.prestations = prestations;
	}

}
