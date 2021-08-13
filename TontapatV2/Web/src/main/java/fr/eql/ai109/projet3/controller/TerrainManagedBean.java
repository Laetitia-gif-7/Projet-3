package fr.eql.ai109.projet3.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import fr.eql.ai109.projet3.entity.Terrain;
import fr.eql.ai109.projet3.entity.Utilisateur;
import fr.eql.ai109.projet3.ibusiness.TerrainIBusiness;

@ManagedBean(name = "mbTerrain")
@RequestScoped
public class TerrainManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{mbCompte.utilisateur}")
	private Utilisateur utilisateurConnecte;
	private List<Terrain> terrains;
	
	@EJB
	TerrainIBusiness terrainIBusiness;
	
	@PostConstruct
	public void init() {
		System.out.println("creation mbTerrain");
		terrains = terrainIBusiness.findTerrainsByUtilisateur(utilisateurConnecte);
	}
	
	@PreDestroy
	public void beforeDelete() {
		System.out.println("destruction mbTerrain");
	}

	public String lancerRecherche(int idTerrain) {
		return "rechercheClient.xhtml?faces-redirect=true&idTerrain=" + Integer.toString(idTerrain);
	}

	public Utilisateur getUtilisateurConnecte() {
		return utilisateurConnecte;
	}

	public void setUtilisateurConnecte(Utilisateur utilisateurConnecte) {
		this.utilisateurConnecte = utilisateurConnecte;
	}

	public List<Terrain> getTerrains() {
		return terrains;
	}

	public void setTerrains(List<Terrain> terrains) {
		this.terrains = terrains;
	}
	
	public String goToReservationTest(int idTerrain) {
		System.out.println("entry test goToReservation "+ idTerrain);
		return "reservationPrestation.xhtml?faces-redirect=true&id="+Integer.toString(idTerrain);
	}

}
