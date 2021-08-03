package fr.eql.ai109.projet3.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;


import fr.eql.ai109.projet3.entity.Troupeau;
import fr.eql.ai109.projet3.entity.Utilisateur;
import fr.eql.ai109.projet3.ibusiness.TroupeauIBuisness;



@ManagedBean(name = "mbTroupeau")
@RequestScoped
public class TroupeauManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{mbCompte.utilisateur}")
	private Utilisateur utilisateurConnecte;
	private List<Troupeau> troupeaux;
	
	@EJB
	TroupeauIBuisness troupeauIBuisness;
	
	@PostConstruct
	public void init() {
		troupeaux = troupeauIBuisness.findTroupeauxByUtilisateur(utilisateurConnecte);
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
