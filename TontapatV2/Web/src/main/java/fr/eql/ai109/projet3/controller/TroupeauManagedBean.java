package fr.eql.ai109.projet3.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
//import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import fr.eql.ai109.projet3.entity.Troupeau;
import fr.eql.ai109.projet3.entity.Utilisateur;
import fr.eql.ai109.projet3.ibusiness.TroupeauIBusiness;



@ManagedBean(name = "mbTroupeau")
@ViewScoped
public class TroupeauManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{mbCompte.utilisateur}")
	private Utilisateur utilisateurConnecte;
	private List<Troupeau> troupeaux;
	//private int nbAnimaux;
	
//	public int getNbAnimaux() {
//		return nbAnimaux;
//	}
//
//	public void setNbAnimaux(int nbAnimaux) {
//		this.nbAnimaux = nbAnimaux;
//	}

	@EJB
	TroupeauIBusiness troupeauIBusiness;
	
	@PostConstruct
	public void init() {
		troupeaux = troupeauIBusiness.findTroupeauxByUtilisateur(utilisateurConnecte);
		//nbAnimaux = troupeaux.get(0).getCompositionTroupeau().get(0).getNbAnimaux();
		//System.out.println("hello");
	}
	
	public String lancerRecherche(int idTroupeau) {
		return "rechercheEleveur.xhtml?faces-redirect=true&idTroupeau=" + Integer.toString(idTroupeau);
	}

	public Utilisateur getUtilisateurConnecte() {
		return utilisateurConnecte;
	}

	public void setUtilisateurConnecte(Utilisateur utilisateurConnecte) {
		this.utilisateurConnecte = utilisateurConnecte;
	}

	public List<Troupeau> getTroupeaux() {
		return troupeaux;
	}

	public void setTroupeaux(List<Troupeau> troupeaux) {
		this.troupeaux = troupeaux;
	}
	
	
	
}
