package fr.eql.ai109.projet3.controller;

import java.io.Serializable;
//import java.util.List;
import java.util.Map;

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

@ManagedBean(name = "mbPresta")
@ViewScoped
public class PrestationManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{mbCompte.utilisateur}")
	private Utilisateur utilisateurConnecte;
	
	private Map<Integer,PrestationBU> prestations;
	
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
		//List list = prestaIBusiness.findPrestationsByUtilisateur(utilisateurConnecte);
		System.out.println("done");
		for (Integer prestationKey : prestations.keySet()) {
			System.out.println("prestExt :" + prestations.get(prestationKey).getPrestation().getDebutPrestation());
			System.out.println("state string :"+ prestations.get(prestationKey).getStateString());
		}
	}

	// should make action on a specific prestation
	public void valide(int idPrestation) {
		System.out.println("Valider : " + idPrestation);
//      no need with HahMap
//		PrestationBU pbu = prestations.stream()
//				.filter(pr -> pr.getPrestation().getIdPrestation() == idPrestation).findFirst().orElse(null);
		PrestationBU newPbu = prestaIBusiness.valide(prestations.get(idPrestation));
		// to insert in prestations
		System.out.println("state : " + newPbu.getStateString());
		System.out.println("IdPrestation : "+ idPrestation + ", newPbu Id : " + newPbu.getPrestation().getIdPrestation());
		
		// assert in debug mode
		assert idPrestation == newPbu.getPrestation().getIdPrestation();
		// update the value
		prestations.put(newPbu.getPrestation().getIdPrestation(), newPbu);
	}
	
	public void annule(int idPrestation) {
		System.out.println("Annuler la prestation :" + idPrestation);
	}

	
	public Utilisateur getUtilisateurConnecte() {
		return utilisateurConnecte;
	}

	public void setUtilisateurConnecte(Utilisateur utilisateurConnecte) {
		this.utilisateurConnecte = utilisateurConnecte;
	}

	public Map<Integer,PrestationBU> getPrestations() {
		return prestations;
	}

	public void setPrestations(Map<Integer,PrestationBU> prestations) {
		this.prestations = prestations;
	}
}
