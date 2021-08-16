package fr.eql.ai109.projet3.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.eql.ai109.projet3.entity.Terrain;
import fr.eql.ai109.projet3.entity.Troupeau;
import fr.eql.ai109.projet3.entity.Utilisateur;
import fr.eql.ai109.projet3.entity.dto.TroupeauTrouveApresRechercheDTO;
import fr.eql.ai109.projet3.ibusiness.CherchePrestationIBusiness;
import fr.eql.ai109.projet3.ibusiness.TerrainIBusiness;

@ManagedBean(name = "mbRecherche")
@RequestScoped
public class CherchePrestationManagedBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{mbCompte.utilisateur}")
	private Utilisateur utilisateurConnecte;
	private Terrain terrain;
	
	private List<TroupeauTrouveApresRechercheDTO> troupeauxCompatiblesAvecDates;
	private int idTerrain;
	
	@EJB
	TerrainIBusiness terrainIBusiness;
	
	@EJB
	CherchePrestationIBusiness cherchePrestationIBusiness;

	@PostConstruct
	public void init() {
		FacesContext ctx = FacesContext.getCurrentInstance();
        Map<String,String> params = ctx.getExternalContext().getRequestParameterMap();
        String idTerrainString = params.get("id");
        System.out.println("id terrain en entierString "+ idTerrainString);
        idTerrain =Integer.parseInt(idTerrainString);
        System.out.println("id terrain en entier "+ idTerrain);
		terrain = terrainIBusiness.findTerrainByIdTerrainAndUtilisateur(idTerrain, utilisateurConnecte);
		
		troupeauxCompatiblesAvecDates = cherchePrestationIBusiness.chercheTroupeauxCompatibles(idTerrain);
	}
	
	public Utilisateur getUtilisateurConnecte() {
		return utilisateurConnecte;
	}

	public void setUtilisateurConnecte(Utilisateur utilisateurConnecte) {
		this.utilisateurConnecte = utilisateurConnecte;
	}

	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}
	
	public int getIdTerrain() {
		return idTerrain;
	}

	public void setIdTerrain(int idTerrain) {
		this.idTerrain = idTerrain;
	}

	public List<TroupeauTrouveApresRechercheDTO> getTroupeauxCompatiblesAvecDates() {
		return troupeauxCompatiblesAvecDates;
	}

	public void setTroupeauxCompatiblesAvecDates(List<TroupeauTrouveApresRechercheDTO> troupeauxCompatiblesAvecDates) {
		this.troupeauxCompatiblesAvecDates = troupeauxCompatiblesAvecDates;
	}

}
