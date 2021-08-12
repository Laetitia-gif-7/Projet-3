package fr.eql.ai109.projet3.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import fr.eql.ai109.projet3.entity.dto.TerrainTrouveApresRechercheDTO;
import fr.eql.ai109.projet3.entity.dto.TroupeauTrouveApresRechercheDTO;
import fr.eql.ai109.projet3.ibusiness.CherchePrestationIBusiness;
import fr.eql.ai109.projet3.ibusiness.TerrainIBusiness;
import fr.eql.ai109.projet3.ibusiness.TroupeauIBusiness;

@ManagedBean(name = "mbRecherche")
@RequestScoped
public class CherchePrestationManagedBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{mbCompte.utilisateur}")
	private Utilisateur utilisateurConnecte;
	private Terrain terrain;
	private Troupeau troupeau;
	
	private List<TroupeauTrouveApresRechercheDTO> troupeauxCompatiblesAvecDates;
	private int idTerrain;
	
	private List<TerrainTrouveApresRechercheDTO> terrainsCompatiblesAvecDates;
	private int idTroupeau;
	
	@EJB
	TerrainIBusiness terrainIBusiness;
	
	@EJB
	TroupeauIBusiness troupeauIBusiness;
	
	@EJB
	CherchePrestationIBusiness cherchePrestationIBusiness;

	@PostConstruct
	public void init() {
		FacesContext ctx = FacesContext.getCurrentInstance();
        Map<String,String> params = ctx.getExternalContext().getRequestParameterMap();
        String idTerrainString = params.get("idTerrain");
        String idTroupeauString = params.get("idTroupeau");
        System.out.println("id terrain en entierString "+ idTerrainString);
        if(idTerrainString != null) {
        	idTerrain =Integer.parseInt(idTerrainString);
        	System.out.println("id terrain en entier "+ idTerrain);
    		//terrain = terrainIBusiness.findTerrainByIdTerrainAndUtilisateur(idTerrain, utilisateurConnecte);
    		terrain = terrainIBusiness.findByIdWithEquipement(idTerrain);
    		troupeauxCompatiblesAvecDates = cherchePrestationIBusiness.chercheTroupeauxCompatibles(idTerrain);
        }
        if(idTroupeauString != null) {
        	idTroupeau =Integer.parseInt(idTroupeauString);
    		troupeau = troupeauIBusiness.findTroupeauById(idTroupeau);
        	terrainsCompatiblesAvecDates = cherchePrestationIBusiness.chercheTerrainsCompatibles(idTroupeau);
        }
	}
	
	public Troupeau getTroupeau() {
		return troupeau;
	}

	public void setTroupeau(Troupeau troupeau) {
		this.troupeau = troupeau;
	}

	public String getMyDateString(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(date);
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

	public List<TerrainTrouveApresRechercheDTO> getTerrainsCompatiblesAvecDates() {
		return terrainsCompatiblesAvecDates;
	}

	public void setTerrainsCompatiblesAvecDates(List<TerrainTrouveApresRechercheDTO> terrainsCompatiblesAvecDates) {
		this.terrainsCompatiblesAvecDates = terrainsCompatiblesAvecDates;
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
